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
package org.infinitystudio.foodcraftreloaded.utils.modmanagent.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import org.infinitystudio.foodcraftreloaded.block.BlockCropBean;
import org.infinitystudio.foodcraftreloaded.block.BlockCropVegetable;
import org.infinitystudio.foodcraftreloaded.block.BlockJuiceFluid;
import org.infinitystudio.foodcraftreloaded.common.FoodCraftRegistration;
import org.infinitystudio.foodcraftreloaded.fluid.FluidJuice;
import org.infinitystudio.foodcraftreloaded.item.food.*;
import org.infinitystudio.foodcraftreloaded.utils.modmanagent.IModManagement;
import org.infinitystudio.foodcraftreloaded.utils.modmanagent.ModManagement;

import java.lang.reflect.Constructor;

/**
 * Pre Defined Mod Managers
 *
 * @author ustc_zzzz
 */
public class CommonModManagement {
    public final static ModManagement<ModObject> OBJPREINIT = new ModManagement<ModObject>(ModObject.class,
            IModManagement.Stage.PREINIT) {
        @Override
        public Object init(String modid, ModObject annotation, Class<?> clazz) throws Exception {
            return annotation.stage() == IModManagement.Stage.PREINIT ? super.init(modid, annotation, clazz) : null;
        }
    };

    public final static ModManagement<ModObject> OBJINIT = new ModManagement<ModObject>(ModObject.class, IModManagement.Stage.INIT) {
        @Override
        public Object init(String modid, ModObject annotation, Class<?> clazz) throws Exception {
            return annotation.stage() == IModManagement.Stage.INIT ? super.init(modid, annotation, clazz) : null;
        }
    };

    public final static ModManagement<ModObject> OBJPOSTINIT = new ModManagement<ModObject>(ModObject.class,
            IModManagement.Stage.POSTINIT) {
        @Override
        public Object init(String modid, ModObject annotation, Class<?> clazz) throws Exception {
            return annotation.stage() == IModManagement.Stage.POSTINIT ? super.init(modid, annotation, clazz) : null;
        }
    };

    public final static ModManagement<ModEventBus> EVENTBUS = new ModManagement<ModEventBus>(ModEventBus.class,
            IModManagement.Stage.PREINIT) {
        @Override
        public void register(String modid, ModEventBus annotation, Object instance) throws Exception {
            MinecraftForge.EVENT_BUS.register(instance);
        }

    };

    public final static ModManagement<ModMaterial> MATERIAL = new ModManagement<ModMaterial>(ModMaterial.class, IModManagement.Stage.PREINIT) {
        @Override
        public Object init(String modid, ModMaterial annotation, Class<?> clazz) throws Exception {
            MapColor color = MapColor.AIR;
            for(MapColor mapColor : MapColor.COLORS)
                if(mapColor.colorIndex == annotation.value())
                    color = mapColor;
            return Material.class.getConstructor(MapColor.class).newInstance(color);
        }

        @Override
        public void register(String modid, ModMaterial annotation, Object instance) throws Exception {
        }
    };

    public final static ModManagement<ModBlock> BLOCK = new ModManagement<ModBlock>(ModBlock.class, IModManagement.Stage.PREINIT,
            IModManagement.Stage.INIT) {
        @Override
        public void register(String modid, ModBlock annotation, Object instance) throws Exception {
            GameRegistry.registerBlock((Block) instance, annotation.itemBlock(), annotation.name());
            ((Block) instance).setUnlocalizedName(annotation.name());

        }

        @SideOnly(Side.CLIENT)
        @Override
        public void registerClient(String modid, ModBlock annotation, Object instance) throws Exception {
            if (annotation.itemRender()) {
                String location = modid + ":" + annotation.name();
                ModelResourceLocation mrl = new ModelResourceLocation(location, "inventory");
                ModelLoader.setCustomModelResourceLocation(
                		Item.getItemFromBlock((Block) instance), 0, mrl);
            }
        }
    };

