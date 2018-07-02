package cc.lasmgratel.foodcraftreloaded.minecraft.common.loader;

import cc.lasmgratel.foodcraftreloaded.common.food.FoodManager;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.util.loader.annotation.Load;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class FoodLoader {
    @Load
    public void loadFoods(FMLPreInitializationEvent event) {
        FoodManager foodManager = new FoodManager(event.getModConfigurationDirectory().toPath());
        foodManager.load();
        System.out.println(foodManager.materialMap);
        System.out.println(foodManager.foodMap);
        System.out.println("Loaded");
    }
}
