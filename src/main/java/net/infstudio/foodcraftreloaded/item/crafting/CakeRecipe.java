package net.infstudio.foodcraftreloaded.item.crafting;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.common.FruitLoader;
import net.infstudio.foodcraftreloaded.common.VegetableLoader;
import net.infstudio.foodcraftreloaded.item.food.ItemJuices;
import net.infstudio.foodcraftreloaded.item.food.ItemVegetableJuices;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;

public class CakeRecipe extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
    @Override
    public boolean matches(@Nonnull InventoryCrafting inv, @Nonnull World worldIn) {
        ItemStack[] firstRow = new ItemStack[]{inv.getStackInRowAndColumn(0, 0), inv.getStackInRowAndColumn(0, 1), inv.getStackInRowAndColumn(0, 2)};
        int meta = firstRow[0].getMetadata();
        for (ItemStack stack : firstRow)
            if (stack.getMetadata() != meta || !(stack.getItem() instanceof ItemJuices) || !(stack.getItem() instanceof ItemVegetableJuices))
                return false;
        if (inv.getStackInRowAndColumn(1, 0)
            .isItemEqual(inv.getStackInRowAndColumn(1, 2)) &&
            inv.getStackInRowAndColumn(1, 0).getItem().equals(Items.SUGAR) &&
            inv.getStackInRowAndColumn(1, 1).getItem().equals(Items.EGG))
            if (inv.getStackInRowAndColumn(2, 0)
                .isItemEqual(inv.getStackInRowAndColumn(2, 1)) &&
                inv.getStackInRowAndColumn(2, 2).isItemEqual(inv.getStackInRowAndColumn(2, 1))
                && inv.getStackInRowAndColumn(2, 0).getItem().equals(Items.WHEAT))
                return true;
        return false;
    }

    @Nonnull
    @Override
    public ItemStack getCraftingResult(@Nonnull InventoryCrafting inv) {
        ItemStack result;
        if (inv.getStackInRowAndColumn(0, 0).getItem() instanceof ItemJuices) {
            result = new ItemStack(FoodCraftReloaded.getProxy().getLoaderManager().getLoader(FruitLoader.class).get().getCakes(), inv.getStackInRowAndColumn(0, 0).getMetadata());
        } else if (inv.getStackInRowAndColumn(0, 0).getItem() instanceof ItemVegetableJuices) {
            result = new ItemStack(FoodCraftReloaded.getProxy().getLoaderManager().getLoader(VegetableLoader.class).get().getCakes(), inv.getStackInRowAndColumn(0, 0).getMetadata());
        } else {
            result = ItemStack.EMPTY;
        }
        return result;
    }

    @Override
    public boolean canFit(int width, int height) {
        return width >= 3 && height >= 3;
    }

    @Nonnull
    @Override
    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }
}
