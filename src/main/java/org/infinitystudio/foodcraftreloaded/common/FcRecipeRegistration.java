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

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import org.infinitystudio.foodcraftreloaded.utils.modmanagent.common.ModFruit;

import static org.infinitystudio.foodcraftreloaded.common.FoodCraftRegistration.*;

public class FcRecipeRegistration {
    public static void register() {
        ////////////////////////////////////////////////////////////////
        // Block Crafting
        // 物品合成
        ////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////
        // Item Crafting
        // 物品合成
        ////////////////////////////////////////////////////////////////

        // Food Crafting
        // 食物合成

        // Vegetable Crafting
        // 蔬菜合成
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(carrot, 2),
                               "cropCarrot", "cropCarrot"
                                                     ));

        // Fruit Crafting
        // 水果合成

        // Juice Crafting
        // 果汁合成
        for(ModFruit.FruitType fruitType : ModFruit.FruitType.values()) {
            String fruitName = fruitType.name();
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.getByNameOrId("itemFruit" + fruitName + "Icecream")),
                                   "food" + fruitName + "juice", "itemBaseIceCream"
                                                         ));
        }

        // Meat Crafting
        // 肉类合成

        // Water (Meat) Crafting
        // 水产合成
        GameRegistry.addSmelting(itemMeatCrabCooked, new ItemStack(itemMeatCrabRaw), 1.0f);
        GameRegistry.addSmelting(itemMeatShrimpCooked, new ItemStack(itemMeatShrimpRaw), 1.0f);
        
    }
}
