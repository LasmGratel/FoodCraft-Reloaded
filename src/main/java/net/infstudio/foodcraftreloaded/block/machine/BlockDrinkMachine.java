package net.infstudio.foodcraftreloaded.block.machine;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.block.tileentity.TileEntityDrinkMachine;
import net.infstudio.foodcraftreloaded.client.GuiID;
import net.infstudio.foodcraftreloaded.init.FCRCreativeTabs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockDrinkMachine extends BlockMachine {
    public BlockDrinkMachine() {
        setCreativeTab(FCRCreativeTabs.BASE);
        setHardness(2.5f);
        setHarvestLevel("pickaxe", 0);
        setLightLevel(1.0f);
    }

    @Override
    public void breakBlock(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
        worldIn.removeTileEntity(pos);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.getTileEntity(pos) == null)
            createNewTileEntity(worldIn, getMetaFromState(state));
        playerIn.openGui(FoodCraftReloaded.INSTANCE, GuiID.DRINK_MACHINE, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Nonnull
    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, @Nonnull IBlockState state) {
        return new ItemStack(new BlockDrinkMachine());
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(@Nonnull World worldIn, int meta) {
        TileEntityDrinkMachine drinkMachine = new TileEntityDrinkMachine();
        FMLCommonHandler.instance().getMinecraftServerInstance().registerTickable(drinkMachine);
        return drinkMachine;
    }
}
