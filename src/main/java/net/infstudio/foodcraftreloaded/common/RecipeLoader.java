package net.infstudio.foodcraftreloaded.common;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.api.recipe.DrinkRecipeManager;
import net.infstudio.foodcraftreloaded.item.crafting.CakeRecipe;
import net.infstudio.foodcraftreloaded.item.food.FruitType;
import net.infstudio.foodcraftreloaded.util.loader.annotation.Load;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.commons.lang3.StringUtils;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class RecipeLoader {
    private FruitLoader loader;

    @Load(LoaderState.AVAILABLE)
    public void loadDrinkRecipes() {
        loader = FoodCraftReloaded.getProxy().getLoaderManager().getLoader(FruitLoader.class).get();
        for (FruitType fruitType : FruitType.values())
            DrinkRecipeManager.getInstance().addRecipes("fruit" + StringUtils.capitalize(fruitType.toString()), new FluidStack(loader.getJuiceMap().get(fruitType), 500));
        ForgeRegistries.RECIPES.register(new CakeRecipe().setRegistryName(FoodCraftReloaded.MODID, "cake_recipe"));
    }
}
