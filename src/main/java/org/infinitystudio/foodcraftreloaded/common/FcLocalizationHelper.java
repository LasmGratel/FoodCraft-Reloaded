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
package org.infinitystudio.foodcraftreloaded.common;

import net.minecraftforge.fml.common.registry.LanguageRegistry;
import org.infinitystudio.foodcraftreloaded.utils.modmanagent.common.ModFruit;

public class FcLocalizationHelper {
    public static void register() {
        for(ModFruit.FruitType fruitType : ModFruit.FruitType.values()) {
            LanguageRegistry.instance().addStringLocalization("itemJuice" + fruitType.name(),
                    String.format(LanguageRegistry.instance().getStringLocalization("item.itemJuice"), fruitType.name()));
            LanguageRegistry.instance().addStringLocalization("itemFruit" + fruitType.name() + "Soda",
                    String.format(LanguageRegistry.instance().getStringLocalization("item.itemFruitSoda"), fruitType.name()));
            LanguageRegistry.instance().addStringLocalization("itemFruit" + fruitType.name() + "Icecream",
                    String.format(LanguageRegistry.instance().getStringLocalization("item.itemFruitIcecream"), fruitType.name()));
        }
    }
}
