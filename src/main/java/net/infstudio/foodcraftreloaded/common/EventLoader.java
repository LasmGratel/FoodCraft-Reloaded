package net.infstudio.foodcraftreloaded.common;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.init.FCRBlocks;
import net.infstudio.foodcraftreloaded.init.FCRItems;
import net.infstudio.foodcraftreloaded.item.food.FruitType;
import net.infstudio.foodcraftreloaded.item.food.ItemCakes;
import net.infstudio.foodcraftreloaded.item.food.ItemVegetableCakes;
import net.infstudio.foodcraftreloaded.util.loader.annotation.Load;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import org.apache.commons.lang3.StringUtils;

public class EventLoader {

    @Load(value = LoaderState.INITIALIZATION)
    public void register() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onNeighborNotify(BlockEvent.NeighborNotifyEvent event) {
        if (event.getWorld().getBlockState(event.getPos().up()).getBlock() == FCRBlocks.RICE_PLANT &&
            event.getState().getBlock() != Blocks.FARMLAND)
            event.getWorld().destroyBlock(event.getPos().up(), true);
    }

    @SubscribeEvent
    public void onRegister(RegistryEvent.Register<IRecipe> event) {
        FruitLoader loader = FoodCraftReloaded.getProxy().getLoaderManager().getLoader(FruitLoader.class).get();
        for (FruitType fruitType : FruitType.values()) {
            event.getRegistry().register(new ShapedOreRecipe(new ResourceLocation("food"), new ItemStack(loader.getSaplingMap().get(fruitType)), " F ", "FXF", " F ", 'F', "crop" + StringUtils.capitalize(fruitType.toString()), 'X', "treeSapling").setRegistryName("fruit_sapling"));
            event.getRegistry().register(new ShapelessOreRecipe(new ResourceLocation("food"), new ItemStack(loader.getIcecreams(), 1, fruitType.ordinal()), "food" + StringUtils.capitalize(fruitType.toString()) + "juice", "foodIcecream").setRegistryName("fruit_icecream"));
        }
    }

    @SubscribeEvent
    public void onCraftingCake(PlayerEvent.ItemCraftedEvent event) {
        if (event.crafting.getItem() instanceof ItemCakes || event.crafting.getItem() instanceof ItemVegetableCakes)
            event.craftMatrix.setInventorySlotContents(1, new ItemStack(FCRItems.GLASS_BOTTLE));
    }
}
