package net.infstudio.foodcraftreloaded.block;

import net.infstudio.foodcraftreloaded.item.food.FruitType;
import net.infstudio.foodcraftreloaded.util.NameBuilder;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.FluidRegistry;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;

public class BlockFluidJuice extends BlockFluidClassic {
    private FruitType fruitType;

    public BlockFluidJuice(FruitType fruitType) {
        super(FluidRegistry.getFluid(NameBuilder.buildRegistryName(fruitType.toString(), "juice")), Material.WATER);
        this.fruitType = fruitType;
        setUnlocalizedName(NameBuilder.buildUnlocalizedName(fruitType.toString(), "juice"));
        setRegistryName(NameBuilder.buildRegistryName(fruitType.toString(), "juice"));
    }

    public FruitType getFruitType() {
        return fruitType;
    }

    @Nonnull
    @Override
    public String getLocalizedName() {
        return I18n.format("item.juice", net.minecraft.client.resources.I18n.format("item.fruit" + StringUtils.capitalize(fruitType.toString())));
    }
}
