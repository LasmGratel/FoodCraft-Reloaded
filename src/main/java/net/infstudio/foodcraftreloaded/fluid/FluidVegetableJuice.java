package net.infstudio.foodcraftreloaded.fluid;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.item.food.VegetableType;
import net.infstudio.foodcraftreloaded.util.NameBuilder;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidVegetableJuice extends Fluid {
    private VegetableType fruitType;

    public FluidVegetableJuice(VegetableType vegetableType) {
        super(NameBuilder.buildRegistryName(vegetableType.toString(), "juice"), new ResourceLocation(FoodCraftReloaded.MODID, "fluids/juice_still"),  new ResourceLocation(FoodCraftReloaded.MODID, "fluids/juice_flow"));
        this.fruitType = vegetableType;
        setViscosity(2000);

    }

    @Override
    public int getColor() {
        return fruitType.getColor().getRGB();
    }
}
