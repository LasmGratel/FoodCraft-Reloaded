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
package org.infinitystudio.foodcraftreloaded.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import org.infinitystudio.foodcraftreloaded.block.BaseBlock;
import org.infinitystudio.foodcraftreloaded.block.VegetableBlock;
import org.infinitystudio.foodcraftreloaded.item.VegetableItem;
import org.infinitystudio.foodcraftreloaded.utils.modmanagent.common.ModBlock;
import org.infinitystudio.foodcraftreloaded.utils.modmanagent.common.ModVegetable;
import org.infinitystudio.foodcraftreloaded.utils.modmanagent.common.ModVegetableBlock;

public class FoodCraftRegistration {
    /**
     * 基础 Base
     */
    public static final CreativeTabs FcTabBase = new CreativeTabs("creativetabs.base") {
        @Override
        public Item getTabIconItem() {
            return carrot;
        }
    };

    /**
     * 植物&种子 Plants & Seeds
     */
    public static final CreativeTabs FcTabPlant = new CreativeTabs("creativetabs.plant") {
        @Override
        public Item getTabIconItem() {
            return carrot;
        }
    };

    /**
     * 饮品 Drinks
     */
    public static final CreativeTabs FcTabDrink = new CreativeTabs("creativetabs.drink") {
        @Override
        public Item getTabIconItem() {
            return carrot;
        }
    };

    /**
     * 主食 Staple
     */
    public static final CreativeTabs FcTabStaple = new CreativeTabs("creativetabs.staple") {
        @Override
        public Item getTabIconItem() {
            return carrot;
        }
    };

    /**
     * 食材 Ingredient
     */
    public static final CreativeTabs FcTabIngredient = new CreativeTabs("creativetabs.ingredient") {
        @Override
        public Item getTabIconItem() {
            return carrot;
        }
    };

    /**
     * 小吃 Snack
     */
    public static final CreativeTabs FcTabSnack = new CreativeTabs("creativetabs.snack") {
        @Override
        public Item getTabIconItem() {
            return carrot;
        }
    };

    @ModBlock(name = "blockRice")
    public static BaseBlock blockRice;

    @ModBlock(name = "blockCarrot")
    @ModVegetableBlock(name = "blockCarrot", seedName = "carrot", cropName = "carrot")
    public static VegetableBlock blockCarrot;

    @ModBlock(name = "carrot")
    @ModVegetable(name = "carrot", satuation = 2.5f, oredicts = {"cropCarrot"}, seedBlockName = "blockCarrot")
    public static VegetableItem carrot;
}
