package cc.lasmgratel.foodcraftreloaded.minecraft.common.item.food;

import cc.lasmgratel.foodcraftreloaded.api.food.material.Material;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.item.ItemBase;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.util.NameBuilder;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.util.Translator;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class MinecraftMaterialWrapper extends ItemBase {
    private Material material;

    public MinecraftMaterialWrapper(Material material) {
        this.material = material;
        setRegistryName(material.getName());
        setTranslationKey(NameBuilder.buildUnlocalizedName(material.getName()));
    }

    public Material getMaterial() {
        return material;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(Translator.format("tooltip.fcr.energy", getMaterial().getEnergy()));
    }
}
