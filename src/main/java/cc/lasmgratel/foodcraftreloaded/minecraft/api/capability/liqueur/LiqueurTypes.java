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

package cc.lasmgratel.foodcraftreloaded.minecraft.api.capability.liqueur;

import cc.lasmgratel.foodcraftreloaded.minecraft.common.util.Translator;

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
    public void setTranslationKey(String unlocalizedName) {
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
