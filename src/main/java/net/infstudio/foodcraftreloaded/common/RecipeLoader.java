package net.infstudio.foodcraftreloaded.common;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.init.FCRItems;
import net.infstudio.foodcraftreloaded.item.food.EnumFruitType;
import net.infstudio.foodcraftreloaded.item.food.ItemIcecreams;
import net.infstudio.foodcraftreloaded.utils.loader.annotation.Load;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import org.apache.commons.lang3.StringUtils;

public class RecipeLoader {
    @Load(LoaderState.AVAILABLE)
    public void loadShapedRecipes() {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FCRItems.GLASS_BOTTLE), "# #", "# #", "###", '#', "blockGlass"));
    }

    @Load(LoaderState.AVAILABLE)
    public void loadShapelessRecipes() {
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        ItemIcecreams icecreams = FoodCraftReloaded.getProxy().getLoaderManager().getLoader(FruitLoader.class).get().getIcecreams();

        for (EnumFruitType fruitType : EnumFruitType.values())
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(icecreams, 1, fruitType.ordinal()), "food" + StringUtils.capitalize(fruitType.toString()) + "juice", "foodIcecream"));
    }
}
