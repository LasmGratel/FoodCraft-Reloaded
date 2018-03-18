/*
 * FoodCraft Mod - Add more food to your Minecraft.
 * Copyright (C) 2017 Lasm Gratel
 *
 * This file is part of FoodCraft Mod.
 *
 * FoodCraft Mod is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FoodCraft Mod is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with FoodCraft Mod.  If not, see <http://www.gnu.org/licenses/>.
 */

package cc.lasmgratel.foodcraftreloaded.minecraft.common.loader;

import cc.lasmgratel.foodcraftreloaded.common.FoodCraftReloaded;
import cc.lasmgratel.foodcraftreloaded.minecraft.client.util.masking.Colorable;
import cc.lasmgratel.foodcraftreloaded.minecraft.client.util.masking.CustomModelMasking;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.FoodCraftReloadedMod;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.item.food.EffectiveItem;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.item.food.FCRItemFood;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.loader.register.RegisterManager;
import com.google.common.reflect.TypeToken;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Constructor;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class EnumLoader<T extends Enum<T>> {
    private final Map<Class<?>, Map<T, ?>> enumInstanceMap = new HashMap<>();

    /**
     * Attempt to register all entries from {@link #enumInstanceMap} to {@link ForgeRegistries}.
     * @see ForgeRegistries
     */
    public void register() {
        enumInstanceMap.forEach((instanceClass, enumMap) -> {
            if (IForgeRegistryEntry.class.isAssignableFrom(instanceClass))
                enumMap.entrySet().parallelStream()
                    .forEach(o -> {
                        if (o.getValue() instanceof FCRItemFood && o.getKey() instanceof EffectiveItem) {
                            if (((EffectiveItem) o.getKey()).getEffects() != null)
                            ((EffectiveItem) o.getKey()).getEffects().forEach(((FCRItemFood) o.getValue())::addEffect);
                        }
                    });
        });
    }

    public Class<T> getType() {
        return (Class<T>) new TypeToken<T>(getClass()){}.getRawType();
    }

    public <V> Map<T, V> getInstanceMap(Class<V> containerClass) {
        if (!enumInstanceMap.containsKey(containerClass))
            enumInstanceMap.put(containerClass, new EnumMap<T, V>(getType()));
        return (Map<T, V>) enumInstanceMap.get(containerClass);
    }

    public <V> V getValue(T enumInstance) {
        Class<V> valueClass = (Class<V>) new TypeToken<V>(){}.getRawType();
        return (V) enumInstanceMap.get(valueClass).get(enumInstance);
    }

    public <V> List<V> getValue() {
        Class<V> valueClass = (Class<V>) new TypeToken<V>(){}.getRawType();
        return enumInstanceMap.get(valueClass).values().stream().map(value -> (V) value).collect(Collectors.toList());
    }

    public <V> void putValue(T enumInstance, Class<V> valueClass) {
        V instance = null;
        try {
            for (Constructor<?> constructor : valueClass.getConstructors()) {
                if (constructor.getParameterCount() == 0)
                    instance = (V) constructor.newInstance();
                else if (constructor.getParameterTypes()[0].isAssignableFrom(enumInstance.getDeclaringClass()))
                    instance = (V) constructor.newInstance(enumInstance);
            }
            if (instance != null)
                putValue(enumInstance, instance);
        } catch (Exception e) {
            FoodCraftReloaded.getLogger().error("Un-able to create a instance", e);
        }
    }

    public <V> void putValue(Class<V> valueClass) {
        for (T e : getType().getEnumConstants()) putValue(e, valueClass);
    }

    public <V> void putValue(T enumInstance, V value) {
        if (value instanceof Fluid) {
            FluidRegistry.registerFluid((Fluid) value);
            FluidRegistry.addBucketForFluid((Fluid) value);
        }
        if (value instanceof IForgeRegistryEntry) {
            RegisterManager.getInstance().putRegister((IForgeRegistryEntry) value);
        }
        Map enumMap;
        if (enumInstanceMap.containsKey(value.getClass())) enumMap = enumInstanceMap.get(value.getClass());
        else enumMap = new EnumMap<>(getType());
        enumMap.put(enumInstance, value);
        enumInstanceMap.put(value.getClass(), enumMap);
    }

    public <V> V getInstance(T enumType) {
        return getInstance((Class<V>) new TypeToken<V>(getClass()){}.getRawType(), enumType);
    }

    public <V> V getInstance(Class<V> vClass, T enumType) {
        return getInstanceMap(vClass).get(enumType);
    }

    @SideOnly(Side.CLIENT)
    public void registerColors() {
        enumInstanceMap.values().stream().map(Map::entrySet).map(Collection::stream).forEach(entries -> entries.forEach(entry -> {
            if (entry.getValue() instanceof Item) {
                FoodCraftReloaded.getLogger().debug("Registering custom item color for " + ((Item) entry.getValue()).getClass().getSimpleName() + ":" + ((Item) entry.getValue()).getRegistryName());
                Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
                    try {
                        if (((CustomModelMasking) entry.getValue()).getTintIndex() != -1)
                            if (tintIndex == ((CustomModelMasking) entry.getValue()).getTintIndex())
                                return ((Colorable) entry.getKey()).getColor().getRGB();
                    } catch (ClassCastException ignored) {
                        try {
                            if (tintIndex == 1)
                                return ((Colorable) entry.getKey()).getColor().getRGB();
                        } catch (ClassCastException ignored2) { }
                    }
                    return -1;
                }, (Item) entry.getValue());
            } else if (entry.getValue() instanceof Block)
                if (entry.getValue() instanceof CustomModelMasking
                    && ((CustomModelMasking) entry.getValue()).getBlockColorMultiplier() != null
                    && ((CustomModelMasking) entry.getValue()).getItemColorMultiplier() != null) {
                    Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(((CustomModelMasking) entry.getValue()).getBlockColorMultiplier(), (Block) entry.getValue());
                    Minecraft.getMinecraft().getItemColors().registerItemColorHandler(((CustomModelMasking) entry.getValue()).getItemColorMultiplier(), (Block) entry.getValue());
                } else {
                    Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) -> {
                        if (entry.getKey() instanceof Colorable)
                            if (entry.getValue() instanceof CustomModelMasking && ((CustomModelMasking) entry.getValue()).getTintIndex() != -1) {
                                if (tintIndex == ((CustomModelMasking) entry.getValue()).getTintIndex())
                                    return ((Colorable) entry.getKey()).getColor().getRGB();
                            } else if (tintIndex == 1) {
                                return ((Colorable) entry.getKey()).getColor().getRGB();
                            }
                        return -1;
                    }, (Block) entry.getValue());
                    Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
                        if (entry.getKey() instanceof Colorable)
                            if (entry.getValue() instanceof CustomModelMasking && ((CustomModelMasking) entry.getValue()).getTintIndex() != -1) {
                                if (tintIndex == ((CustomModelMasking) entry.getValue()).getTintIndex())
                                    return ((Colorable) entry.getKey()).getColor().getRGB();
                            } else if (tintIndex == 1) {
                                return ((Colorable) entry.getKey()).getColor().getRGB();
                            }
                        return -1;
                    }, (Block) entry.getValue());
                }
        }));
    }

    @SideOnly(Side.CLIENT)
    public void registerRenders() {/*
        enumInstanceMap.values().stream().map(Map::entrySet).map(Collection::stream).forEach(entries -> entries.forEach(entry -> {
            if (Item.class.isAssignableFrom(entry.getValue().getClass())) {
                if (entry.getValue() instanceof CustomModelMasking) {
                    registerRender((Item) entry.getValue(), 0, ((CustomModelMasking) entry.getValue()).getModelLocation());
                    FoodCraftReloadedMod.getLogger().debug("Registered custom model " + entry.getValue().getClass() + " as " + ((CustomModelMasking) entry.getValue()).getModelLocation());
                } else if (((Item) entry.getValue()).getRegistryName() != null) {
                    registerRender((Item) entry.getValue(), 0, new ModelResourceLocation(((Item) entry.getValue()).getRegistryName(), "inventory"));
                }
            } else if (BlockFluidBase.class.isAssignableFrom(entry.getValue().getClass())) {
                // TODO Null condition
                registerFluidRender((BlockFluidBase) entry.getValue(), ((Block)entry.getValue()).getRegistryName().getResourcePath());
            } else if (Block.class.isAssignableFrom(entry.getValue().getClass())) {
                if (entry.getValue() instanceof CustomModelMasking) {
                    ModelLoader.setCustomStateMapper((Block) entry.getValue(), block -> ((CustomModelMasking) entry.getValue()).getStateModelLocations());
                    if (((CustomModelMasking) entry.getValue()).getModelLocation() != null)
                        registerRender(Item.getItemFromBlock((Block) entry.getValue()), 0, ((CustomModelMasking) entry.getValue()).getModelLocation());
                }
            }
        }));*/
    }

    @SideOnly(Side.CLIENT)
    protected void registerRender(Item item, int meta, ModelResourceLocation location) {
        ModelBakery.registerItemVariants(item, location);
        ModelLoader.setCustomModelResourceLocation(item, meta, location);
        ModelLoader.setCustomMeshDefinition(item, stack -> location);
    }

    @SideOnly(Side.CLIENT)
    protected void registerFluidRender(BlockFluidBase blockFluid, String blockStateName) {
        final String location = FoodCraftReloadedMod.MODID + ":" + blockStateName;
        final Item itemFluid = Item.getItemFromBlock(blockFluid);
        ModelLoader.setCustomMeshDefinition(itemFluid, stack -> new ModelResourceLocation(location, "fluid"));
        ModelLoader.setCustomStateMapper(blockFluid, new StateMapperBase() {
            @Nonnull
            @Override
            protected ModelResourceLocation getModelResourceLocation(@Nonnull IBlockState state) {
                return new ModelResourceLocation(location, "fluid");
            }
        });
    }

}