    public final static ModManagement<ModItem> ITEM = new ModManagement<ModItem>(ModItem.class, IModManagement.Stage.PREINIT,
            IModManagement.Stage.INIT) {
        @Override
        public void register(String modid, ModItem annotation, Object instance) throws Exception {
            GameRegistry.registerItem((Item) instance, annotation.name());
            ((Item) instance).setUnlocalizedName(annotation.name());
        }

        @SideOnly(Side.CLIENT)
        @Override
        public void registerClient(String modid, ModItem annotation, Object instance) throws Exception {
            if (annotation.itemRender()) {
                String location = modid + ":" + annotation.name();
                ModelResourceLocation mrl = new ModelResourceLocation(location, "inventory");
                ModelLoader.setCustomModelResourceLocation(
                		Item.getItemFromBlock((Block) instance), 0, mrl);
            }
        }
    };

    public final static ModManagement<ModEvent> EVENT = new ModManagement<ModEvent>(ModEvent.class) {
        @Override
        public void register(String modid, ModEvent annotation, Object instance) throws Exception {
            switch (annotation.value()) {
                case ALL:
                    FMLCommonHandler.instance().bus().register(instance);
                    MinecraftForge.ORE_GEN_BUS.register(instance);
                    MinecraftForge.TERRAIN_GEN_BUS.register(instance);
                    MinecraftForge.EVENT_BUS.register(instance);
                    break;
                case FML:
                    FMLCommonHandler.instance().bus().register(instance);
                    break;
                case ORE:
                    MinecraftForge.ORE_GEN_BUS.register(instance);
                    break;
                case TERRAIN:
                    MinecraftForge.TERRAIN_GEN_BUS.register(instance);
                    break;
                case FORGE:
                    MinecraftForge.EVENT_BUS.register(instance);
                    break;
                case NONE:
                    break;
            }
        }
    };

    public final static ModManagement<ModBeanBlock> BEANBLOCK = new ModManagement<ModBeanBlock>(ModBeanBlock.class, IModManagement.Stage.PREINIT) {
        @Override
        public void register(String modid, ModBeanBlock annotation, Object instance) throws Exception {
            String sproutsName = "item" + annotation.type().name() + "BeanSprouts";

            String seedName = "item" + annotation.type().name() + "Bean";
            String name = "block" + annotation.type().name() + "Bean";

            ((BlockCropBean) instance).setUnlocalizedName(name);
            ((BlockCropBean) instance).setSeed(seedName);
            ((BlockCropBean) instance).setCrop(seedName);
            ((BlockCropBean) instance).setSprouts(sproutsName);

            GameRegistry.registerBlock((Block) instance, annotation.itemBlock(), name);

        }

        @Override
        public void registerClient(String modid, ModBeanBlock annotation, Object instance) throws Exception {
            if (annotation.itemRender()) {
                String name = "block" + annotation.type().name() + "Bean";
                String location = modid + ":" + name;
                ModelResourceLocation mrl = new ModelResourceLocation(location, "inventory");
                ModelLoader.setCustomModelResourceLocation(
                		Item.getItemFromBlock((Block) instance), 0, mrl);
            }
        }
    };

    public final static ModManagement<ModFruit> FRUIT = new ModManagement<ModFruit>(ModFruit.class, IModManagement.Stage.PREINIT) {
        @Override
        public void register(String modid, ModFruit annotation, Object instance) throws Exception {
            String fruitName = "itemFruit" + annotation.type().name();
            ((Item) instance).setCreativeTab(FoodCraftRegistration.FcTabPlant);
            ((Item) instance).setUnlocalizedName(fruitName);
            GameRegistry.registerItem((Item) instance, fruitName);
            OreDictionary.registerOre("listAllfruit", (Item) instance);
            OreDictionary.registerOre("crop" + annotation.type().name(), (Item) instance);
        }

        @Override
        public void registerClient(String modid, ModFruit annotation, Object instance) throws Exception {
            if (annotation.itemRender()) {
                String fruitName = "itemFruit" + annotation.type().name();
                String location = modid + ":" + fruitName;
                ModelResourceLocation mrl = new ModelResourceLocation(location, "inventory");
                ModelLoader.setCustomModelResourceLocation(
                		Item.getItemFromBlock((Block) instance), 0, mrl);
            }
        }
    };

