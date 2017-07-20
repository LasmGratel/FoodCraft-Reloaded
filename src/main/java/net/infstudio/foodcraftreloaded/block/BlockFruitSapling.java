package net.infstudio.foodcraftreloaded.block;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.item.food.FruitType;
import net.infstudio.foodcraftreloaded.util.NameBuilder;
import net.minecraft.block.BlockSapling;

public class BlockFruitSapling extends BlockSapling {
    private FruitType fruitType;

    public BlockFruitSapling(FruitType fruitType) {
        this.fruitType = fruitType;
        setRegistryName(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName(fruitType.toString(), "sapling"));
        setUnlocalizedName(NameBuilder.buildUnlocalizedName(fruitType.toString(), "sapling"));
    }

    public FruitType getFruitType() {
        return fruitType;
    }
}
