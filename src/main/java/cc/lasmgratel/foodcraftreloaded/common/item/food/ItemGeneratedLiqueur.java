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

package cc.lasmgratel.foodcraftreloaded.common.item.food;

import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.function.Function;

/**
 * A simple liqueur with display name configured.
 */
public class ItemGeneratedLiqueur extends ItemLiqueur {
    private Function<ItemStack, String> itemStackDisplayNameCallback;

    public ItemGeneratedLiqueur(int amount) {
        super(amount);
    }

    public void setItemStackDisplayNameCallback(Function<ItemStack, String> itemStackDisplayNameCallback) {
        this.itemStackDisplayNameCallback = itemStackDisplayNameCallback;
    }

    public Function<ItemStack, String> getItemStackDisplayNameCallback() {
        return itemStackDisplayNameCallback;
    }

    @Nonnull
    @Override
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        if (getItemStackDisplayNameCallback() != null)
            return getItemStackDisplayNameCallback().apply(stack);
        else
            return super.getItemStackDisplayName(stack);
    }
}