    public final static ModManagement<ModJuice> JUICE = new ModManagement<ModJuice>(ModJuice.class, IModManagement.Stage.INIT) {
        @Override
        public void register(String modid, ModJuice annotation, Object instance) throws Exception {
            String fruitName = "itemJuice" + annotation.type().name();
            ((ItemJuice) instance).setColor(annotation.type().getColor());
            ((Item) instance).setCreativeTab(FoodCraftRegistration.FcTabDrink);
            ((Item) instance).setUnlocalizedName(fruitName);
            GameRegistry.registerItem((Item) instance, fruitName);
            OreDictionary.registerOre("listAlljuice", (Item) instance);
            OreDictionary.registerOre("food" + annotation.type().name() + "juice", (Item) instance);
        }

        @Override
        public void registerClient(String modid, ModJuice annotation, Object instance) throws Exception {
            if (annotation.itemRender()) {
                String fruitName = "itemJuice";
                String location = modid + ":" + fruitName;
                ModelResourceLocation mrl = new ModelResourceLocation(location, "inventory");
                ModelLoader.setCustomModelResourceLocation(
                		Item.getItemFromBlock((Block) instance), 0, mrl);
            }
        }
    };

    public final static ModManagement<ModIcecream> FRUITICECREAM = new ModManagement<ModIcecream>(ModIcecream.class, IModManagement.Stage.INIT) {
        @Override
        public void register(String modid, ModIcecream annotation, Object instance) throws Exception {
            String fruitName = "itemFruit" + annotation.type().name() + "Icecream";
            ((Item) instance).setCreativeTab(FoodCraftRegistration.FcTabDrink);
            ((Item) instance).setUnlocalizedName(fruitName);
            ((ItemIcecream) instance).setColor(annotation.type().getColor());
            GameRegistry.registerItem((Item) instance, fruitName);
            OreDictionary.registerOre("listAllicecream", (Item) instance);
            OreDictionary.registerOre("food" + annotation.type().name() + "icecream", (Item) instance);
        }

        @Override
        public void registerClient(String modid, ModIcecream annotation, Object instance) throws Exception {
            if (annotation.itemRender()) {
                String fruitName = "itemFruitIcecream";
                String location = modid + ":" + fruitName;
                ModelResourceLocation mrl = new ModelResourceLocation(location, "inventory");
                ModelLoader.setCustomModelResourceLocation(
                		Item.getItemFromBlock((Block) instance), 0, mrl);
            }
        }
    };

    public final static ModManagement<ModSprouts> SPROUTS = new ModManagement<ModSprouts>(ModSprouts.class, IModManagement.Stage.PREINIT) {

        @Override
        public void register(String modid, ModSprouts annotation, Object instance) throws Exception {
            ((Item) instance).setUnlocalizedName("item" + annotation.type().name() + "BeanSprouts");
            ((Item) instance).setCreativeTab(FoodCraftRegistration.FcTabPlant);

            GameRegistry.registerItem((Item) instance, ((Item) instance).getUnlocalizedName());
            OreDictionary.registerOre("crop" + annotation.type().name() + "beansprout", (Item) instance);
            OreDictionary.registerOre("seed" + annotation.type().name() + "beansprout", (Item) instance);
            OreDictionary.registerOre("listAllseed", (Item) instance);
        }

        @Override
        public void registerClient(String modid, ModSprouts annotation, Object instance) throws Exception {
            if (annotation.itemRender()) {
                String beanName = "item" + annotation.type().name() + "BeanSprouts";
                String location = modid + ":" + beanName;
                ModelResourceLocation mrl = new ModelResourceLocation(location, "inventory");
                ModelLoader.setCustomModelResourceLocation(
                		Item.getItemFromBlock((Block) instance), 0, mrl);
            }
        }
    };

