package net.infstudio.foodcraftreloaded.item.food;

import net.infstudio.foodcraftreloaded.init.FCRCreativeTabs;
import net.infstudio.foodcraftreloaded.init.FCRItems;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
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
import java.util.List;

public class ItemLiqueur extends FCRItemFood {
    private LiqueurType type = LiqueurType.NORMAL;

    public ItemLiqueur(int amount) {
        super(amount, false);
        setCreativeTab(FCRCreativeTabs.DRINK);
    }

    public LiqueurType getType() {
        return type;
    }

    public ItemLiqueur setType(LiqueurType type) {
        this.type = type;
        return this;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add(I18n.format("tooltip.fcrfood.liqueur.type", I18n.format("tooltip.fcrfood.liqueur.type." + type.ordinal())));
    }

    @Nonnull
    public EnumAction getItemUseAction(@Nonnull ItemStack stack) {
        return EnumAction.DRINK;
    }

    @Nonnull
    @Override
    public ItemStack onItemUseFinish(ItemStack stack, @Nullable World worldIn, EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            entityplayer.getFoodStats().addStats(this, stack);
            worldIn.playSound(entityplayer, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            this.onFoodEaten(stack, worldIn, entityplayer);
            entityplayer.addStat(StatList.getObjectUseStats(this));
        }
        return new ItemStack(FCRItems.GLASS_BOTTLE);
    }
}
