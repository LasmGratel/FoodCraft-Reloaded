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

package cc.lasmgratel.foodcraftreloaded.minecraft.common.item.food;

import cc.lasmgratel.foodcraftreloaded.minecraft.api.capability.liqueur.LiqueurType;
import cc.lasmgratel.foodcraftreloaded.minecraft.api.capability.liqueur.LiqueurTypes;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.util.OreDictated;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class ItemLiqueur extends ItemDrink implements OreDictated {
    private LiqueurType type = LiqueurTypes.NORMAL;

    public ItemLiqueur(int amount) {
        super(amount);
    }

    public LiqueurType getLiqueurType() {
        return type;
    }

    public ItemLiqueur setLiqueurType(LiqueurType type) {
        this.type = type;
        return this;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(I18n.format("tooltip.fcrfood.liqueur.type", type.getLocalizedName()));
    }

    @Override
    public void getSubItems(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            ItemStack stack = new ItemStack(this);
            if (getLiqueurType() != LiqueurTypes.NORMAL)
                EnchantmentHelper.setEnchantments(Collections.singletonMap(Enchantments.UNBREAKING, 1), stack);
            items.add(stack);
        }
    }

    @Override
    public String[] getOreDictNames() {
        return ArrayUtils.addAll(super.getOreDictNames(), "listAllliqueur");
    }
}
