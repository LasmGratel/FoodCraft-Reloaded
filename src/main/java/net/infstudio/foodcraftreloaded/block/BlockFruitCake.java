package net.infstudio.foodcraftreloaded.block;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.item.food.EnumFruitType;
import net.infstudio.foodcraftreloaded.util.NameBuilder;
import net.minecraft.block.BlockCake;

public class BlockFruitCake extends BlockCake {
    private EnumFruitType fruitType;

    public BlockFruitCake(EnumFruitType fruitType) {
        super();
        setRegistryName(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName(fruitType.toString(), "cake"));
        this.fruitType = fruitType;
    }

    public EnumFruitType getFruitType() {
        return fruitType;
    }

    public void setFruitType(EnumFruitType fruitType) {
        this.fruitType = fruitType;
    }
}
