/*
 * FoodCraft Mod - Add more food to your Minecraft.
 * Copyright (C) 2017 Lasm Gratel
 *
 * This file is part of FoodCraft Mod.
 *
 * FoodCraft Mod is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FoodCraft Mod is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with FoodCraft Mod.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.infstudio.foodcraftreloaded.worldgen;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.block.BlockFruitSapling;
import net.infstudio.foodcraftreloaded.common.FruitLoader;
import net.minecraft.util.math.MathHelper;
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
        if (event.getType() == DecorateBiomeEvent.Decorate.EventType.TREE) {
            BaseTreeGenerator[] generatorBasicTrees = FoodCraftReloaded.getProxy().getLoaderManager().getLoader(FruitLoader.class).get().getGeneratorTreeMap().values().toArray(new BaseTreeGenerator[0]);
            BaseTreeGenerator generator = generatorBasicTrees[MathHelper.getInt(event.getRand(),0, generatorBasicTrees.length - 1)];
            if (generator.generate(event.getWorld(), event.getRand(), event.getPos())) {
                FoodCraftReloaded.getLogger().info("Generated fruit tree at " + event.getPos());
                event.setResult(Event.Result.DENY);
            }
        }
    }

    @SubscribeEvent
    public void fruitTreeGrow(SaplingGrowTreeEvent event) {
        if (event.getWorld().getBlockState(event.getPos()).getBlock() instanceof BlockFruitSapling) {
            event.setCanceled(true);
            BlockFruitSapling sapling = (BlockFruitSapling) event.getWorld().getBlockState(event.getPos()).getBlock();
            FoodCraftReloaded.getProxy().getLoaderManager().getLoader(FruitLoader.class).get().getGeneratorTreeMap().get(sapling.getFruitType()).generate(event.getWorld(), event.getRand(), event.getPos());
        }
        event.setResult(Event.Result.DENY);
    }
}
