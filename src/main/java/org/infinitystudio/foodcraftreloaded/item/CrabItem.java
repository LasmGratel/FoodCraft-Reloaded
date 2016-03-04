/**
 * Singularity Mod for Minecraft.
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
package org.infinitystudio.foodcraftreloaded.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * Crab
 * 螃蟹
 */
public class CrabItem extends FoodItem {

    public CrabItem(float saturation) {
        super(saturation);
    }

    @Override
    protected void onFoodEaten(ItemStack is, World w, EntityPlayer ep) {
        int o;
        if (!w.isRemote) {
            o = w.rand.nextInt(1);

            switch (o) {
                case 0:
                    ep.addPotionEffect(new PotionEffect(Potion.hunger.id, 6000, 1));
                    break;
            }
        }
        super.onFoodEaten(is, w, ep);
    }
}
