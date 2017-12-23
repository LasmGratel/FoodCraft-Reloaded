package cc.lasmgratel.foodcraftreloaded.api.capability.liqueur;

import cc.lasmgratel.foodcraftreloaded.common.util.Translator;

public enum LiqueurTypes implements LiqueurType {
    NORMAL(1.0f), AGED(1.7f), COCKTAIL(2.0f);

    private String unlocalizedName;
    private float healModifier;

    LiqueurTypes(float healModifier) {
        this.unlocalizedName = name().toLowerCase();
        this.healModifier = healModifier;
    }

    @Override
    public String getUnlocalizedName() {
        return unlocalizedName;
    }

    @Override
    public void setUnlocalizedName(String unlocalizedName) {
        this.unlocalizedName = unlocalizedName;
    }

    @Override
    public float getHealModifier() {
        return healModifier;
    }

    @Override
    public void setHealModifier(float healModifier) {
        this.healModifier = healModifier;
    }

    @Override
    public String getLocalizedName() {
        return Translator.format("tooltip.fcrfood.liqueur.type." + getUnlocalizedName());
    }
}
