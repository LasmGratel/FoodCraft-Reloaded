package cc.lasmgratel.foodcraftreloaded.common.machine;

import cc.lasmgratel.foodcraftreloaded.api.recipe.DrinkRecipe;
import cc.lasmgratel.foodcraftreloaded.api.recipe.RecipeInput;
import cc.lasmgratel.foodcraftreloaded.api.recipe.RecipeManager;
import net.minecraft.item.Item;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class DrinkMachineTest {
    @Test
    public void testRecipeIngredients() {
        // Add null -> null recipe
        RecipeManager.getInstance().addRecipe(new DrinkRecipe(null, null));
        // Should be null recipe
        Optional<DrinkRecipe> recipe = RecipeManager.getInstance().getRecipe(DrinkRecipe.class, new RecipeInput((Item)null));

        // Should be presented
        Assert.assertTrue(recipe.isPresent());
        // Null input
        Assert.assertNull(recipe.get().getInput().first());
        // Null output
        Assert.assertNull(recipe.get().getOutput().first());
    }
}