    public final static ModManagement<ModBean> BEAN = new ModManagement<ModBean>(ModBean.class, IModManagement.Stage.PREINIT) {
        @Override
        public void register(String modid, ModBean annotation, Object instance) throws Exception {
            ((Item) instance).setUnlocalizedName("item" + annotation.type().name() + "Bean");
            ((Item) instance).setCreativeTab(FoodCraftRegistration.FcTabPlant);

            GameRegistry.registerItem((Item) instance, ((Item) instance).getUnlocalizedName());
            OreDictionary.registerOre("listAllseed", (Item) instance);
            OreDictionary.registerOre("crop" + annotation.type().name() + "bean", (Item) instance);
            OreDictionary.registerOre("seed" + annotation.type().name() + "bean", (Item) instance);
        }

        @Override
        public void registerClient(String modid, ModBean annotation, Object instance) throws Exception {
            if (annotation.itemRender()) {
                String beanName = "item" + annotation.type().name() + "Bean";
                String location = modid + ":" + beanName;
                ModelResourceLocation mrl = new ModelResourceLocation(location, "inventory");
                ModelLoader.setCustomModelResourceLocation(
                		Item.getItemFromBlock((Block) instance), 0, mrl);
            }
        }
    };

    public final static ModManagement<ModFood> FOOD = new ModManagement<ModFood>(ModFood.class, IModManagement.Stage.PREINIT) {
        @Override
        public Object init(String modid, ModFood annotation, Class<?> clazz) throws Exception {
            Class<?>[] types = {
                    float.class
            };
            Constructor<ItemFcFood> constructor = ItemFcFood.class.getConstructor(types);
            return constructor.newInstance(annotation.satuation());
        }

        @Override
        public void register(String modid, ModFood annotation, Object instance) throws Exception {
            ((ItemFcFood) instance).setUnlocalizedName(annotation.name());
            ((ItemFcFood) instance).setCreativeTab(FoodCraftRegistration.FcTabBase);
            ((ItemFcFood) instance).setHasEffect(annotation.hasEffect());
            GameRegistry.registerItem((Item) instance, annotation.name());
            for (String oreDictName : annotation.oredicts()) {
                OreDictionary.registerOre(oreDictName, (Item) instance);
            }
        }

        @Override
        public void registerClient(String modid, ModFood annotation, Object instance) throws Exception {
            if (annotation.itemRender()) {
                String location = modid + ":" + annotation.name();
                ModelResourceLocation mrl = new ModelResourceLocation(location, "inventory");
                ModelLoader.setCustomModelResourceLocation(
                		Item.getItemFromBlock((Block) instance), 0, mrl);
            }
        }
    };

    public final static ModManagement<ModVegetable> VEGETABLE = new ModManagement<ModVegetable>(ModVegetable.class, IModManagement.Stage.PREINIT) {
        @Override
        public Object init(String modid, ModVegetable annotation, Class<?> clazz) throws Exception {
            Class[] type = new Class[]{
                    float.class
            };
            Constructor<ItemVegetable> constructor = ItemVegetable.class.getConstructor(type);
            return constructor.newInstance(annotation.satuation());
        }

        @Override
        public void register(String modid, ModVegetable annotation, Object instance) throws Exception {
            ((ItemVegetable) instance).setCreativeTab(FoodCraftRegistration.FcTabPlant);
            ((ItemVegetable) instance).setCanPlant(annotation.canPlant());
            ((ItemVegetable) instance).setSeedblock(Block.getBlockFromName(annotation.seedBlockName()));
            ((ItemVegetable) instance).setHasEffect(annotation.hasEffect());
            MinecraftForge.addGrassSeed(new ItemStack((ItemVegetable) instance), 2);
            for (String oreDictName : annotation.oredicts()) {
                OreDictionary.registerOre(oreDictName, (Item) instance);
            }
        }

        @Override
        public void registerClient(String modid, ModVegetable annotation, Object instance) throws Exception {
            if (annotation.itemRender()) {
                String location = modid + ":" + annotation.name();
                ModelResourceLocation mrl = new ModelResourceLocation(location, "inventory");
                ModelLoader.setCustomModelResourceLocation(
                		Item.getItemFromBlock((Block) instance), 0, mrl);
            }
        }
    };

