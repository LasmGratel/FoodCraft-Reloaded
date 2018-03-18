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

import cc.lasmgratel.foodcraftreloaded.api.init.FCRCreativeTabs;
import cc.lasmgratel.foodcraftreloaded.api.init.FCRItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class ItemDrink extends FCRItemFood {
    public ItemDrink(int amount) {
        this(amount, 0.6f);
    }

    public ItemDrink(int amount, float saturation) {
        super(amount, saturation, false);
        setCreativeTab(FCRCreativeTabs.DRINK);
        setContainerItem(FCRItems.GLASS_BOTTLE);
    }

    @Nonnull
    @Override
    public ItemStack onItemUseFinish(ItemStack stack, @Nullable World worldIn, EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntityPlayer && worldIn != null) {
            EntityPlayer entityPlayer = (EntityPlayer)entityLiving;
            entityPlayer.getFoodStats().addStats(this, stack);
            worldIn.playSound(entityPlayer, entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            this.onFoodEaten(stack, worldIn, entityPlayer);
            Optional.ofNullable(StatList.getObjectUseStats(this)).ifPresent(entityPlayer::addStat);
            entityPlayer.addItemStackToInventory(new ItemStack(FCRItems.GLASS_BOTTLE));
        }
        return stack.splitStack(1);
    }

    @Override
    public String[] getOreDictNames() {
        return ArrayUtils.addAll(super.getOreDictNames(), "listAlldrink");
    }

    @Nonnull
    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.DRINK;
    }
}
