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

package cc.lasmgratel.foodcraftreloaded.minecraft.extra.item;

import cc.lasmgratel.foodcraftreloaded.minecraft.api.init.FCRCreativeTabs;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.FoodCraftReloadedMod;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.item.food.FCRItemFood;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.util.Translator;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * For Shp241
 */
public class ItemShp extends FCRItemFood {
    public ItemShp() {
        super(6, false);
        setRegistryName(FoodCraftReloadedMod.MODID, "shp241");
        setUnlocalizedName("shp241");
        setCreativeTab(FCRCreativeTabs.EXTRA);
        setMaxStackSize(1);
        addEffect(new PotionEffect(MobEffects.JUMP_BOOST, 10 * 20, 10));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(Translator.format("item.shp241.lore"));
    }

    @Nonnull
    public ItemStack onItemUseFinish(ItemStack stack, @Nullable World worldIn, EntityLivingBase entityLiving)
    {
        EntitySquid squid = new EntitySquid(worldIn);
        squid.setPosition(entityLiving.posX, entityLiving.posY, entityLiving.posZ);
        worldIn.spawnEntity(squid);
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
}
