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

package cc.lasmgratel.foodcraftreloaded.minecraft.common.loader.register;

import cc.lasmgratel.foodcraftreloaded.common.FoodCraftReloaded;
import cc.lasmgratel.foodcraftreloaded.minecraft.client.util.masking.CustomModelMasking;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.FoodCraftReloadedMod;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.util.OreDictated;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;
import java.lang.reflect.Type;
import java.util.Arrays;

public final class RegisterHandler<T extends IForgeRegistryEntry<T>> {
    @Nonnull
    private final T value;

    private Class<?> typeClass;

    public RegisterHandler(@Nonnull T value) {
        this.value = value;
        typeClass = cast(value.getClass());
    }

    private static Class<?> cast(Class<?> typeClass) {
        if (IForgeRegistryEntry.class.isAssignableFrom(typeClass.getSuperclass()) && typeClass.getSuperclass() != IForgeRegistryEntry.class && typeClass.getSuperclass() != IForgeRegistryEntry.Impl.class)
            return cast(typeClass.getSuperclass());
        return typeClass;
    }

    @Nonnull
    public T getValue() {
        return value;
    }

    public void register(IForgeRegistry<T> registry) {
        registry.register(value);
    }

    @SideOnly(Side.CLIENT)
    public void registerRender() {
        if (value instanceof Item) {
            if (value instanceof CustomModelMasking) {
                registerRender((Item) value, ((CustomModelMasking) value).getModelLocation());
                FoodCraftReloaded.getLogger().debug("Registered custom model " + value.getClass() + " as " + ((CustomModelMasking) value).getModelLocation());
            } else if (value.getRegistryName() != null) {
                registerRender((Item) value, new ModelResourceLocation(value.getRegistryName(), "inventory"));
            }
        } else if (value instanceof BlockFluidBase) {
            registerFluidRender((BlockFluidBase) value, value.getRegistryName().getPath());
        } else if (value instanceof Block) {
            if (value instanceof CustomModelMasking) {
                ModelLoader.setCustomStateMapper((Block) value, block -> ((CustomModelMasking) value).getStateModelLocations());
                if (((CustomModelMasking) value).getModelLocation() != null)
                    registerRender(Item.getItemFromBlock((Block) value), ((CustomModelMasking) value).getModelLocation());
            }
        }
    }

    public void registerOre() {
        if (value instanceof OreDictated) {
            Arrays.stream(((OreDictated) value).getOreDictNames()).forEach(s -> {
                if (value instanceof Block)
                    OreDictionary.registerOre(s, (Block) value);
                else if (value instanceof Item)
                    OreDictionary.registerOre(s, (Item) value);
            });
        }
    }

    public void registerOre(String... names) {
        Arrays.asList(names).forEach(s -> {
            if (value instanceof Block)
                OreDictionary.registerOre(s, (Block) value);
            else if (value instanceof Item)
                OreDictionary.registerOre(s, (Item) value);
        });
    }

    @SideOnly(Side.CLIENT)
    private void registerRender(Item item, ModelResourceLocation location) {
        ModelLoader.setCustomModelResourceLocation(item, 0, location);
        if (item instanceof CustomModelMasking && ((CustomModelMasking) item).getItemMeshDefinition() != null)
            ModelLoader.setCustomMeshDefinition(item, ((CustomModelMasking) item).getItemMeshDefinition());
        else
            ModelLoader.setCustomMeshDefinition(item, stack -> location);
    }

    @SideOnly(Side.CLIENT)
    private void registerFluidRender(BlockFluidBase blockFluid, String blockStateName) {
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

    public Type getType() {
        return typeClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegisterHandler<?> that = (RegisterHandler<?>) o;

        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
