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

package cc.lasmgratel.foodcraftreloaded.minecraft.client.interaction.jei.drink;

import cc.lasmgratel.foodcraftreloaded.minecraft.client.interaction.jei.FCRRecipeCategory;
import cc.lasmgratel.foodcraftreloaded.minecraft.client.interaction.jei.RecipeUIDs;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.FoodCraftReloadedMod;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.block.tileentity.TileEntityDrinkMachine;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.util.Translator;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class DrinkRecipeCategory implements FCRRecipeCategory<DrinkRecipeWrapper> {
    private static final String TITLE = Translator.format("container.drink_machine");
    private static final ResourceLocation LOCATION = new ResourceLocation(FoodCraftReloadedMod.MODID, "textures/gui/container/drink_machine.png");
    private final IDrawable background;
    private final IDrawableAnimated arrow;

    public DrinkRecipeCategory(IGuiHelper helper) {
        background = helper.createDrawable(LOCATION, 25, 10, 121, 60);
        IDrawableStatic arrowStatic = helper.createDrawable(LOCATION, 176, 0, 24, 17);
        arrow = helper.createAnimatedDrawable(arrowStatic, 200, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Nonnull
    @Override
    public String getUid() {
        return RecipeUIDs.DRINK;
    }

    @Nonnull
    @Override
    public String getTitle() {
        return TITLE;
    }

    @Nonnull
    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void drawExtras(Minecraft minecraft) {
        arrow.draw(minecraft, 88 - 25, 32 - 10);
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull DrinkRecipeWrapper recipeWrapper, @Nonnull IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, true, 65 - 25 - 1, 31 - 10 - 1);
        recipeLayout.getItemStacks().init(1, false, 121 + 4 - 25 - 1, 28 + 4 - 10 - 1);
        recipeLayout.getFluidStacks().init(0, false, 1, 1, 16, 58, TileEntityDrinkMachine.FLUID_CAPACITY, true, null);
        recipeWrapper.getIngredients(ingredients);
        recipeLayout.getItemStacks().set(ingredients);
        recipeLayout.getFluidStacks().set(ingredients);
    }
}
