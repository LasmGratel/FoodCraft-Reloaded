package net.infstudio.foodcraftreloaded.api.recipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

public class DrinkRecipeManager {
    private Map<ItemStack, DrinkRecipe> recipeMap = new HashMap<>();

    private static final DrinkRecipeManager INSTANCE = new DrinkRecipeManager();

    public static DrinkRecipeManager getInstance() {
        return INSTANCE;
    }

    public Optional<DrinkRecipe> getRecipe(@Nonnull ItemStack stack) {
        return Optional.ofNullable(recipeMap.get(stack));
    }

    public List<DrinkRecipe> getRecipes(String oreDict) {
        return OreDictionary.getOres(oreDict).stream().map(recipeMap::get).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Nullable
    public DrinkRecipe getRecipeNullable(@Nonnull ItemStack stack) {
        return recipeMap.get(stack);
    }

    public boolean hasRecipe(@Nonnull ItemStack stack) {
        return recipeMap.containsKey(stack);
    }

    public boolean hasRecipes(String oreDict) {
        return OreDictionary.getOres(oreDict).stream().map(recipeMap::get).anyMatch(Objects::nonNull);
    }

    public void addRecipe(@Nonnull ItemStack input, FluidStack output) {
        recipeMap.put(input, new DrinkRecipe(output));
    }

    public void addRecipes(String oreDictInput, FluidStack output) {
        OreDictionary.getOres(oreDictInput).forEach(stack -> recipeMap.put(stack, new DrinkRecipe(output)));
    }

    public void removeRecipe(@Nonnull ItemStack input) {
        recipeMap.remove(input);
    }

    public void removeRecipes(String oreDictInput) {
        OreDictionary.getOres(oreDictInput).forEach(recipeMap::remove);
    }

    public float getXp(@Nonnull ItemStack input) {
        return Optional.ofNullable(recipeMap.get(input)).map(DrinkRecipe::getXp).orElse(0f);
    }
}
