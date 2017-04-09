package net.infstudio.foodcraftreloaded.worldgen;

import biomesoplenty.common.world.generator.tree.GeneratorBasicTree;
import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.block.BlockFruitSapling;
import net.infstudio.foodcraftreloaded.common.FruitLoader;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.SaplingGrowTreeEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FruitTreeGenerator {
    public FruitTreeGenerator() {
        MinecraftForge.TERRAIN_GEN_BUS.register(this);
    }

    @SubscribeEvent
    public void generateFruitTree(DecorateBiomeEvent.Decorate event) {
        Biome biome = event.getWorld().getBiome(event.getPos());
        if (event.getType() == DecorateBiomeEvent.Decorate.EventType.TREE && (biome.getTempCategory() == Biome.TempCategory.MEDIUM || biome.getTempCategory() == Biome.TempCategory.WARM)) {
            GeneratorBasicTree[] generatorBasicTrees = FoodCraftReloaded.getProxy().getLoaderManager().getLoader(FruitLoader.class).get().getGeneratorTreeMap().values().toArray(new GeneratorBasicTree[0]);
            GeneratorBasicTree generator = generatorBasicTrees[MathHelper.getInt(event.getRand(),0, generatorBasicTrees.length - 1)];
            if (generator.generate(event.getWorld(), event.getRand(), event.getPos())) {
                FoodCraftReloaded.getLogger().debug("[FoodCraft]Generated fruit tree " + generator.getIdentifier() +" at " + event.getPos());
            }
        }
    }

    @SubscribeEvent
    public void fruitTreeGrow(SaplingGrowTreeEvent event) {
        if (event.getWorld().getBlockState(event.getPos()).getBlock() instanceof BlockFruitSapling) {
            BlockFruitSapling sapling = (BlockFruitSapling) event.getWorld().getBlockState(event.getPos()).getBlock();
            FoodCraftReloaded.getProxy().getLoaderManager().getLoader(FruitLoader.class).get().getGeneratorTreeMap().get(sapling.getFruitType()).generate(event.getWorld(), event.getRand(), event.getPos());
        }
        event.setResult(Event.Result.DENY);
    }
}
