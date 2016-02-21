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
import org.infinitystudio.foodcraftreloaded.item.VegetableItem;

public class FoodCraftRegistration {
    public static final CreativeTabs FcTabBase = new CreativeTabs("creativetabs.base") {
        public Item getTabIconItem() {
            return carrot;
        }
    };

    public static final CreativeTabs FcTabPlant = new CreativeTabs("creativetabs.plant") {
        public Item getTabIconItem() {
            return carrot;
        }
    };

    public static final CreativeTabs FcTabDrink = new CreativeTabs("creativetabs.drink") {
        public Item getTabIconItem() {
            return carrot;
        }
    };

    public static final CreativeTabs FcTabStaple = new CreativeTabs("creativetabs.staple") {
        public Item getTabIconItem() {
            return carrot;
        }
    };

    public static final CreativeTabs FcTabIngredient = new CreativeTabs("creativetabs.ingredient") {
        public Item getTabIconItem() {
            return carrot;
        }
    };

    public static final CreativeTabs FcTabSnack = new CreativeTabs("creativetabs.snack") {
        public Item getTabIconItem() {
            return carrot;
        }
    };

    public static final VegetableItem carrot = new VegetableItem("carrot", 2.5f, false, "cropCarrot");
}