    public final static ModManagement<ModVegetableBlock> VEGETABLEBLOCK = new ModManagement<ModVegetableBlock>(ModVegetableBlock.class, IModManagement.Stage.PREINIT) {
        @Override
        public void register(String modid, ModVegetableBlock annotation, Object instance) throws Exception {
            ((BlockCropVegetable) instance).setCrop(annotation.cropName());
            ((BlockCropVegetable) instance).setSeed(annotation.seedName());
        }

        @Override
        public void registerClient(String modid, ModVegetableBlock annotation, Object instance) throws Exception {
            if (annotation.itemRender()) {
                String location = modid + ":" + annotation.name();
                ModelResourceLocation mrl = new ModelResourceLocation(location, "inventory");
                ModelLoader.setCustomModelResourceLocation(
                		Item.getItemFromBlock((Block) instance), 0, mrl);
            }
        }
    };

    public final static ModManagement<ModMeat> MEAT = new ModManagement<ModMeat>(ModMeat.class, IModManagement.Stage.PREINIT) {
        @Override
        public Object init(String modid, ModMeat annotation, Class<?> clazz) throws Exception {
            Class[] type = new Class[]{
                    float.class
            };
            Constructor<ItemMeat> constructor = ItemMeat.class.getConstructor(type);
            return constructor.newInstance(annotation.satuation());
        }

        @Override
        public void register(String modid, ModMeat annotation, Object instance) throws Exception {
            ((ItemMeat) instance).setUnlocalizedName(annotation.name());
            ((ItemMeat) instance).setHasEffect(annotation.hasEffect());
            ((ItemMeat) instance).setCreativeTab(FoodCraftRegistration.FcTabPlant);
        }

        @Override
        public void registerClient(String modid, ModMeat annotation, Object instance) throws Exception {
            if (annotation.itemRender()) {
                String location = modid + ":" + annotation.name();
                ModelResourceLocation mrl = new ModelResourceLocation(location, "inventory");
                ModelLoader.setCustomModelResourceLocation(
                		Item.getItemFromBlock((Block) instance), 0, mrl);
            }
        }
    };

