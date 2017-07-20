package net.infstudio.foodcraftreloaded.block;

import net.infstudio.foodcraftreloaded.item.food.VegetableType;
import net.infstudio.foodcraftreloaded.util.NameBuilder;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.FluidRegistry;

import javax.annotation.Nonnull;

public class BlockFluidVegetableJuice extends BlockFluidClassic {
    private VegetableType vegetableType;

    public BlockFluidVegetableJuice(VegetableType vegetableType) {
        super(FluidRegistry.getFluid(NameBuilder.buildRegistryName(vegetableType.toString(), "juice")), Material.WATER);
        this.vegetableType = vegetableType;
        setUnlocalizedName(NameBuilder.buildUnlocalizedName(vegetableType.toString(), "juice"));
        setRegistryName(NameBuilder.buildRegistryName(vegetableType.toString(), "juice"));
    }

    public VegetableType getVegetableType() {
        return vegetableType;
    }

    @Nonnull
    @Override
    public String getLocalizedName() {
        return I18n.format("item.vegetableJuice", net.minecraft.client.resources.I18n.format(NameBuilder.buildRegistryName("item.vegetable", vegetableType.toString())));
    }
}
