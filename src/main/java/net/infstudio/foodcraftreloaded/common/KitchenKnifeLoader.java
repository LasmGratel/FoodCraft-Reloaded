package net.infstudio.foodcraftreloaded.common;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.item.tool.EnumKitchenKnifeType;
import net.infstudio.foodcraftreloaded.item.tool.ItemKitchenKnife;
import net.infstudio.foodcraftreloaded.util.loader.annotation.Load;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class KitchenKnifeLoader {
    private Map<EnumKitchenKnifeType, ItemKitchenKnife> kitchenKnifeMap = new EnumMap<>(EnumKitchenKnifeType.class);

    @Load
    public void loadKitchenKnifes() {
        for (EnumKitchenKnifeType knifeType : EnumKitchenKnifeType.values())
            kitchenKnifeMap.put(knifeType, new ItemKitchenKnife(knifeType));
        kitchenKnifeMap.forEach((type, item) -> ForgeRegistries.ITEMS.register(item));
    }

    @Load(side = Side.CLIENT)
    public void loadKitchenKnifeRender() {
        kitchenKnifeMap.forEach((type, item) -> ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "kitchen_knife"), "inventory")));
    }

    @Load(value = LoaderState.POSTINITIALIZATION, side = Side.CLIENT)
    public void loadKitchenKnifeColor() {
        kitchenKnifeMap.forEach((type, item) -> Minecraft.getMinecraft().getItemColors().registerItemColorHandler(item, item));
    }

    public ItemKitchenKnife get(EnumKitchenKnifeType key) {
        return kitchenKnifeMap.get(key);
    }

    public ItemKitchenKnife put(EnumKitchenKnifeType key, ItemKitchenKnife value) {
        return kitchenKnifeMap.put(key, value);
    }

    public void forEach(BiConsumer<? super EnumKitchenKnifeType, ? super ItemKitchenKnife> action) {
        kitchenKnifeMap.forEach(action);
    }

    public Map<EnumKitchenKnifeType, ItemKitchenKnife> getKitchenKnifeMap() {
        return kitchenKnifeMap;
    }
}
