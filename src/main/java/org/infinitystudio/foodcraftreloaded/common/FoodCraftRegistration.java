/**
 * FoodCraft Mod for Minecraft.
 * Copyright (C) 2016 Infinity Studio.
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @license GPLv3
 */
package org.infinitystudio.foodcraftreloaded.common;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import org.infinitystudio.foodcraftreloaded.block.BaseBlock;
import org.infinitystudio.foodcraftreloaded.block.BlockCropBean;
import org.infinitystudio.foodcraftreloaded.block.BlockCropVegetable;
import org.infinitystudio.foodcraftreloaded.block.machine.BlockGlassCup;
import org.infinitystudio.foodcraftreloaded.fluid.FluidJuice;
import org.infinitystudio.foodcraftreloaded.item.food.*;
import org.infinitystudio.foodcraftreloaded.tileentity.GlassCupTileEntity;
import org.infinitystudio.foodcraftreloaded.utils.modmanagent.common.*;

public class FoodCraftRegistration {
    /**
     * 基础 Base
     */
    public static final CreativeTabs FcTabBase = new CreativeTabs("foodcraftreloaded.base") {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(blockRice);
        }
    };

    /**
     * 植物&种子 Plants & Seeds
     */
    public static final CreativeTabs FcTabPlant = new CreativeTabs("foodcraftreloaded.plant") {
        @Override
        public Item getTabIconItem() {
            return itemBlackBean;
        }
    };

    /**
     * 饮品 Drinks
     */
    public static final CreativeTabs FcTabDrink = new CreativeTabs("foodcraftreloaded.drink") {
        @Override
        public Item getTabIconItem() {
            return itemFruitOrangeDrink;
        }
    };

    /**
     * 主食 Staple
     */
    public static final CreativeTabs FcTabStaple = new CreativeTabs("foodcraftreloaded.staple") {
        @Override
        public Item getTabIconItem() {
            return itemBlackBean;
        }
    };

    /**
     * 食材 Ingredient
     */
    public static final CreativeTabs FcTabIngredient = new CreativeTabs("foodcraftreloaded.ingredient") {
        @Override
        public Item getTabIconItem() {
            return itemBlackBean;
        }
    };

    /**
     * 小吃 Snack
     */
    public static final CreativeTabs FcTabSnack = new CreativeTabs("foodcraftreloaded.snack") {
        @Override
        public Item getTabIconItem() {
            return itemBlackBean;
        }
    };
    ////////////////////////////////////////////////////////////////
    // Material Registry
    // 材料注册
    ////////////////////////////////////////////////////////////////
    @ModMaterial(26)
    public static Material materialRiceMachine;

    @ModMaterial(21)
    public static Material materialAdvancedRiceMachine;

    @ModMaterial()
    public static Material materialJuiceFluid;

    ////////////////////////////////////////////////////////////////
    // Item Registry
    // 物品注册
    ////////////////////////////////////////////////////////////////

    // Vegetable Registry
    // 蔬菜注册
