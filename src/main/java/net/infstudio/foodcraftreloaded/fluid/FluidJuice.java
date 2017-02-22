package net.infstudio.foodcraftreloaded.fluid;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.item.food.EnumFruitType;
import net.infstudio.foodcraftreloaded.utils.NameBuilder;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidJuice extends Fluid {
    private EnumFruitType fruitType;

    public FluidJuice(EnumFruitType fruitType) {
        super(NameBuilder.buildRegistryName(fruitType.toString(), "juice"), new ResourceLocation(FoodCraftReloaded.MODID, "juice_still"),  new ResourceLocation(FoodCraftReloaded.MODID, "juice_flow"));
        this.fruitType = fruitType;
    }

    @Override
    public int getColor() {
        return fruitType.getColor().getRGB();
    }
}
