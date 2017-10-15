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

package net.infstudio.foodcraftreloaded.util;

import com.google.gson.JsonElement;

import java.io.File;
import java.util.Map;
import java.util.Set;

public interface ISettings {
    File getConfigFolder();
    File getConfigFile(String domain);
    Object remove(String domain, String property);
    void addProperty(String domain, String property, String value);
    void addProperty(String domain, String property, Number value);
    void addProperty(String domain, String property, boolean value);
    void addProperty(String domain, String property, char value);
    Object getProperty(String domain, String property);
    Set<Map.Entry<String, JsonElement>> entrySet(String domain);
}
