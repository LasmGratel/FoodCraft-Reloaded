/**
 * FoodCraft Mod for Minecraft.
 * Copyright (C) 2016 Infinity Studio.
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
package net.infstudio.foodcraftreloaded.item;

import net.infstudio.foodcraftreloaded.utils.food.Food;
import net.infstudio.foodcraftreloaded.utils.food.FoodEffect;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
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
     * The amount this food item heals the player.
     */
    private int healAmount;

    /**
     * If this field is true, the food can be consumed even if the player don't need to eat.
     */
    private boolean alwaysEdible;

    private FoodEffect[] effects;

    /**
     * Modifier for the food, see the example json.
     */
    private float[] modifier;

    public FCRItemFood(Food food) {
        healAmount = food.getFoodLevel();
        alwaysEdible = food.isAlwaysEdible();
        effects = food.getEffects();
        modifier = food.getModifier();
    }

    public ItemStack onItemUseFinish(@Nullable ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        if (stack != null) {
            stack.setCount(stack.getCount() - 1);
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
        if(effects != null)
        for(FoodEffect effect : effects) {
            float potionEffectProbability = effect.getProbability();
            int amplifier = effect.getAmplifier();
            int duration = effect.getDuration();
            if (!worldIn.isRemote && worldIn.rand.nextFloat() < potionEffectProbability && Potion.getPotionFromResourceLocation(effect.getEffectName()) != null) {
                player.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation(effect.getEffectName()), duration, amplifier));
            }
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

    public FoodEffect[] getEffects() {
        return effects;
    }

    public void setEffects(FoodEffect[] effects) {
        this.effects = effects;
    }

    public float[] getModifier() {
        return modifier;
    }

    public void setModifier(float[] modifier) {
        this.modifier = modifier;
    }
}
