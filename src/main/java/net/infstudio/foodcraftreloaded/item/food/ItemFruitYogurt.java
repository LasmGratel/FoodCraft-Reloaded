package net.infstudio.foodcraftreloaded.item.food;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.init.FCRItems;
import net.infstudio.foodcraftreloaded.util.NameBuilder;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemFruitYogurt extends FCRItemFood {
    private FruitType type;

    public ItemFruitYogurt(FruitType type) {
        super(6, false);
        setRegistryName(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName(type.toString(), "yogurt"));
        setMaxDamage(0);
        setHasSubtypes(false);
        this.type = type;
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

    @Nonnull
    @Override
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        return I18n.format("item.fruitYogurt", I18n.format("item.fruit" + StringUtils.capitalize(type.toString())));
    }
}
