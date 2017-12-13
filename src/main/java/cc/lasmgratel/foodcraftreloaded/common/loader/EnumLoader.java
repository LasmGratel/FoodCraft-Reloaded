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

package cc.lasmgratel.foodcraftreloaded.common.loader;

import cc.lasmgratel.foodcraftreloaded.client.util.masking.Colorable;
import cc.lasmgratel.foodcraftreloaded.client.util.masking.CustomModelMasking;
import cc.lasmgratel.foodcraftreloaded.common.FoodCraftReloaded;
import com.google.common.reflect.TypeToken;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;
import java.lang.reflect.Constructor;
import java.util.*;
import java.util.stream.Collectors;

public class EnumLoader<T extends Enum<T>> {
    private final Map<Class<?>, Map<T, ?>> enumInstanceMap = new HashMap<>();

    /**
     * Attempt to register all entries from {@link #enumInstanceMap} to {@link ForgeRegistries}.
     * @see ForgeRegistries
     */
    public void register() {
        enumInstanceMap.forEach((instanceClass, enumMap) -> {
            if (IForgeRegistryEntry.class.isAssignableFrom(instanceClass))
                enumMap.values().stream().map(o -> (IForgeRegistryEntry<? extends IForgeRegistryEntry<?>>) o)
//                    .map(o -> new RegisterHandler(o))
                    .forEach(o -> {
//                    TODO RegisterManager.getInstance().putRegister(o);
                        if (o instanceof Item) ForgeRegistries.ITEMS.register((Item) o);
                        else if (o instanceof Block) ForgeRegistries.BLOCKS.register((Block) o);
                    });
            if (Fluid.class.isAssignableFrom(instanceClass))
                enumMap.values().stream().map(o -> (Fluid) o).forEach(FluidRegistry::addBucketForFluid);
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
        Map enumMap;
        if (enumInstanceMap.containsKey(value.getClass())) enumMap = enumInstanceMap.get(value.getClass());
        else enumMap = new EnumMap<>(getType());
        enumMap.put(enumInstance, value);
        enumInstanceMap.put(value.getClass(), enumMap);
    }

    @SideOnly(Side.CLIENT)
    public void registerColors() {
        enumInstanceMap.values().stream().map(Map::entrySet).map(Collection::stream).forEach(entries -> {
            entries.forEach(entry -> {
                if (entry.getValue() instanceof Item) {
                    FoodCraftReloaded.getLogger().info("Registering custom item color for " + ((Item) entry.getValue()).getClass().getSimpleName() + ":" + ((Item) entry.getValue()).getRegistryName());
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
                    Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
                        if (entry.getKey() instanceof Colorable)
                            if (entry.getValue() instanceof CustomModelMasking && ((CustomModelMasking) entry.getValue()).getTintIndex() != -1) {
                                if (tintIndex == ((CustomModelMasking) entry.getValue()).getTintIndex())
                                    return ((Colorable) entry.getKey()).getColor().getRGB();
                            } else if (tintIndex == 1)
                                return ((Colorable) entry.getKey()).getColor().getRGB();
                        return -1;
                    }, (Block) entry.getValue());
            });
        });
    }

    @SideOnly(Side.CLIENT)
    public void registerRenders() {
        enumInstanceMap.values().stream().map(Map::entrySet).map(Collection::stream).forEach(entries -> {
            entries.forEach(entry -> {
                if (Item.class.isAssignableFrom(entry.getValue().getClass())) {
                    if (entry.getValue() instanceof CustomModelMasking) {
                        registerRender((Item) entry.getValue(), 0, ((CustomModelMasking) entry.getValue()).getModelLocation());
                        FoodCraftReloaded.getLogger().info("Registered custom model " + entry.getValue().getClass() + " as " + ((CustomModelMasking) entry.getValue()).getModelLocation());
                    } else if (((Item) entry.getValue()).getRegistryName() != null) {
                        registerRender((Item) entry.getValue(), 0, new ModelResourceLocation(((Item) entry.getValue()).getRegistryName(), "inventory"));
                    }
                } else if (BlockFluidBase.class.isAssignableFrom(entry.getValue().getClass())) {
                    // TODO Null condition
                    registerFluidRender((BlockFluidBase) entry.getValue(), ((Block)entry.getValue()).getRegistryName().getResourcePath());
                }
            });
        });
    }

    @SideOnly(Side.CLIENT)
    protected void registerRender(Item item, int meta, ModelResourceLocation location) {
        ModelBakery.registerItemVariants(item, location);
        ModelLoader.setCustomModelResourceLocation(item, meta, location);
        ModelLoader.setCustomMeshDefinition(item, stack -> location);
    }

    @SideOnly(Side.CLIENT)
    protected void registerFluidRender(BlockFluidBase blockFluid, String blockStateName) {
        final String location = FoodCraftReloaded.MODID + ":" + blockStateName;
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
