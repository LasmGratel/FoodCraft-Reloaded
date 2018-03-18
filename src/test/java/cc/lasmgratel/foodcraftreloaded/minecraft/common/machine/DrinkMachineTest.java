package cc.lasmgratel.foodcraftreloaded.minecraft.common.machine;

import cc.lasmgratel.foodcraftreloaded.minecraft.api.recipe.DrinkRecipe;
import cc.lasmgratel.foodcraftreloaded.minecraft.api.recipe.RecipeInput;
import cc.lasmgratel.foodcraftreloaded.minecraft.api.recipe.RecipeManager;
import net.minecraft.item.Item;
import org.junit.Assert;
import org.junit.Test;

public class DrinkMachineTest {
    @Test
    public void testRecipeIngredients() {
        // Add null -> null recipe
        RecipeManager.getInstance().addRecipe(new DrinkRecipe(null, null));
        // Should be null recipe
        DrinkRecipe recipe = RecipeManager.getInstance().getRecipeNullable(DrinkRecipe.class, new RecipeInput((Item)null));

        // Should be presented
        Assert.assertNotNull(recipe);
        // Null input
        Assert.assertNull(recipe.getInput().first());
        // Null output
        Assert.assertNull(recipe.getOutput().first());
    }
}