//    @ModItem(name = "itemCarrot")
    @ModVegetable(name = "itemCarrot", satuation = 2.5f, oredicts = {"cropCarrot", "seedCarrot", "listAllseed", "listAllveggie"}, seedBlockName = "blockCarrot")
    public static ItemVegetable carrot;

    // Bean Registry
    // 豆子注册
    @ModBean(type = ModBean.BeanType.Red)
    public static ItemBean itemRedBean;

    @ModBean(type = ModBean.BeanType.Black)
    public static ItemBean itemBlackBean;

    @ModBean(type = ModBean.BeanType.Green)
    public static ItemBean itemGreenBean;

    @ModBean(type = ModBean.BeanType.Soy)
    public static ItemBean itemSoyBean;

    @ModSprouts(type = ModBean.BeanType.Red)
    public static ItemSprouts itemRedBeanSprouts;

    @ModSprouts(type = ModBean.BeanType.Black)
    public static ItemSprouts itemBlackBeanSprouts;

    @ModSprouts(type = ModBean.BeanType.Green)
    public static ItemSprouts itemGreenBeanSprouts;

    @ModSprouts(type = ModBean.BeanType.Soy)
    public static ItemSprouts itemSoyBeanSprouts;

    // Food Registry
    // 食物注册

    // Fruit Registry
    // 水果注册
    @ModFruit(type = ModFruit.FruitType.Pear)
    public static ItemFruit itemFruitPear;

    @ModFruit(type = ModFruit.FruitType.Litchi)
    public static ItemFruit itemFruitLitchi;

    @ModFruit(type = ModFruit.FruitType.Peach)
    public static ItemFruit itemFruitPeach;

    @ModFruit(type = ModFruit.FruitType.Orange)
    public static ItemFruit itemFruitOrange;

    @ModFruit(type = ModFruit.FruitType.Mango)
    public static ItemFruit itemFruitMango;

    @ModFruit(type = ModFruit.FruitType.Lemon)
    public static ItemFruit itemFruitLemon;

    @ModFruit(type = ModFruit.FruitType.Grapefruit)
    public static ItemFruit itemFruitGrapefruit;

    @ModFruit(type = ModFruit.FruitType.Persimmon)
    public static ItemFruit itemFruitPersimmon;

    @ModFruit(type = ModFruit.FruitType.Papaya)
    public static ItemFruit itemFruitPapaya;

    @ModFruit(type = ModFruit.FruitType.Hawthorn)
    public static ItemFruit itemFruitHawthorn;

    @ModFruit(type = ModFruit.FruitType.Pomegranate)
    public static ItemFruit itemFruitPomegranate;

    @ModFruit(type = ModFruit.FruitType.Date)
    public static ItemFruit itemFruitDate;

    @ModFruit(type = ModFruit.FruitType.Cherry)
    public static ItemFruit itemFruitCherry;

    @ModFruit(type = ModFruit.FruitType.Coconut)
    public static ItemFruit itemFruitCoconut;

    @ModFruit(type = ModFruit.FruitType.Banana)
    public static ItemFruit itemFruitBanana;


    // Juice Registry
    // 果汁注册
    @ModJuice(type = ModFruit.FruitType.Pear)
    public static ItemJuice itemFruitPearDrink;

    @ModJuice(type = ModFruit.FruitType.Litchi)
    public static ItemJuice itemFruitLitchiDrink;

    @ModJuice(type = ModFruit.FruitType.Peach)
    public static ItemJuice itemFruitPeachDrink;

    @ModJuice(type = ModFruit.FruitType.Orange)
    public static ItemJuice itemFruitOrangeDrink;

    @ModJuice(type = ModFruit.FruitType.Mango)
    public static ItemJuice itemFruitMangoDrink;

    @ModJuice(type = ModFruit.FruitType.Lemon)
    public static ItemJuice itemFruitLemonDrink;

    @ModJuice(type = ModFruit.FruitType.Grapefruit)
    public static ItemJuice itemFruitGrapefruitDrink;

    @ModJuice(type = ModFruit.FruitType.Persimmon)
    public static ItemJuice itemFruitPersimmonDrink;

    @ModJuice(type = ModFruit.FruitType.Papaya)
    public static ItemJuice itemFruitPapayaDrink;

    @ModJuice(type = ModFruit.FruitType.Hawthorn)
    public static ItemJuice itemFruitHawthornDrink;

    @ModJuice(type = ModFruit.FruitType.Pomegranate)
    public static ItemJuice itemFruitPomegranateDrink;

    @ModJuice(type = ModFruit.FruitType.Date)
    public static ItemJuice itemFruitDateDrink;

    @ModJuice(type = ModFruit.FruitType.Cherry)
    public static ItemJuice itemFruitCherryDrink;

    @ModJuice(type = ModFruit.FruitType.Coconut)
    public static ItemJuice itemFruitCoconutDrink;

    @ModJuice(type = ModFruit.FruitType.Banana)
    public static ItemJuice itemFruitBananaDrink;

    // Ice-cream Registry
    // 冰激凌注册
    @ModFood(name = "itemBaseIceCream", satuation = 2.0f, oredicts = {"foodIcecream", "listAllicecream"})
    public static ItemFcFood baseIceCream;

    @ModIcecream(type = ModFruit.FruitType.Pear)
    public static ItemIcecream itemFruitPearIcecream;

    @ModIcecream(type = ModFruit.FruitType.Litchi)
    public static ItemIcecream itemFruitLitchiIcecream;

    @ModIcecream(type = ModFruit.FruitType.Peach)
    public static ItemIcecream itemFruitPeachIcecream;

    @ModIcecream(type = ModFruit.FruitType.Orange)
    public static ItemIcecream itemFruitOrangeIcecream;

    @ModIcecream(type = ModFruit.FruitType.Mango)
    public static ItemIcecream itemFruitMangoIcecream;

    @ModIcecream(type = ModFruit.FruitType.Lemon)
    public static ItemIcecream itemFruitLemonIcecream;

    @ModIcecream(type = ModFruit.FruitType.Grapefruit)
    public static ItemIcecream itemFruitGrapefruitIcecream;

    @ModIcecream(type = ModFruit.FruitType.Persimmon)
    public static ItemIcecream itemFruitPersimmonIcecream;

    @ModIcecream(type = ModFruit.FruitType.Papaya)
    public static ItemIcecream itemFruitPapayaIcecream;

    @ModIcecream(type = ModFruit.FruitType.Hawthorn)
    public static ItemIcecream itemFruitHawthornIcecream;

    @ModIcecream(type = ModFruit.FruitType.Pomegranate)
    public static ItemIcecream itemFruitPomegranateIcecream;

    @ModIcecream(type = ModFruit.FruitType.Date)
    public static ItemIcecream itemFruitDateIcecream;

    @ModIcecream(type = ModFruit.FruitType.Cherry)
    public static ItemIcecream itemFruitCherryIcecream;

    @ModIcecream(type = ModFruit.FruitType.Coconut)
    public static ItemIcecream itemFruitCoconutIcecream;

    @ModIcecream(type = ModFruit.FruitType.Banana)
    public static ItemIcecream itemFruitBananaIcecream;

    // Soda Registry
    // 汽水注册
    @ModSoda(type = ModFruit.FruitType.Pear)
    public static ItemSoda itemFruitPearSoda;

    @ModSoda(type = ModFruit.FruitType.Litchi)
    public static ItemSoda itemFruitLitchiSoda;

    @ModSoda(type = ModFruit.FruitType.Peach)
    public static ItemSoda itemFruitPeachSoda;

    @ModSoda(type = ModFruit.FruitType.Orange)
    public static ItemSoda itemFruitOrangeSoda;

    @ModSoda(type = ModFruit.FruitType.Mango)
    public static ItemSoda itemFruitMangoSoda;

    @ModSoda(type = ModFruit.FruitType.Lemon)
    public static ItemSoda itemFruitLemonSoda;

    @ModSoda(type = ModFruit.FruitType.Grapefruit)
    public static ItemSoda itemFruitGrapefruitSoda;

    @ModSoda(type = ModFruit.FruitType.Persimmon)
    public static ItemSoda itemFruitPersimmonSoda;

    @ModSoda(type = ModFruit.FruitType.Papaya)
    public static ItemSoda itemFruitPapayaSoda;

    @ModSoda(type = ModFruit.FruitType.Hawthorn)
    public static ItemSoda itemFruitHawthornSoda;

    @ModSoda(type = ModFruit.FruitType.Pomegranate)
    public static ItemSoda itemFruitPomegranateSoda;

    @ModSoda(type = ModFruit.FruitType.Date)
    public static ItemSoda itemFruitDateSoda;

    @ModSoda(type = ModFruit.FruitType.Cherry)
    public static ItemSoda itemFruitCherrySoda;

    @ModSoda(type = ModFruit.FruitType.Coconut)
    public static ItemSoda itemFruitCoconutSoda;

    @ModSoda(type = ModFruit.FruitType.Banana)
    public static ItemSoda itemFruitBananaSoda;

    // Meat Registry
    // 肉类注册

    // Fish Registry
    // 水产注册
    @ModFood(name = "itemMeatCrabRaw", oredicts = {"foodCrabraw"}, satuation = 2.0f)
    public static ItemCrab itemMeatCrabRaw = new ItemCrab(2.0f);

    @ModFood(name = "itemMeatShrimpRaw", oredicts = {"foodShrimpraw", "listAllfishraw"}, satuation = 2.0f)
    public static ItemShrimp itemMeatShrimpRaw = new ItemShrimp(2.0f);

    @ModFood(name = "itemMeatCrabCooked", oredicts = {"foodCrabcooked"}, satuation = 4.0f)
    public static ItemFcFood itemMeatCrabCooked;

    @ModFood(name = "itemMeatShrimpCooked", oredicts = {"foodShrimpcooked", "listAllfishcooked"}, satuation = 4.0f)
    public static ItemFcFood itemMeatShrimpCooked;

    ////////////////////////////////////////////////////////////////
    // TileEntity Registry
    // TileEntity注册
    ////////////////////////////////////////////////////////////////
    @ModTileEntity(id = "glassCupTileEntity", tileEntityClass = GlassCupTileEntity.class)
    public static GlassCupTileEntity glassCupTileEntity;
    
    ////////////////////////////////////////////////////////////////
    // Fluid Registry
    // 流体注册
    ////////////////////////////////////////////////////////////////
    @ModJuiceFluid(type = ModFruit.FruitType.Pear)
    public static FluidJuice fluidPearJuice;

    @ModJuiceFluid(type = ModFruit.FruitType.Litchi)
    public static FluidJuice fluidLitchiJuice;

    @ModJuiceFluid(type = ModFruit.FruitType.Peach)
    public static FluidJuice fluidPeachJuice;

    @ModJuiceFluid(type = ModFruit.FruitType.Orange)
    public static FluidJuice fluidOrangeJuice;

    @ModJuiceFluid(type = ModFruit.FruitType.Mango)
    public static FluidJuice fluidMangoJuice;

    @ModJuiceFluid(type = ModFruit.FruitType.Lemon)
    public static FluidJuice fluidLemonJuice;

    @ModJuiceFluid(type = ModFruit.FruitType.Grapefruit)
    public static FluidJuice fluidGrapefruitJuice;

    @ModJuiceFluid(type = ModFruit.FruitType.Persimmon)
    public static FluidJuice fluidPersimmonJuice;

    @ModJuiceFluid(type = ModFruit.FruitType.Papaya)
    public static FluidJuice fluidPapayaJuice;

    @ModJuiceFluid(type = ModFruit.FruitType.Hawthorn)
    public static FluidJuice fluidHawthornJuice;

    @ModJuiceFluid(type = ModFruit.FruitType.Pomegranate)
    public static FluidJuice fluidPomegranateJuice;

    @ModJuiceFluid(type = ModFruit.FruitType.Date)
    public static FluidJuice fluidDateJuice;

    @ModJuiceFluid(type = ModFruit.FruitType.Cherry)
    public static FluidJuice fluidCherryJuice;

    @ModJuiceFluid(type = ModFruit.FruitType.Coconut)
    public static FluidJuice fluidCoconutJuice;

    @ModJuiceFluid(type = ModFruit.FruitType.Banana)
    public static FluidJuice fluidBananaJuice;

    ////////////////////////////////////////////////////////////////
    // Block Registry
    // 方块注册
    ////////////////////////////////////////////////////////////////
    // Fluid Block Registry
    // 流体方块注册
