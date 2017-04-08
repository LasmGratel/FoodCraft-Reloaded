package net.infstudio.foodcraftreloaded.block;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.item.food.EnumFruitType;
import net.infstudio.foodcraftreloaded.utils.NameBuilder;
import net.minecraft.block.BlockSapling;

public class BlockFruitSapling extends BlockSapling {
    private EnumFruitType fruitType;

    public BlockFruitSapling(EnumFruitType fruitType) {
        this.fruitType = fruitType;
        setRegistryName(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName(fruitType.toString(), "sapling"));
        setUnlocalizedName(NameBuilder.buildUnlocalizedName(fruitType.toString(), "sapling"));
    }

    public EnumFruitType getFruitType() {
        return fruitType;
    }
}
