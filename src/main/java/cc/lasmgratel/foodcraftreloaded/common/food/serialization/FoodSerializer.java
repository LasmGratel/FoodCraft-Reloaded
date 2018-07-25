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

package cc.lasmgratel.foodcraftreloaded.common.food.serialization;

import cc.lasmgratel.foodcraftreloaded.api.food.Food;
import cc.lasmgratel.foodcraftreloaded.common.food.FoodBase;
import cc.lasmgratel.foodcraftreloaded.common.food.FoodManager;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FoodSerializer implements JsonSerializer<Food>, JsonDeserializer<Food> {
    @Override
    public Food deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        FoodBase food = new FoodBase();
        if (json.isJsonObject()) {
            JsonObject jsonObject = json.getAsJsonObject();
            food.setName(jsonObject.getAsJsonPrimitive("name").getAsString());
            JsonObject materials = jsonObject.getAsJsonObject("materials");
            materials.entrySet().forEach(entry -> food.getMaterialMap().put(FoodManager.INSTANCE.materialMap.get(entry.getKey()), entry.getValue().getAsInt()));
        } else {
            throw new JsonParseException("JSON " + json + " is not a JsonObject!");
        }
        return food;
    }

    @Override
    public JsonElement serialize(Food src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        JsonElement materials = context.serialize(src.getMaterialMap().entrySet().stream().map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey().getName(), entry.getValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        object.add("materials", materials);
        object.addProperty("name", src.getName());
        return object;
    }
}