//    @ModBlockJuiceFluid(type = ModFruit.FruitType.Pear)
//    public static BlockJuiceFluid blockFluidPearJuice = new BlockJuiceFluid(fluidPearJuice);
//
//    @ModBlockJuiceFluid(type = ModFruit.FruitType.Litchi)
//    public static BlockJuiceFluid blockFluidLitchiJuice = new BlockJuiceFluid(fluidLitchiJuice);
//
//    @ModBlockJuiceFluid(type = ModFruit.FruitType.Peach)
//    public static BlockJuiceFluid blockFluidPeachJuice = new BlockJuiceFluid(fluidPeachJuice);
//
//    @ModBlockJuiceFluid(type = ModFruit.FruitType.Orange)
//    public static BlockJuiceFluid blockFluidOrangeJuice = new BlockJuiceFluid(fluidOrangeJuice);
//
//    @ModBlockJuiceFluid(type = ModFruit.FruitType.Mango)
//    public static BlockJuiceFluid blockFluidMangoJuice = new BlockJuiceFluid(fluidMangoJuice);
//
//    @ModBlockJuiceFluid(type = ModFruit.FruitType.Lemon)
//    public static BlockJuiceFluid blockFluidLemonJuice = new BlockJuiceFluid(fluidLemonJuice);
//
//    @ModBlockJuiceFluid(type = ModFruit.FruitType.Grapefruit)
//    public static BlockJuiceFluid blockFluidGrapefruitJuice = new BlockJuiceFluid(fluidGrapefruitJuice);
//
//    @ModBlockJuiceFluid(type = ModFruit.FruitType.Persimmon)
//    public static BlockJuiceFluid blockFluidPersimmonJuice = new BlockJuiceFluid(fluidPersimmonJuice);
//
//    @ModBlockJuiceFluid(type = ModFruit.FruitType.Papaya)
//    public static BlockJuiceFluid blockFluidPapayaJuice = new BlockJuiceFluid(fluidPapayaJuice);
//
//    @ModBlockJuiceFluid(type = ModFruit.FruitType.Hawthorn)
//    public static BlockJuiceFluid blockFluidHawthornJuice = new BlockJuiceFluid(fluidHawthornJuice);
//
//    @ModBlockJuiceFluid(type = ModFruit.FruitType.Pomegranate)
//    public static BlockJuiceFluid blockFluidPomegranateJuice = new BlockJuiceFluid(fluidPomegranateJuice);
//
//    @ModBlockJuiceFluid(type = ModFruit.FruitType.Date)
//    public static BlockJuiceFluid blockFluidDateJuice = new BlockJuiceFluid(fluidDateJuice);
//
//    @ModBlockJuiceFluid(type = ModFruit.FruitType.Cherry)
//    public static BlockJuiceFluid blockFluidCherryJuice = new BlockJuiceFluid(fluidCherryJuice);
//
//    @ModBlockJuiceFluid(type = ModFruit.FruitType.Coconut)
//    public static BlockJuiceFluid blockFluidCoconutJuice = new BlockJuiceFluid(fluidCoconutJuice);
//
//    @ModBlockJuiceFluid(type = ModFruit.FruitType.Banana)
//    public static BlockJuiceFluid blockFluidBananaJuice = new BlockJuiceFluid(fluidBananaJuice);

    // Machine Registry
    // 机器注册
    @ModBlock(name = "blockGlassCup")
    public static BlockGlassCup blockGlassCup;

    // Tree Registry
    // 树木注册

    // Tree Leaves Registry
    // 树叶注册
