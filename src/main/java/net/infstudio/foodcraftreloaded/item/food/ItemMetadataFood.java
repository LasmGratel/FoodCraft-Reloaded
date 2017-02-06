package net.infstudio.foodcraftreloaded.item.food;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.item.IMetadatable;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class ItemMetadataFood extends FCRItemFood implements IMetadatable {
    private Map<Integer, FCRItemFood> foodMap = new HashMap<>();

    public ItemMetadataFood() {
        super(0, 0.0f, true);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    public Map<Integer, FCRItemFood> getFoodMap() {
        return foodMap;
    }

    public void setFoodMap(Map<Integer, FCRItemFood> foodMap) {
        this.foodMap = foodMap;
    }

    public FCRItemFood put(Integer key, FCRItemFood value) throws RuntimeException {
        return foodMap.put(key, value);
    }

    public void register() {
        foodMap.values().forEach(GameRegistry::register);
    }

    @SideOnly(Side.CLIENT)
    public void registerRender() {
        ModelLoader.setCustomMeshDefinition(this, (stack) -> new ModelResourceLocation(FoodCraftReloaded.MODID + ":" + getUnlocalizedName(stack), "inventory"));
    }

    @Override
    public int getHealAmount(ItemStack stack) {
        return foodMap.get(stack.getMetadata()).getHealAmount(stack);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return foodMap.get(stack.getMetadata()).getMaxItemUseDuration(stack);
    }

    @Override
    public float getSaturationModifier(ItemStack stack) {
        return foodMap.get(stack.getMetadata()).getSaturationModifier(stack);
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, @Nonnull EntityPlayer player) {
        FCRItemFood food = foodMap.get(stack.getMetadata());
        if (!worldIn.isRemote && food.getPotionId() != null && worldIn.rand.nextFloat() < food.getPotionEffectProbability())
        {
            player.addPotionEffect(new PotionEffect(food.getPotionId()));
        }
    }

    @Nonnull
    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return foodMap.get(stack.getMetadata()).getUnlocalizedName();
    }

    @Override
    public int getMaxMetadata() {
        return foodMap.size();
    }
}
