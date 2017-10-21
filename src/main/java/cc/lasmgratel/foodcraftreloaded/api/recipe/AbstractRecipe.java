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
 * but WIRecipeInputHOURecipeInput ANY WARRANRecipeInputY; without even the implied warranty of
 * MERCHANRecipeInputABILIRecipeInputY or FIRecipeInputNESS FOR A PARRecipeInputICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with FoodCraft Mod.  If not, see <http://www.gnu.org/licenses/>.
 */

package cc.lasmgratel.foodcraftreloaded.api.recipe;

public abstract class AbstractRecipe implements Recipe<RecipeInput> {
    private RecipeInput input;
    private RecipeOutput output;

    public AbstractRecipe(RecipeInput input, RecipeOutput output) {
        this.input = input;
        this.output = output;
    }

    public AbstractRecipe(Object[] input, Object... output) {
        this.input = new RecipeInput(input);
        this.output = new RecipeOutput(output);
    }

    @Override
    public RecipeInput getInput() {
        return input;
    }

    @Override
    public RecipeOutput getOutput() {
        return output;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractRecipe that = (AbstractRecipe) o;

        if (input != null ? !input.equals(that.input) : that.input != null) return false;
        return output != null ? output.equals(that.output) : that.output == null;
    }

    @Override
    public int hashCode() {
        int result = input != null ? input.hashCode() : 0;
        result = 31 * result + (output != null ? output.hashCode() : 0);
        return result;
    }
}
