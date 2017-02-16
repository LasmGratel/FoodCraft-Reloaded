package net.infstudio.foodcraftreloaded.item.food;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.init.FCRCreativeTabs;
import net.infstudio.foodcraftreloaded.init.FCRItems;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemSodas extends FCRItemFood {
    public ItemSodas() {
        super(5, 1.0f, false);
        setRegistryName(FoodCraftReloaded.MODID, "soda");
        setCreativeTab(FCRCreativeTabs.DRINK);
        setMaxDamage(0);
        setHasSubtypes(true);
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

    @Override
    public void getSubItems(@Nonnull Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        for (EnumFruitType fruitType : EnumFruitType.values())
            subItems.add(new ItemStack(itemIn, 1, fruitType.ordinal()));
    }

    @Nonnull
    @Override
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        return I18n.format("item.soda", I18n.format("item.fruit" + StringUtils.capitalize(EnumFruitType.values()[stack.getMetadata()].toString())));
    }
}
