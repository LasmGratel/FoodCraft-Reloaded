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

package cc.lasmgratel.foodcraftreloaded.item.food;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class FCRItemFood extends ItemFood {
    /** Number of ticks to run while 'EnumAction'ing until result. */
    public int itemUseDuration = 32;
    /** The amount this food item heals the player. */
    private int healAmount;
    private float saturationModifier;
    /** Whether wolves like this food (true for raw and cooked porkchop). */
    private boolean isWolfsFavoriteMeat;
    /** If this field is true, the food can be consumed even if the player don't need to eat. */
    private boolean alwaysEdible;
    /** represents the potion effect that will occurr upon eating this food. Set by setPotionEffect */
    private PotionEffect potionId;
    /** probably of the set potion effect occurring */
    private float potionEffectProbability;

    public FCRItemFood(int amount, boolean isWolfFood) {
        super(amount, isWolfFood);
        this.healAmount = amount;
        this.isWolfsFavoriteMeat = isWolfFood;
    }

    public FCRItemFood(int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);
        this.healAmount = amount;
        this.saturationModifier = saturation;
        this.isWolfsFavoriteMeat = isWolfFood;
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

    @Override
    public boolean isWolfsFavoriteMeat() {
        return isWolfsFavoriteMeat;
    }

    public void setWolfsFavoriteMeat(boolean wolfsFavoriteMeat) {
        isWolfsFavoriteMeat = wolfsFavoriteMeat;
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

    public float getSaturationModifier() {
        return saturationModifier;
    }

    public void setSaturationModifier(float saturationModifier) {
        this.saturationModifier = saturationModifier;
    }

    @Nonnull
    public ItemFood setPotionEffect(@Nonnull PotionEffect effect, float probability) {
        this.potionId = effect;
        this.potionEffectProbability = probability;
        return this;
    }

    /**
     * Set the field 'alwaysEdible' to true, and make the food edible even if the player don't need to eat.
     */
    @Nonnull
    public ItemFood setAlwaysEdible() {
        this.alwaysEdible = true;
        return this;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        FCRItemFood item = (FCRItemFood) stack.getItem();
        tooltip.add(I18n.format("tooltip.fcrfood.heal", item.healAmount));
        tooltip.add(I18n.format("tooltip.fcrfood.duration", (item.itemUseDuration / 20.0)));
    }
}
