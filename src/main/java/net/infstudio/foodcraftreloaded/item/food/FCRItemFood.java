package net.infstudio.foodcraftreloaded.item.food;

import net.minecraft.item.ItemFood;
import net.minecraft.potion.PotionEffect;

public class FCRItemFood extends ItemFood {
    /** Number of ticks to run while 'EnumAction'ing until result. */
    public int itemUseDuration;
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

    public ItemFood setPotionEffect(PotionEffect effect, float probability)
    {
        this.potionId = effect;
        this.potionEffectProbability = probability;
        return this;
    }

    /**
     * Set the field 'alwaysEdible' to true, and make the food edible even if the player don't need to eat.
     */
    public ItemFood setAlwaysEdible()
    {
        this.alwaysEdible = true;
        return this;
    }
}
