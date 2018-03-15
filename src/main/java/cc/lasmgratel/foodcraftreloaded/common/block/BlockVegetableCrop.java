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

package cc.lasmgratel.foodcraftreloaded.common.block;

import cc.lasmgratel.foodcraftreloaded.client.util.masking.CustomModelMasking;
import cc.lasmgratel.foodcraftreloaded.common.FoodCraftReloaded;
import cc.lasmgratel.foodcraftreloaded.common.item.food.vegetable.ItemVegetable;
import cc.lasmgratel.foodcraftreloaded.common.item.food.vegetable.VegetableType;
import cc.lasmgratel.foodcraftreloaded.common.loader.VegetableEnumLoader;
import cc.lasmgratel.foodcraftreloaded.common.util.NameBuilder;
import cc.lasmgratel.foodcraftreloaded.common.util.enumeration.VegetableTyped;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class BlockVegetableCrop extends BlockCrops implements VegetableTyped, CustomModelMasking {
    private VegetableType type;

    public BlockVegetableCrop(VegetableType type) {
        super();
        this.type = type;
        setRegistryName(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName(type.toString(), "crop"));
    }

    @Nonnull
    protected Item getSeed() {
        return FoodCraftReloaded.getLoader(VegetableEnumLoader.class).get().getInstanceMap(ItemVegetable.class).get(type);
    }

    @Nonnull
    protected Item getCrop() {
        return FoodCraftReloaded.getLoader(VegetableEnumLoader.class).get().getInstanceMap(ItemVegetable.class).get(type);
    }

    @Override
    public VegetableType getType() {
        return type;
    }

    @Nonnull
    @Override
    public Map<IBlockState, ModelResourceLocation> getStateModelLocations() {
        Map<IBlockState, ModelResourceLocation> stateMap = new HashMap<>();
        for (int i = 0; i <= getMaxAge(); i++)
            stateMap.put(getDefaultState().withProperty(BlockCrops.AGE, i), new ModelResourceLocation("minecraft:carrots", "age=" + i));
        return stateMap;
    }
}
