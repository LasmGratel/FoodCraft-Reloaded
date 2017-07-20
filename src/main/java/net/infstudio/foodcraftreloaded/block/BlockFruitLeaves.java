package net.infstudio.foodcraftreloaded.block;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.common.FruitLoader;
import net.infstudio.foodcraftreloaded.item.food.FruitType;
import net.infstudio.foodcraftreloaded.util.NameBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BlockFruitLeaves extends Block implements IShearable {
    private FruitType fruitType;

    public BlockFruitLeaves(FruitType fruitType) {
        super(Material.LEAVES);
        this.setTickRandomly(true);
        this.fruitType = fruitType;
        this.setHardness(0.2F);
        this.setLightOpacity(1);
        setRegistryName(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName(fruitType.toString(), "leaves"));
    }

    public FruitType getFruitType() {
        return fruitType;
    }

    public int quantityDroppedWithBonus(int fortune, @Nonnull Random random) {
        return MathHelper.clamp(this.quantityDropped(random) + random.nextInt(fortune + 1), 1, 4);
    }

    @Override
    public int quantityDropped(Random random) {
        return 2 + random.nextInt(3);
    }

    @Override
    @Nonnull
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return FoodCraftReloaded.getProxy().getLoaderManager().getLoader(FruitLoader.class).get().getFruits();
    }

    @Override
    @Nonnull
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public int damageDropped(IBlockState state) {
        return fruitType.ordinal();
    }

    @Override
    public boolean isLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
        return true;
    }

    @Override
    public boolean isShearable(@Nonnull ItemStack item, IBlockAccess world, BlockPos pos) {
        return true;
    }

    @Override
    public List<ItemStack> onSheared(@Nonnull ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        return Collections.singletonList(new ItemStack(FoodCraftReloaded.getProxy().getLoaderManager().getLoader(FruitLoader.class).get().getFruits(), fruitType.ordinal(), MathHelper.ceil(quantityDroppedWithBonus(fortune, ((World) world).rand) * 1.5)));
    }
}
