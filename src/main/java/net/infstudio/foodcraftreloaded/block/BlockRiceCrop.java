package net.infstudio.foodcraftreloaded.block;

import net.infstudio.foodcraftreloaded.init.FCRItems;
import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;

public class BlockRiceCrop extends BlockCrops {
    public BlockRiceCrop() {
        super();
    }

    @Override
    protected Item getSeed() {
        return FCRItems.RICE;
    }

    @Override
    protected Item getCrop() {
        return FCRItems.RICE;
    }
}
