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
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemFcFood extends ItemFood {
    protected boolean hasEffect = false;

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

    @Override
    protected void onFoodEaten(ItemStack is, World w, EntityPlayer ep) {
        if (hasEffect) {
            int o;
            if (!w.isRemote) {
                o = w.rand.nextInt(7);

                switch (o) {
                case 0:
                    ep.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 600, 1));
                    break;
                case 1:
                    ep.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 600, 1));
                    break;
                case 2:
                    ep.addPotionEffect(new PotionEffect(Potion.invisibility.id, 600, 1));
                    break;
                case 3:
                    ep.addPotionEffect(new PotionEffect(Potion.jump.id, 600, 1));
                    break;
                case 4:
                    ep.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 600, 1));
                    break;
                case 5:
                    ep.addPotionEffect(new PotionEffect(Potion.nightVision.id, 600, 1));
                    break;
                case 6:
                    ep.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 600, 1));
                    break;
                }
            }
        }
        super.onFoodEaten(is, w, ep);
    }

}
