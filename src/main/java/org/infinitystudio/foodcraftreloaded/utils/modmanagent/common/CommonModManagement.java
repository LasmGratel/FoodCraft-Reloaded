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
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
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
import org.infinitystudio.foodcraftreloaded.block.BeanBlock;
import org.infinitystudio.foodcraftreloaded.block.VegetableBlock;
import org.infinitystudio.foodcraftreloaded.common.FoodCraftRegistration;
import org.infinitystudio.foodcraftreloaded.item.*;
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
                Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
                        .register(Item.getItemFromBlock((Block) instance), 0, mrl);
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
                Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register((Item) instance, 0, mrl);
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
            String sproutsName = "item" + annotation.type().name() + "BeanSprouts";;
            String seedName = "item" + annotation.type().name() + "Bean";
            String name = "block" + annotation.type().name() + "Bean";

            ((BeanBlock) instance).setUnlocalizedName(name);
            ((BeanBlock) instance).setSeed(seedName);
            ((BeanBlock) instance).setCrop(seedName);
            ((BeanBlock) instance).setSprouts(sproutsName);

            GameRegistry.registerBlock((Block) instance, annotation.itemBlock(), name);
        }

        @Override
        public void registerClient(String modid, ModBeanBlock annotation, Object instance) throws Exception {
            if (annotation.itemRender()) {
                String name = "block" + annotation.type().name() + "Bean";
                String location = modid + ":" + name;
                ModelResourceLocation mrl = new ModelResourceLocation(location, "inventory");
                Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
                        .register(Item.getItemFromBlock((Block) instance), 0, mrl);
            }
        }
    };

    public final static ModManagement<ModFruit> FRUIT = new ModManagement<ModFruit>(ModFruit.class, IModManagement.Stage.PREINIT) {
        @Override
        public Object init(String modid, ModFruit annotation, Class<?> clazz) throws Exception {
            String fruitName = "itemFruit" + annotation.type().name();
            Class[] typeName = new Class[]{
                    String.class
            };
            return FruitItem.class.getConstructor(typeName).newInstance(fruitName);
        }

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
                Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register((Item) instance, 0, mrl);
            }
        }
    };

    public final static ModManagement<ModJuice> JUICE = new ModManagement<ModJuice>(ModJuice.class, IModManagement.Stage.INIT) {
        @Override
        public Object init(String modid, ModJuice annotation, Class<?> clazz) throws Exception {
            String fruitName = "itemFruit" + annotation.type().name() + "Drink";
            Class[] typeName = new Class[]{
                    String.class
            };
            return FruitDrinkItem.class.getConstructor(typeName).newInstance(fruitName);
        }

        @Override
        public void register(String modid, ModJuice annotation, Object instance) throws Exception {
            String fruitName = "itemFruit" + annotation.type().name() + "Drink";
            ((Item) instance).setCreativeTab(FoodCraftRegistration.FcTabDrink);
            ((Item) instance).setUnlocalizedName(fruitName);
            GameRegistry.registerItem((Item) instance, fruitName);
            OreDictionary.registerOre("listAlljuice", (Item) instance);
            OreDictionary.registerOre("food" + annotation.type().name() + "juice", (Item) instance);
        }

        @Override
        public void registerClient(String modid, ModJuice annotation, Object instance) throws Exception {
            if (annotation.itemRender()) {
                String fruitName = "itemFruit" + annotation.type().name() + "Drink";
                String location = modid + ":" + fruitName;
                ModelResourceLocation mrl = new ModelResourceLocation(location, "inventory");
                Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register((Item) instance, 0, mrl);
            }
        }
    };

    public final static ModManagement<ModIcecream> FRUITICECREAM = new ModManagement<ModIcecream>(ModIcecream.class, IModManagement.Stage.INIT) {
        @Override
        public Object init(String modid, ModIcecream annotation, Class<?> clazz) throws Exception {
            String fruitName = "itemFruit" + annotation.type().name() + "Icecream";
            Class[] typeName = new Class[]{
                    String.class
            };
            return IcecreamItem.class.getConstructor(typeName).newInstance(fruitName);
        }

        @Override
        public void register(String modid, ModIcecream annotation, Object instance) throws Exception {
            String fruitName = "itemFruit" + annotation.type().name() + "Icecream";
            ((Item) instance).setCreativeTab(FoodCraftRegistration.FcTabIcecream);
            ((Item) instance).setUnlocalizedName(fruitName);
            GameRegistry.registerItem((Item) instance, fruitName);
            OreDictionary.registerOre("listAllicecream", (Item) instance);
            OreDictionary.registerOre("food" + annotation.type().name() + "icecream", (Item) instance);
        }

        @Override
        public void registerClient(String modid, ModIcecream annotation, Object instance) throws Exception {
            if (annotation.itemRender()) {
                String fruitName = "itemFruit" + annotation.type().name() + "Icecream";
                String location = modid + ":" + fruitName;
                ModelResourceLocation mrl = new ModelResourceLocation(location, "inventory");
                Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register((Item) instance, 0, mrl);
            }
        }
    };

    public final static ModManagement<ModSprouts> SPROUTS = new ModManagement<ModSprouts>(ModSprouts.class, IModManagement.Stage.PREINIT) {
        @Override
        public Object init(String modid, ModSprouts annotation, Class<?> clazz) throws Exception {
            Class[] typeName = new Class[]{
                    String.class
            };

            String beanName = "item" + annotation.type().name() + "BeanSprouts";
            return SproutsItem.class.getConstructor(typeName).newInstance(beanName);
        }

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
                Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register((Item) instance, 0, mrl);
            }
        }
    };

    public final static ModManagement<ModBean> BEAN = new ModManagement<ModBean>(ModBean.class, IModManagement.Stage.PREINIT) {
        @Override
        public Object init(String modid, ModBean annotation, Class<?> clazz) throws Exception {
            Class[] typeName = new Class[]{
                    String.class
            };

            String beanName = "item" + annotation.type().name() + "Bean";

            return BeanItem.class.getConstructor(typeName).newInstance(beanName);
        }

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
                Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register((Item) instance, 0, mrl);
            }
        }
    };

    public final static ModManagement<ModFood> FOOD = new ModManagement<ModFood>(ModFood.class, IModManagement.Stage.PREINIT) {
        @Override
        public Object init(String modid, ModFood annotation, Class<?> clazz) throws Exception {
            Class<?>[] types = {
                    String.class, float.class, boolean.class
            };
            Constructor<FoodItem> constructor = FoodItem.class.getConstructor(types);
            return constructor.newInstance(annotation.name(), annotation.satuation(), annotation.hasEffect());
        }

        @Override
        public void register(String modid, ModFood annotation, Object instance) throws Exception {
            ((FoodItem) instance).setUnlocalizedName(annotation.name());
            ((FoodItem) instance).setCreativeTab(FoodCraftRegistration.FcTabBase);
            GameRegistry.registerItem((Item) instance, annotation.name());
            for(String oreDictName : annotation.oredicts()) {
                OreDictionary.registerOre(oreDictName, (Item) instance);
            }
        }

        @Override
        public void registerClient(String modid, ModFood annotation, Object instance) throws Exception {
            if (annotation.itemRender()) {
                String location = modid + ":" + annotation.name();
                ModelResourceLocation mrl = new ModelResourceLocation(location, "inventory");
                Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register((Item) instance, 0, mrl);
            }
        }
    };

    public final static ModManagement<ModVegetable> VEGETABLE = new ModManagement<ModVegetable>(ModVegetable.class, IModManagement.Stage.PREINIT) {
        @Override
        public Object init(String modid, ModVegetable annotation, Class<?> clazz) throws Exception {
            Class[] type = new Class[]{
                String.class, float.class, boolean.class
            };
            Constructor<VegetableItem> constructor = VegetableItem.class.getConstructor(type);
            return constructor.newInstance(annotation.name(), annotation.satuation(), annotation.hasEffect());
        }

        @Override
        public void register(String modid, ModVegetable annotation, Object instance) throws Exception {
            ((VegetableItem) instance).setCreativeTab(FoodCraftRegistration.FcTabPlant);
            ((VegetableItem) instance).setCanPlant(annotation.canPlant());
            ((VegetableItem) instance).setSeedblock(Block.getBlockFromName(annotation.seedBlockName()));
            MinecraftForge.addGrassSeed(new ItemStack((VegetableItem) instance), 2);
            for(String oreDictName : annotation.oredicts()) {
                OreDictionary.registerOre(oreDictName, (Item) instance);
            }
        }

        @Override
        public void registerClient(String modid, ModVegetable annotation, Object instance) throws Exception {
            if (annotation.itemRender()) {
                String location = modid + ":" + annotation.name();
                ModelResourceLocation mrl = new ModelResourceLocation(location, "inventory");
                Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register((Item) instance, 0, mrl);
            }
        }
    };

    public final static ModManagement<ModVegetableBlock> VEGETABLEBLOCK = new ModManagement<ModVegetableBlock>(ModVegetableBlock.class, IModManagement.Stage.PREINIT) {
        @Override
        public void register(String modid, ModVegetableBlock annotation, Object instance) throws Exception {
            ((VegetableBlock) instance).setCrop(annotation.cropName());
            ((VegetableBlock) instance).setSeed(annotation.seedName());
        }

        @Override
        public void registerClient(String modid, ModVegetableBlock annotation, Object instance) throws Exception {
            if (annotation.itemRender()) {
                String location = modid + ":" + annotation.name();
                ModelResourceLocation mrl = new ModelResourceLocation(location, "inventory");
                Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
                        .register(Item.getItemFromBlock((Block) instance), 0, mrl);
            }
        }
    };

    public final static ModManagement<ModMeat> MEAT = new ModManagement<ModMeat>(ModMeat.class, IModManagement.Stage.PREINIT) {
        @Override
        public Object init(String modid, ModMeat annotation, Class<?> clazz) throws Exception {
            Class[] type = new Class[]{
                    String.class, float.class, boolean.class
            };
            Constructor<MeatItem> constructor = MeatItem.class.getConstructor(type);
            return constructor.newInstance(annotation.name(), annotation.satuation(), annotation.hasEffect());
        }

        @Override
        public void register(String modid, ModMeat annotation, Object instance) throws Exception {
            ((MeatItem) instance).setUnlocalizedName(annotation.name());
            ((MeatItem) instance).setCreativeTab(FoodCraftRegistration.FcTabPlant);
        }

        @Override
        public void registerClient(String modid, ModMeat annotation, Object instance) throws Exception {
            if (annotation.itemRender()) {
                String location = modid + ":" + annotation.name();
                ModelResourceLocation mrl = new ModelResourceLocation(location, "inventory");
                Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register((Item) instance, 0, mrl);
            }
        }
    };

    public final static ModManagement<ModFluid> FLUID = new ModManagement<ModFluid>(ModFluid.class, IModManagement.Stage.PREINIT) {
        @Override
        public void register(String modid, ModFluid annotation, Object instance) throws Exception {
            FluidRegistry.registerFluid((Fluid) instance);
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
                ModelBakery.addVariantName(itemFluid);
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
        public Object init(String modid, ModTileEntity annotation, Class<?> clazz) {
            return annotation.tileEntityClass();
        }

        @Override
        public void register(String modid, ModTileEntity annotation, Object instance) throws Exception {
            Class<? extends TileEntity> clz = ((Class<?>) instance).asSubclass(TileEntity.class);
            GameRegistry.registerTileEntity(clz, annotation.id());
        }
    };
}
