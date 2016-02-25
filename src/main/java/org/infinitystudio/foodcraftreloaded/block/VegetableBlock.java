/**
 * FoodCraft Mod for Minecraft.
 * Copyright (C) 2016 Infinity Studio.
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @license GPLv3
 */
package org.infinitystudio.foodcraftreloaded.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;

public class VegetableBlock extends BlockCrops {
    private String crop;
    private String seed;

    public VegetableBlock() {
        this.setHarvestLevel("pickaxe", -1);
        this.setStepSound(Block.soundTypeGrass);
    }

    public void setCrop(String crop) {
        this.crop = crop;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    @Override
    public Item getSeed() {
        return Item.getByNameOrId(seed);
    }

    @Override
    public Item getCrop() {
        return Item.getByNameOrId(crop);
    }
}