//    @ModBlock(name = "blockFruitTreeLeaves")
//    public static BlockFruitTreeLeaves blockFruitTreeLeaves;

    // Tree Logs Registry
    // 树干注册
//    @ModBlock(name = "blockFruitTreeLog")
//    public static BlockFruitTree blockFruitTreeLog;

    // Tree Sapling Registry
    // 树苗注册
//    @ModBlock(name = "blockFruitTreeSapling")
//    public static BlockFruitTreeSapling blockFruitTreeSapling;

    // Food Block Registry
    // 食物方块注册(用于储存&合成)
    @ModBlock(name = "blockRice")
    public static BaseBlock blockRice;

    // Seed Block Registry
    // 种子方块注册
//    @ModBlock(name = "blockCarrot")
    @ModVegetableBlock(name = "blockCarrot", seedName = "itemCarrot", cropName = "itemCarrot")
    public static BlockCropVegetable blockCarrot;

    // Bean Block Registry
    // 豆子种子方块注册
    @ModBeanBlock(type = ModBean.BeanType.Red)
    public static BlockCropBean blockRedBean;

    @ModBeanBlock(type = ModBean.BeanType.Black)
    public static BlockCropBean blockBlackBean;

    @ModBeanBlock(type = ModBean.BeanType.Green)
    public static BlockCropBean blockGreenBean;

    @ModBeanBlock(type = ModBean.BeanType.Soy)
    public static BlockCropBean blockSoybean;

}
