/**
 * FoodCraft Mod for Minecraft.
 * Copyright (C) 2016 Infinity Studio.
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @license GPLv3
 */
package org.infinitystudio.foodcraftreloaded.item.food;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import org.infinitystudio.foodcraftreloaded.utils.potion.PotionHelper.PotionType;

public class ItemFcFood extends ItemFood {
    private boolean hasEffect = false;

    /**
     * Called when an ItemStack with NBT data is read to potentially that ItemStack's NBT data
     *
     * @param nbt
     */
    @Override
    public boolean updateItemStackNBT(NBTTagCompound nbt) {
        return super.updateItemStackNBT(nbt);
    }

    public ItemFcFood(float saturation) {
        super((int) saturation, saturation, false);
    }

    public boolean isHasEffect() {
        return hasEffect;
    }

    public void setHasEffect(boolean hasEffect) {
        this.hasEffect = hasEffect;
    }

    @Override
    public boolean hasEffect(ItemStack par1ItemStack) {
        return hasEffect;
    }

    public int getHealAmount() {
        return healAmount;
    }

    public void setHealAmount(int healAmount) {
        this.healAmount = healAmount;
    }

    public float getSaturation() {
        return saturationModifier;
    }

    public void setSaturation(float saturationModifier) {
        this.saturationModifier = saturationModifier;
    }

    @Override
    protected void onFoodEaten(ItemStack is, World w, EntityPlayer player) {
        if (hasEffect) {
            int o;
            if (!w.isRemote) {
                o = w.rand.nextInt(7);
                switch (o) {
                case 0:
                    player.addPotionEffect(PotionType.digSpeed.createPotionEffect(600, 1));
                    break;
                case 1:
                    player.addPotionEffect(PotionType.fireResistance.createPotionEffect(600, 1));
                    break;
                case 2:
                    player.addPotionEffect(PotionType.invisibility.createPotionEffect(600, 1));
                    break;
                case 3:
                    player.addPotionEffect(PotionType.jump.createPotionEffect(600, 1));
                    break;
                case 4:
                    player.addPotionEffect(PotionType.moveSpeed.createPotionEffect(600, 1));
                    break;
                case 5:
                    player.addPotionEffect(PotionType.nightVision.createPotionEffect(600, 1));
                    break;
                case 6:
                    player.addPotionEffect(PotionType.waterBreathing.createPotionEffect(600, 1));
                    break;
                }
            }
        }
        super.onFoodEaten(is, w, player);
    }

}
