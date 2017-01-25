/**
 * FoodCraft Mod for Minecraft.
 * Copyright (C) 2016 Infinity Studio.
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.infstudio.foodcraftreloaded.common;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.item.FCRItemFood;
import net.infstudio.foodcraftreloaded.utils.food.FCRItemStackFoodHelper;
import net.infstudio.foodcraftreloaded.utils.food.Food;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;

public class FCRCommonRegistration {
    public static Collection<ItemStack> foods = new ArrayList<>();

    @SideOnly(Side.CLIENT)
    public static void addFood(Food food) {
        Item foodItem = new FCRItemFood(food);
        ModelLoader.setCustomMeshDefinition(foodItem, new FoodModelHelper());
        ModelBakery.registerItemVariants(foodItem, new ModelResourceLocation(FoodCraftReloaded.MODID + ":" + food.getUnlocalizedName(), "inventory"));
    }

    private static class FoodModelHelper implements ItemMeshDefinition {
        @Override
        @Nonnull
        public ModelResourceLocation getModelLocation(@Nonnull ItemStack stack) {
            Food food = FCRItemStackFoodHelper.getFoodFromStack(stack);
            if (food != null)
                return new ModelResourceLocation(food.getUnlocalizedName(), "inventory");
            return new ModelResourceLocation("");
        }
    }
}
