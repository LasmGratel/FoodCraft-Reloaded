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

import cc.lasmgratel.foodcraftreloaded.minecraft.api.init.FCRItems;
import cc.lasmgratel.foodcraftreloaded.minecraft.api.recipe.DrinkRecipe;
import cc.lasmgratel.foodcraftreloaded.minecraft.api.recipe.RecipeManager;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.FoodCraftReloadedMod;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.block.BlockFruitSapling;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.fluid.FluidFruitJuice;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.fluid.FluidVegetableJuice;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.item.food.fruit.*;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.item.food.vegetable.*;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.item.tool.ItemKitchenKnife;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.item.tool.KitchenKnifeType;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.util.NameBuilder;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.util.Translator;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.util.loader.annotation.Load;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreIngredient;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class RecipeLoader {

    @Load(LoaderState.AVAILABLE)
    public void loadDrinkRecipes() {
        FruitEnumLoader fruitLoader = FoodCraftReloadedMod.getProxy().getLoaderManager().getLoader(FruitEnumLoader.class).get();
        VegetableEnumLoader vegetableLoader = FoodCraftReloadedMod.getProxy().getLoaderManager().getLoader(VegetableEnumLoader.class).get();
        for (FruitType fruitType : FruitType.values()) {
            RecipeManager.getInstance().addRecipe(new DrinkRecipe(fruitLoader.getInstanceMap(ItemFruit.class).get(fruitType), new FluidStack(fruitLoader.getInstance(FluidFruitJuice.class, fruitType), 1000)));
            GameRegistry.addShapedRecipe(new ResourceLocation(FoodCraftReloadedMod.MODID, NameBuilder.buildRegistryName(fruitType.toString(), "sapling")),
                new ResourceLocation(FoodCraftReloadedMod.MODID, "sapling"),
                new ItemStack(fruitLoader.getInstanceMap(BlockFruitSapling.class).get(fruitType)).setStackDisplayName(Translator.format("item.foodcraftreloaded.sapling", Translator.format(NameBuilder.buildUnlocalizedName("item.fruit", fruitType.toString())))),
                "XXX",
                "XSX",
                "XXX",
                'X', fruitLoader.getInstanceMap(ItemFruit.class).get(fruitType),
                'S', "treeSapling"
            );
            GameRegistry.addShapelessRecipe(
                new ResourceLocation(FoodCraftReloadedMod.MODID, NameBuilder.buildRegistryName(fruitType.toString(), "icecream")),
                new ResourceLocation(FoodCraftReloadedMod.MODID, "icecream"),
                new ItemStack(fruitLoader.getInstanceMap(ItemFruitIcecream.class).get(fruitType)),
                OreIngredient.fromItem(FCRItems.ORIGINAL_ICE_CREAM),
                OreIngredient.fromItem(fruitLoader.getInstanceMap(ItemFruitJuice.class).get(fruitType)));
            GameRegistry.addShapelessRecipe(
                new ResourceLocation(FoodCraftReloadedMod.MODID, NameBuilder.buildRegistryName("cake", "fruit", fruitType.toString())),
                new ResourceLocation(FoodCraftReloadedMod.MODID, "cake"),
                new ItemStack(fruitLoader.getInstanceMap(ItemFruitCake.class).get(fruitType)),
                OreIngredient.fromItem(fruitLoader.getInstanceMap(ItemFruitJuice.class).get(fruitType)),
                OreIngredient.fromItem(Items.CAKE)
            );
        }
        for (KitchenKnifeType kitchenKnifeType : KitchenKnifeType.values()) {
            GameRegistry.addShapedRecipe(
                new ResourceLocation(FoodCraftReloadedMod.MODID, NameBuilder.buildRegistryName("kitchen", "knife", kitchenKnifeType.toString())),
                new ResourceLocation(FoodCraftReloadedMod.MODID, "kitchen_knife"),
                new ItemStack(FoodCraftReloadedMod.getProxy().getLoaderManager().getLoader(KitchenKnifeLoader.class).get().getInstanceMap(ItemKitchenKnife.class).get(kitchenKnifeType)),
                "XX ",
                "XX ",
                "  S",
                'X', OreIngredient.fromStacks(kitchenKnifeType.getRepairItemStack()),
                'S', "stickWood"
            );
        }
        for (VegetableType vegetableType : VegetableType.values()) {
            RecipeManager.getInstance().addRecipe(
                new DrinkRecipe(vegetableLoader.getInstanceMap(ItemVegetable.class).get(vegetableType),
                    new FluidStack(vegetableLoader.getInstance(FluidVegetableJuice.class, vegetableType), 1000))
            );
            GameRegistry.addShapelessRecipe(
                new ResourceLocation(FoodCraftReloadedMod.MODID, NameBuilder.buildRegistryName(vegetableType.toString(), "icecream")),
                new ResourceLocation(FoodCraftReloadedMod.MODID, "icecream"),
                new ItemStack(vegetableLoader.getInstanceMap(ItemVegetableIcecream.class).get(vegetableType)),
                OreIngredient.fromItem(FCRItems.ORIGINAL_ICE_CREAM),
                OreIngredient.fromItem(vegetableLoader.getInstanceMap(ItemVegetableJuice.class).get(vegetableType)));
            GameRegistry.addShapelessRecipe(
                new ResourceLocation(FoodCraftReloadedMod.MODID, NameBuilder.buildRegistryName("cake", "vegetable", vegetableType.toString())),
                new ResourceLocation(FoodCraftReloadedMod.MODID, "cake"),
                new ItemStack(vegetableLoader.getInstanceMap(ItemVegetableCake.class).get(vegetableType)),
                OreIngredient.fromItem(vegetableLoader.getInstanceMap(ItemVegetableJuice.class).get(vegetableType)),
                OreIngredient.fromItem(Items.CAKE)
            );
        }
//        ForgeRegistries.RECIPES.register(new CakeRecipe().setRegistryName(FoodCraftReloadedMod.MODID, "cake_recipe"));
    }
}
