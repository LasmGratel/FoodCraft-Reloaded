package net.infstudio.foodcraftreloaded.init;

import net.infstudio.foodcraftreloaded.block.BlockRiceCrop;
import net.infstudio.foodcraftreloaded.utils.loader.annotation.RegBlock;

public interface FCRBlocks {
    @RegBlock({"rice", "plant"})
    BlockRiceCrop RICE_PLANT = new BlockRiceCrop();
}
