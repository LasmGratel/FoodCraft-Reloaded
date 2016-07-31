/**
 * Infinity Launcher for Minecraft.
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.infinitystudio.foodcraftreloaded.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Another implements of food in Minecraft.
 * Makes it works fine with this Mod.
 * @see net.minecraft.item.ItemFood
 */
public class FCRItemFood extends Item {
    /**
     * Number of ticks to run while 'EnumAction'ing until result.
     */
    public int itemUseDuration;

    /**
     * The amount this food item heals the player.
     */
    private int healAmount;

    /**
     * If this field is true, the food can be consumed even if the player don't need to eat.
     */
    private boolean alwaysEdible;

    /**
     * represents the potion effect that will occurr upon eating this food. Set by setPotionEffect
     */
    private PotionEffect potionId;

    /**
     * probably of the set potion effect occurring
     */
    private float potionEffectProbability;

    /**
     * Modifier for the food, see the example json.
     */
    private float[] modifier;

    public ItemStack onItemUseFinish(@Nullable ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        if (stack != null) {
            --stack.stackSize;
        }

        if (entityLiving instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            entityplayer.getFoodStats().addStats(getHealAmount(), 1.0f);
            worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            this.onFoodEaten(stack, worldIn, entityplayer);
            entityplayer.addStat(StatList.getObjectUseStats(this));
        }

        return stack;
    }

    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        if (!worldIn.isRemote && this.potionId != null && worldIn.rand.nextFloat() < this.potionEffectProbability)
        {
            player.addPotionEffect(new PotionEffect(this.potionId));
        }
    }

    public boolean isAlwaysEdible() {
        return alwaysEdible;
    }

    public void setAlwaysEdible(boolean alwaysEdible) {
        this.alwaysEdible = alwaysEdible;
    }

    public int getHealAmount() {
        return healAmount;
    }

    public void setHealAmount(int healAmount) {
        this.healAmount = healAmount;
    }

    public int getItemUseDuration() {
        return itemUseDuration;
    }

    public void setItemUseDuration(int itemUseDuration) {
        this.itemUseDuration = itemUseDuration;
    }

    public float getPotionEffectProbability() {
        return potionEffectProbability;
    }

    public void setPotionEffectProbability(float potionEffectProbability) {
        this.potionEffectProbability = potionEffectProbability;
    }

    public PotionEffect getPotionId() {
        return potionId;
    }

    public void setPotionId(PotionEffect potionId) {
        this.potionId = potionId;
    }

    public float[] getModifier() {
        return modifier;
    }

    public void setModifier(float[] modifier) {
        this.modifier = modifier;
    }
}
