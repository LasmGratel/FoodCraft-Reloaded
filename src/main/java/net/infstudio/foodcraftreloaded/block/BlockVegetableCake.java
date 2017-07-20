package net.infstudio.foodcraftreloaded.block;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.item.food.VegetableType;
import net.infstudio.foodcraftreloaded.util.NameBuilder;
import net.minecraft.block.BlockCake;

public class BlockVegetableCake extends BlockCake {
    private VegetableType vegetableType;

    public BlockVegetableCake(VegetableType vegetableType) {
        super();
        setRegistryName(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName(vegetableType.toString(), "cake"));
        this.vegetableType = vegetableType;
    }

    public VegetableType getVegetableType() {
        return vegetableType;
    }

    public void setVegetableType(VegetableType fruitType) {
        this.vegetableType = fruitType;
    }
}
