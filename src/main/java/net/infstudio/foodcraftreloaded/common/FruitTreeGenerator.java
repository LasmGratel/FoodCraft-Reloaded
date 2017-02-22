package net.infstudio.foodcraftreloaded.common;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.block.BlockFruitLeaves;
import net.infstudio.foodcraftreloaded.block.BlockFruitSapling;
import net.infstudio.foodcraftreloaded.item.food.EnumFruitType;
import net.infstudio.foodcraftreloaded.worldgen.WorldGenTree;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.SaplingGrowTreeEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Map;

public class FruitTreeGenerator {
    private WorldGenTree worldGenTree = new WorldGenTree();

    public FruitTreeGenerator() {
        MinecraftForge.TERRAIN_GEN_BUS.register(this);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onSaplingGrowEvent(SaplingGrowTreeEvent event) {
        if (event.getWorld().getBlockState(event.getPos()).getBlock() instanceof BlockFruitSapling) {
            event.getWorld().setBlockToAir(event.getPos());
            EnumFruitType fruitType = ((BlockFruitSapling) event.getWorld().getBlockState(event.getPos()).getBlock()).getFruitType();
            //noinspection OptionalGetWithoutIsPresent
            BlockFruitLeaves leaves = FoodCraftReloaded.getProxy().getLoaderManager().getLoader(FruitLoader.class).get().getLeavesMap().get(fruitType);
            IBlockState jungleLeave = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.JUNGLE);
            worldGenTree.setTreeLeaves(jungleLeave);
            worldGenTree.setTreeLog(Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE));
            worldGenTree.generateMap(event.getWorld(), event.getRand(), event.getPos());
            Map<BlockPos, IBlockState> map = worldGenTree.getGeneratedTreeMap();
            map.replaceAll(((pos, state) -> state == jungleLeave && event.getRand().nextInt(3) == 0 ? leaves.getDefaultState() : state));
            map.forEach((pos1, state) -> WorldGenTree.setBlockStateWithCheck(event.getWorld(), pos1, state));
            event.setResult(Event.Result.DENY);
        }
    }

}
