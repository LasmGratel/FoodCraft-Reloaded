package net.infstudio.foodcraftreloaded.block;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.block.tileentity.TileEntityDrinkMachine;
import net.infstudio.foodcraftreloaded.init.FCRBlocks;
import net.infstudio.foodcraftreloaded.utils.NameBuilder;
import net.infstudio.foodcraftreloaded.utils.loader.annotation.Load;
import net.infstudio.foodcraftreloaded.utils.loader.annotation.RegBlock;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;

public class BlockLoader {
    @Load
    public void registerBlocks() {
        for (Field field : FCRBlocks.class.getFields()) {
            field.setAccessible(true);
            RegBlock anno = field.getAnnotation(RegBlock.class);
            if (anno==null) continue;

            try {
                Block block = (Block) field.get(null);
                GameRegistry.register(block.setRegistryName(NameBuilder.buildRegistryName(anno.value())).setUnlocalizedName(NameBuilder.buildUnlocalizedName(anno.value())));

                //Register item block.
                Class<? extends ItemBlock> itemClass = anno.itemClass();
                Constructor<? extends ItemBlock> con = itemClass.getConstructor(Block.class);
                con.setAccessible(true);
                GameRegistry.register(con.newInstance(block).setRegistryName(block.getRegistryName()).setUnlocalizedName(block.getUnlocalizedName()));

                Arrays.asList(anno.oreDict()).forEach(s -> OreDictionary.registerOre(s, block));
            } catch (Exception e) {
                FoodCraftReloaded.getLogger().warn("Un-able to register block " + field.toGenericString(), e);
            }
        }
    }

    @Load
    public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityDrinkMachine.class, "drink_machine");
    }

    @Load(side = Side.CLIENT)
    public void registerRenders() {
        for (Field field : FCRBlocks.class.getFields()) {
            field.setAccessible(true);
            RegBlock anno = field.getAnnotation(RegBlock.class);
            if (anno==null) continue;

            try {
                Block block = (Block) field.get(null);
                registerRender(block,0);
            } catch (Exception e) {
                FoodCraftReloaded.getLogger().warn("Un-able to register block " + field.toGenericString(), e);
            }
        }
    }

    private void registerRender(Block block, int meta)
    {
        Item item = Item.getItemFromBlock(block);
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }
}
