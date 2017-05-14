package net.infstudio.foodcraftreloaded.init;

import net.infstudio.foodcraftreloaded.block.BlockRiceCrop;
import net.infstudio.foodcraftreloaded.block.machine.BlockDrinkMachine;
import net.infstudio.foodcraftreloaded.block.machine.BlockPressureCooker;
import net.infstudio.foodcraftreloaded.util.loader.annotation.RegBlock;

public interface FCRBlocks {
    @RegBlock({"rice", "plant"})
    BlockRiceCrop RICE_PLANT = new BlockRiceCrop();

    @RegBlock({"drink", "machine"})
    BlockDrinkMachine DRINK_MACHINE = new BlockDrinkMachine();

    @RegBlock({"pressure", "cooker"})
    BlockPressureCooker PRESSURE_COOKER = new BlockPressureCooker();
}
