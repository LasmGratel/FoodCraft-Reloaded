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
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public class CakeRecipe extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
    @Override
    public boolean matches(@Nonnull InventoryCrafting inv, @Nonnull World worldIn) {
        boolean cakes = false;
        boolean juices = false;
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack stack = inv.getStackInSlot(i);
            if (!stack.isEmpty() && (stack.getItem() != Items.CAKE ||
                !(stack.getItem() instanceof ItemJuices) && !(stack.getItem() instanceof ItemVegetableJuices)))
                return false;
            if (stack.getItem() == Items.CAKE)
                if (cakes)
                    return false;
                else
                    cakes = true;
            if (stack.getItem() instanceof ItemJuices || stack.getItem() instanceof ItemVegetableJuices)
                if (juices)
                    return false;
                else
                    juices = true;
        }
        return cakes && juices;
    }

    @Nonnull
    @Override
    @SuppressWarnings("ConstantConditions")
    public ItemStack getCraftingResult(@Nonnull InventoryCrafting inv) {
        ItemStack[] invArray = new ItemStack[inv.getSizeInventory()];
        for (int i = 0; i < inv.getSizeInventory(); i++)
            invArray[i] = inv.getStackInSlot(i);
        AtomicReference<ItemStack> ret = new AtomicReference<>(ItemStack.EMPTY);
        Arrays.stream(invArray).filter(itemStack -> itemStack.getItem() instanceof ItemJuices || itemStack.getItem() instanceof ItemVegetableJuices).findAny().ifPresent(juice -> {
            if (juice.getItem() instanceof ItemJuices)
                ret.set(new ItemStack(FoodCraftReloaded.getProxy().getLoaderManager().getLoader(FruitLoader.class).get().getCakes(), 1, juice.getMetadata()));
            else if (juice.getItem() instanceof ItemVegetableJuices)
                ret.set(new ItemStack(FoodCraftReloaded.getProxy().getLoaderManager().getLoader(VegetableLoader.class).get().getCakes(), 1, juice.getMetadata()));
        });
        return ret.get();
    }

    @Override
    public boolean canFit(int width, int height) {
        return width >= 2 && height >= 1;
    }

    @Nonnull
    @Override
    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }
}
