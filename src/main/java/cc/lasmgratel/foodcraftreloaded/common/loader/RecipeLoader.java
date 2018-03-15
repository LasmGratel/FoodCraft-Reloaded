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

import cc.lasmgratel.foodcraftreloaded.api.recipe.DrinkRecipe;
import cc.lasmgratel.foodcraftreloaded.api.recipe.RecipeManager;
import cc.lasmgratel.foodcraftreloaded.common.FoodCraftReloaded;
import cc.lasmgratel.foodcraftreloaded.common.block.BlockFruitSapling;
import cc.lasmgratel.foodcraftreloaded.common.item.food.fruit.*;
import cc.lasmgratel.foodcraftreloaded.common.item.food.vegetable.ItemVegetable;
import cc.lasmgratel.foodcraftreloaded.common.item.food.vegetable.VegetableType;
import cc.lasmgratel.foodcraftreloaded.common.item.tool.ItemKitchenKnife;
import cc.lasmgratel.foodcraftreloaded.common.item.tool.KitchenKnifeType;
import cc.lasmgratel.foodcraftreloaded.common.util.NameBuilder;
import cc.lasmgratel.foodcraftreloaded.common.util.Translator;
import cc.lasmgratel.foodcraftreloaded.common.util.loader.annotation.Load;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreIngredient;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import org.apache.commons.lang3.StringUtils;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class RecipeLoader {

    @Load(LoaderState.AVAILABLE)
    public void loadDrinkRecipes() {
        FruitEnumLoader fruitLoader = FoodCraftReloaded.getProxy().getLoaderManager().getLoader(FruitEnumLoader.class).get();
        VegetableEnumLoader vegetableLoader = FoodCraftReloaded.getProxy().getLoaderManager().getLoader(VegetableEnumLoader.class).get();
        for (FruitType fruitType : FruitType.values()) {
            RecipeManager.getInstance().addRecipe(new DrinkRecipe(fruitLoader.getInstanceMap(ItemFruit.class).get(fruitType), new FluidStack(fruitLoader.getFluidJuiceEnumMap().get(fruitType), 1000)));
            GameRegistry.addShapedRecipe(new ResourceLocation(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName(fruitType.toString(), "sapling")),
                new ResourceLocation(FoodCraftReloaded.MODID, "sapling"),
                new ItemStack(fruitLoader.getInstanceMap(BlockFruitSapling.class).get(fruitType)).setStackDisplayName(Translator.format("item.foodcraftreloaded.sapling", Translator.format(NameBuilder.buildUnlocalizedName("item.fruit", fruitType.toString())))),
                "XXX",
                "XSX",
                "XXX",
                'X', fruitLoader.getInstanceMap(ItemFruit.class).get(fruitType),
                'S', "treeSapling"
            );
            ForgeRegistries.RECIPES.register(new ShapedOreRecipe(new ResourceLocation("food"), new ItemStack(fruitLoader.getInstanceMap(BlockFruitSapling.class).get(fruitType)), " F ", "FXF", " F ", 'F', "crop" + StringUtils.capitalize(fruitType.toString()), 'X', "treeSapling").setRegistryName("fruit_sapling"));
            ForgeRegistries.RECIPES.register(new ShapelessOreRecipe(new ResourceLocation("food"), new ItemStack(fruitLoader.getInstanceMap(ItemFruitIcecream.class).get(fruitType)), "food" + StringUtils.capitalize(fruitType.toString()) + "juice", "foodIcecream").setRegistryName("fruit_icecream"));
            GameRegistry.addShapelessRecipe(
                new ResourceLocation(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName("cake", "fruit", fruitType.toString())),
                new ResourceLocation(FoodCraftReloaded.MODID, "cake"),
                new ItemStack(fruitLoader.getInstanceMap(ItemFruitCake.class).get(fruitType)),
                OreIngredient.fromItem(fruitLoader.getInstanceMap(ItemFruitJuice.class).get(fruitType)),
                OreIngredient.fromItem(Items.CAKE)
            );
        }
        for (KitchenKnifeType kitchenKnifeType : KitchenKnifeType.values()) {
            GameRegistry.addShapedRecipe(
                new ResourceLocation(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName("kitchen", "knife", kitchenKnifeType.toString())),
                new ResourceLocation(FoodCraftReloaded.MODID, "kitchen_knife"),
                new ItemStack(FoodCraftReloaded.getProxy().getLoaderManager().getLoader(KitchenKnifeLoader.class).get().getInstanceMap(ItemKitchenKnife.class).get(kitchenKnifeType)),
                "XX ",
                "XX ",
                "  S",
                'X', kitchenKnifeType.getRepairItemStack(),
                'S', "stickWood"
            );
        }
        for (VegetableType vegetableType : VegetableType.values()) {
            RecipeManager.getInstance().addRecipe(new DrinkRecipe(vegetableLoader.getInstanceMap(ItemVegetable.class).get(vegetableType), new FluidStack(vegetableLoader.getFluidJuiceEnumMap().get(vegetableType), 1000)));
        }
//        ForgeRegistries.RECIPES.register(new CakeRecipe().setRegistryName(FoodCraftReloaded.MODID, "cake_recipe"));
    }
}
