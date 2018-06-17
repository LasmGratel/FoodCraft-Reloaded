package cc.lasmgratel.foodcraftreloaded.minecraft.common.loader;

import cc.lasmgratel.foodcraftreloaded.common.material.MaterialBase;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.item.food.MinecraftMaterialWrapper;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.loader.register.RegisterManager;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.util.loader.annotation.Load;

public class FoodLoader {
    @Load
    public void loadFoods() {
        MaterialBase material = new MaterialBase();
        material.setEnergy(1047564);
        material.setName("garlic_material");
        RegisterManager.getInstance().putRegister(new MinecraftMaterialWrapper(material));
    }
}
