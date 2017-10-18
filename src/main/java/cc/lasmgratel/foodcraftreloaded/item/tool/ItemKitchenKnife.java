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

package cc.lasmgratel.foodcraftreloaded.item.tool;

import cc.lasmgratel.foodcraftreloaded.FoodCraftReloaded;
import cc.lasmgratel.foodcraftreloaded.util.NameBuilder;
import cc.lasmgratel.foodcraftreloaded.init.FCRCreativeTabs;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class ItemKitchenKnife extends Item implements IItemColor {
    private EnumKitchenKnifeType type;

    public ItemKitchenKnife(EnumKitchenKnifeType type) {
        this.type = type;
        setMaxDamage(type.getMaxDamage());
        setMaxStackSize(1);
        setRegistryName(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName("kitchen", "knife", type.toString()));
        setUnlocalizedName(NameBuilder.buildUnlocalizedName("kitchen", "knife", type.toString()));
        setCreativeTab(FCRCreativeTabs.BASE);
    }

    public EnumKitchenKnifeType getType() {
        return type;
    }

    public void setType(EnumKitchenKnifeType type) {
        this.type = type;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int colorMultiplier(@Nonnull ItemStack stack, int tintIndex) {
        if (tintIndex == 0 && stack.getItem() instanceof ItemKitchenKnife)
            return ((ItemKitchenKnife) stack.getItem()).getType().getColor().getRGB();
        return -1;
    }
}
