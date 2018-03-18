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

package cc.lasmgratel.foodcraftreloaded.common.loader;

import cc.lasmgratel.foodcraftreloaded.api.init.FCRPotions;
import cc.lasmgratel.foodcraftreloaded.common.FoodCraftReloaded;
import cc.lasmgratel.foodcraftreloaded.common.loader.register.RegisterManager;
import cc.lasmgratel.foodcraftreloaded.common.util.NameBuilder;
import cc.lasmgratel.foodcraftreloaded.common.util.loader.annotation.RegPotion;
import net.minecraft.potion.Potion;

import java.lang.reflect.Field;

public class PotionLoader {
    public PotionLoader() {
        for (Field field : FCRPotions.class.getFields()) {
            field.setAccessible(true);
            try {
                Potion potion = (Potion) field.get(null);
                RegPotion anno = field.getAnnotation(RegPotion.class);
                if (anno == null)
                    continue;
                potion.setRegistryName(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName(anno.value()));
                potion.setPotionName(NameBuilder.buildUnlocalizedName(anno.value()));
                RegisterManager.getInstance().putRegister(potion);
            } catch (Exception e) {
                FoodCraftReloaded.getLogger().error("Un-able to register potion!", e);
            }
        }
    }
}
