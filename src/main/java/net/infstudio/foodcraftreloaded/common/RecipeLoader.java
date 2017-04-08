package net.infstudio.foodcraftreloaded.common;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.init.FCRItems;
import net.infstudio.foodcraftreloaded.item.food.EnumFruitType;
import net.infstudio.foodcraftreloaded.utils.loader.annotation.Load;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import org.apache.commons.lang3.StringUtils;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class RecipeLoader {
    private FruitLoader loader;

    @Load(LoaderState.AVAILABLE)
    public void loadShapedRecipes() {
        loader = FoodCraftReloaded.getProxy().getLoaderManager().getLoader(FruitLoader.class).get();
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(FCRItems.GLASS_BOTTLE), "# #", "# #", "###", '#', "blockGlass"));
        for (EnumFruitType fruitType : EnumFruitType.values()) {
            GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(loader.getSaplingMap().get(fruitType)), " F ", "FXF", " F ", 'F', "crop" + StringUtils.capitalize(fruitType.toString()), 'X', "treeSapling"));
        }
    }

    @Load(LoaderState.AVAILABLE)
    public void loadShapelessRecipes() {
        loader = FoodCraftReloaded.getProxy().getLoaderManager().getLoader(FruitLoader.class).get();
        for (EnumFruitType fruitType : EnumFruitType.values()) {
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(loader.getIcecreams(), 1, fruitType.ordinal()), "food" + StringUtils.capitalize(fruitType.toString()) + "juice", "foodIcecream"));
        }
    }
}
