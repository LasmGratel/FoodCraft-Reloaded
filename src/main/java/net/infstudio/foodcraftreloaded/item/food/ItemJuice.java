package net.infstudio.foodcraftreloaded.item.food;

import net.infstudio.foodcraftreloaded.init.FCRCreativeTabs;
import net.infstudio.foodcraftreloaded.init.FCRItems;
import net.infstudio.foodcraftreloaded.utils.NameBuilder;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemJuice extends FCRItemFood implements IItemColor {
    private EnumFruitType fruitType;

    public ItemJuice(EnumFruitType fruitType) {
        super(4, 1.0f, false);
        this.fruitType = fruitType;
        setUnlocalizedName(NameBuilder.buildUnlocalizedName("juice", fruitType.toString()));
        setRegistryName(NameBuilder.buildRegistryName("juice", fruitType.toString()));
        setCreativeTab(FCRCreativeTabs.DRINK);
    }

    @Nonnull
    @Override
    public ItemStack onItemUseFinish(ItemStack stack, @Nullable World worldIn, EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            entityplayer.getFoodStats().addStats(this, stack);
            worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            this.onFoodEaten(stack, worldIn, entityplayer);
            entityplayer.addStat(StatList.getObjectUseStats(this));
        }
        return new ItemStack(FCRItems.GLASS_BOTTLE);
    }

    @Nonnull
    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.DRINK;
    }

    public EnumFruitType getFruitType() {
        return fruitType;
    }

    @Override
    public int getColorFromItemstack(@Nonnull ItemStack stack, int tintIndex) {
        if (tintIndex == 1 && stack.getItem() instanceof ItemJuice)
            return ((ItemJuice) stack.getItem()).getFruitType().getColor().getRGB();
        return 0;
    }
}
