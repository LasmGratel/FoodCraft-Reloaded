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
package org.infinitystudio.foodcraftreloaded.item.food;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import org.infinitystudio.foodcraftreloaded.utils.potion.PotionHelper.PotionType;

/**
 * Shrimp
 * 虾
 */
public class ItemShrimp extends ItemFcFood {

    public ItemShrimp(float saturation) {
        super(saturation);
    }

    @Override
    protected void onFoodEaten(ItemStack is, World w, EntityPlayer ep) {
        int o;
        if (!w.isRemote) {
            o = w.rand.nextInt(1);

            switch (o) {
                case 0:
                    ep.addPotionEffect(new PotionEffect(PotionType.hunger.getPotion(), 6000, 1));
                    break;
            }
        }
        setHasEffect(false);
        super.onFoodEaten(is, w, ep);
    }
}