    public final static ModManagement<ModBlockJuiceFluid> BLOCKJUICEFLUID = new ModManagement<ModBlockJuiceFluid>(ModBlockJuiceFluid.class,
            IModManagement.Stage.POSTINIT) {
        @Override
        public void register(String modid, ModBlockJuiceFluid annotation, Object instance) throws Exception {
            String blockName = "blockFluid" + annotation.type().name() + "Juice";
            GameRegistry.registerBlock((Block) instance, blockName);
            ((BlockJuiceFluid) instance).setColor(annotation.type().getColor());
        }

        @SideOnly(Side.CLIENT)
        @Override
        public void registerClient(String modid, ModBlockJuiceFluid annotation, Object instance) throws Exception {
            if (annotation.modelRender()) {
                String blockName = "blockFluid"+ annotation.type().name() +"Juice";
                String location = modid + ":" + "blockFluidJuice";
                BlockFluidBase blockFluid = (BlockFluidBase) instance;
                Item itemFluid = Item.getItemFromBlock(blockFluid);
                ModelBakery.registerItemVariants(itemFluid, new ModelResourceLocation(location, "inventory"));
                final ModelResourceLocation mrl = new ModelResourceLocation(location, "fluid");
                ModelLoader.setCustomMeshDefinition(itemFluid, new ItemMeshDefinition() {
                    @Override
                    public ModelResourceLocation getModelLocation(ItemStack stack) {
                        return mrl;
                    }
                });
                ModelLoader.setCustomStateMapper(blockFluid, new StateMapperBase() {
                    @Override
                    protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                        return mrl;
                    }
                });
            }
        }
    };

    public final static ModManagement<ModFluid> FLUID = new ModManagement<ModFluid>(ModFluid.class, IModManagement.Stage.PREINIT) {
        @Override
        public void register(String modid, ModFluid annotation, Object instance) throws Exception {
            FluidRegistry.registerFluid((Fluid) instance);
        }
    };

    public final static ModManagement<ModJuiceFluid> JUICEFLUID = new ModManagement<ModJuiceFluid>(ModJuiceFluid.class, IModManagement.Stage.PREINIT) {
        @Override
        public Object init(String modid, ModJuiceFluid annotation, Class<?> clazz) throws Exception {
            String fluidName = "fluid" + annotation.type().name() + "Juice";
            String location = modid + ":" + "fluidJuice";
            ResourceLocation still = new ResourceLocation(location, "still");
            ResourceLocation flowing = new ResourceLocation(location, "flowing");
            Class<?>[] types = new Class<?>[]{
                String.class, ResourceLocation.class, ResourceLocation.class
            };
            return FluidJuice.class.getConstructor(types).newInstance(fluidName, still, flowing);
        }

        @Override
        public void register(String modid, ModJuiceFluid annotation, Object instance) throws Exception {
            String fluidName = "fluid" + annotation.type().name() + "Juice";
            ((Fluid) instance).setUnlocalizedName(fluidName);
            FluidRegistry.registerFluid((Fluid) instance);
            ((FluidJuice) instance).setColor(annotation.type().getColor());
        }
    };

    public final static ModManagement<ModBlockFluid> BLOCKFLUID = new ModManagement<ModBlockFluid>(ModBlockFluid.class,
            IModManagement.Stage.PREINIT) {
        @Override
        public void register(String modid, ModBlockFluid annotation, Object instance) throws Exception {
            GameRegistry.registerBlock((Block) instance, annotation.name());
        }

        @SideOnly(Side.CLIENT)
        @Override
        public void registerClient(String modid, ModBlockFluid annotation, Object instance) throws Exception {
            if (annotation.modelRender()) {
                String location = modid + ":" + annotation.name();
                BlockFluidBase blockFluid = (BlockFluidBase) instance;
                Item itemFluid = Item.getItemFromBlock(blockFluid);
                ModelBakery.registerItemVariants(itemFluid, new ModelResourceLocation(location, "inventory"));
                final ModelResourceLocation mrl = new ModelResourceLocation(location, "fluid");
                ModelLoader.setCustomMeshDefinition(itemFluid, new ItemMeshDefinition() {
                    @Override
                    public ModelResourceLocation getModelLocation(ItemStack stack) {
                        return mrl;
                    }
                });
                ModelLoader.setCustomStateMapper(blockFluid, new StateMapperBase() {
                    @Override
                    protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                        return mrl;
                    }
                });
            }
        }
    };

    public final static ModManagement<ModTileEntity> TILEENTITY = new ModManagement<ModTileEntity>(
            ModTileEntity.class) {
        @Override
        public Object init(String modid, ModTileEntity annotation, Class<?> clazz) throws IllegalAccessException, InstantiationException {
            return annotation.tileEntityClass().newInstance();
        }

        @Override
        public void register(String modid, ModTileEntity annotation, Object instance) throws Exception {
            GameRegistry.registerTileEntity(annotation.tileEntityClass(), annotation.id());
        }
    };
    public final static ModManagement<ModSoda> FRUITSODA = new ModManagement<ModSoda>(ModSoda.class, IModManagement.Stage.INIT) {
        @Override
        public void register(String modid, ModSoda annotation, Object instance) throws Exception {
            String fruitName = "itemFruit" + annotation.type().name() + "Soda";
            ((Item) instance).setCreativeTab(FoodCraftRegistration.FcTabDrink);
            ((Item) instance).setUnlocalizedName(fruitName);
            ((ItemSoda) instance).setColor(annotation.type().getColor());
            GameRegistry.registerItem((Item) instance, fruitName);
            OreDictionary.registerOre("listAllsoda", (Item) instance);
            OreDictionary.registerOre("food" + annotation.type().name() + "soda", (Item) instance);
        }

        @Override
        public void registerClient(String modid, ModSoda annotation, Object instance) throws Exception {
            if (annotation.itemRender()) {
                String fruitName = "itemFruitSoda";
                String location = modid + ":" + fruitName;
                ModelResourceLocation mrl = new ModelResourceLocation(location, "inventory");
                ModelLoader.setCustomModelResourceLocation(
                		Item.getItemFromBlock((Block) instance), 0, mrl);
            }
        }
    };

}
