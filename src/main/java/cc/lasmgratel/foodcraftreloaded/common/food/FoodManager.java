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

package cc.lasmgratel.foodcraftreloaded.common.food;

import cc.lasmgratel.foodcraftreloaded.api.food.Food;
import cc.lasmgratel.foodcraftreloaded.api.food.material.Material;
import cc.lasmgratel.foodcraftreloaded.api.util.ReloadableManager;
import cc.lasmgratel.foodcraftreloaded.common.food.serialization.FoodSerializer;
import cc.lasmgratel.foodcraftreloaded.common.material.MaterialBase;
import cc.lasmgratel.foodcraftreloaded.common.material.MaterialBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class FoodManager implements ReloadableManager {
    public Map<String, Food> foodMap = new HashMap<>();
    public Map<String, Material> materialMap = new HashMap<>();
    private Path directory;

    private Gson gson = new GsonBuilder()
        .registerTypeAdapter(Food.class, new FoodSerializer())
        .create();

    public static FoodManager INSTANCE;

    public FoodManager(Path directory) {
        if (Files.isDirectory(directory))
            this.directory = directory.resolve("FoodCraft");
        else
            throw new IllegalArgumentException(directory + " is not a directory!");
        INSTANCE = this;
    }

    public void initializeDefault() {
        materialMap.put("tomato", new MaterialBuilder().withName("tomato").withEnergy(74014.96).build());
        foodMap.put("tomato", new FoodBuilder().withMaterial(materialMap.get("tomato"), 1000).withName("tomato").build());
        save();
    }

    @Override
    public void load() {
        Path materialPath = directory.resolve("materials");
        Path foodPath = directory.resolve("foods");

        try {
            if (!Files.isDirectory(materialPath))
                Files.createDirectories(materialPath);
            if (!Files.isDirectory(foodPath))
                Files.createDirectories(foodPath);

            Files.list(materialPath).forEach(material -> {
                try {
                    MaterialBase instance = gson.fromJson(Files.newBufferedReader(material), MaterialBase.class);
                    materialMap.put(instance.getName(), instance);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            Files.list(foodPath).forEach(food -> {
                try {
                    Food instance = gson.fromJson(Files.newBufferedReader(food), Food.class);
                    foodMap.put(instance.getName(), instance);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save() {
        Path materialPath = directory.resolve("materials");
        Path foodPath = directory.resolve("foods");

        try {
            if (!Files.isDirectory(materialPath))
                Files.createDirectories(materialPath);
            if (!Files.isDirectory(foodPath))
                Files.createDirectories(foodPath);

            for (Map.Entry<String, Material> entry: materialMap.entrySet())
                Files.write(materialPath.resolve(entry.getKey() + ".json"),
                    gson.toJson(entry.getValue()).getBytes(StandardCharsets.UTF_8));

            for (Map.Entry<String, Food> entry: foodMap.entrySet())
                Files.write(foodPath.resolve(entry.getKey() + ".json"),
                    gson.toJson(entry.getValue(), Food.class).getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
