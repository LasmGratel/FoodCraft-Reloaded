package net.infstudio.foodcraftreloaded.block;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.common.FruitLoader;
import net.infstudio.foodcraftreloaded.item.food.EnumFruitType;
import net.infstudio.foodcraftreloaded.utils.NameBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class BlockFruitLeaves extends Block implements IBlockColor {
    private EnumFruitType fruitType;

    public BlockFruitLeaves(EnumFruitType fruitType) {
        super(Material.LEAVES);
        this.fruitType = fruitType;
        setRegistryName(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName(fruitType.toString(), "leaves"));
    }

    public EnumFruitType getFruitType() {
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
    public int damageDropped(IBlockState state) {
        return fruitType.ordinal();
    }

    @Override
    public int colorMultiplier(@Nonnull IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) {
        if (tintIndex == 0)
            return BiomeColorHelper.getFoliageColorAtPos(worldIn, pos);
        else if (tintIndex == 1)
            return ((BlockFruitLeaves) state.getBlock()).fruitType.getColor().getRGB();
        else
            return -1;
    }
}
