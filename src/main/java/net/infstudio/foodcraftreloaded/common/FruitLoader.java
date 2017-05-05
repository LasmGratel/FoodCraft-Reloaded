package net.infstudio.foodcraftreloaded.common;

import biomesoplenty.common.world.generator.tree.GeneratorBasicTree;
import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.block.BlockFluidJuice;
import net.infstudio.foodcraftreloaded.block.BlockFruitLeaves;
import net.infstudio.foodcraftreloaded.block.BlockFruitSapling;
import net.infstudio.foodcraftreloaded.fluid.FluidJuice;
import net.infstudio.foodcraftreloaded.init.FCRCreativeTabs;
import net.infstudio.foodcraftreloaded.item.food.*;
import net.infstudio.foodcraftreloaded.utils.NameBuilder;
import net.infstudio.foodcraftreloaded.utils.loader.annotation.Load;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class FruitLoader {
    private Map<EnumFruitType, BlockFruitLeaves> leavesMap = new EnumMap<>(EnumFruitType.class);
    private Map<EnumFruitType, FluidJuice> juiceMap = new EnumMap<>(EnumFruitType.class);
    private Map<EnumFruitType, BlockFruitSapling> saplingMap = new EnumMap<>(EnumFruitType.class);
    private Map<EnumFruitType, GeneratorBasicTree> generatorTreeMap = new EnumMap<>(EnumFruitType.class);
    private Map<EnumFruitType, BlockFluidJuice> fluidJuiceMap = new EnumMap<>(EnumFruitType.class);
    private ItemFruits fruits;
    private ItemJuices juices;
    private ItemSodas sodas;
    private ItemIcecreams icecreams;
    private ItemCakes cakes;

    @Load
    public void loadFruits() {
        fruits = new ItemFruits();
        GameRegistry.register(fruits);
        Arrays.stream(EnumFruitType.values()).forEach(fruitType -> {
            MinecraftForge.addGrassSeed(new ItemStack(fruits, 1, fruitType.ordinal()), 4);
            BlockFruitLeaves fruitLeaves = new BlockFruitLeaves(fruitType);
            GameRegistry.register(fruitLeaves);
            FluidJuice fluidJuice = new FluidJuice(fruitType);
            FluidRegistry.registerFluid(fluidJuice);
            FluidRegistry.addBucketForFluid(fluidJuice);
            BlockFluidJuice blockFluidJuice = new BlockFluidJuice(fruitType);
            GameRegistry.register(blockFluidJuice);
            fluidJuiceMap.put(fruitType, blockFluidJuice);
            BlockFruitSapling sapling = new BlockFruitSapling(fruitType);
            GameRegistry.register(sapling);
            GameRegistry.register(new ItemBlock(sapling).setUnlocalizedName(NameBuilder.buildUnlocalizedName(fruitType.toString(), "sapling")).setRegistryName(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName(fruitType.toString(), "sapling")).setCreativeTab(FCRCreativeTabs.BASE));
            leavesMap.put(fruitType, fruitLeaves);
            juiceMap.put(fruitType, fluidJuice);
            saplingMap.put(fruitType, sapling);
            generatorTreeMap.put(fruitType, new GeneratorBasicTree.Builder().amountPerChunk(2f).minHeight(4).maxHeight(7)
                .placeOn(Material.GRASS, Material.GROUND).replace(Material.AIR, Material.LEAVES, Material.WOOD)
                .log(BlockPlanks.EnumType.JUNGLE)
                .altLeaves(BlockPlanks.EnumType.JUNGLE).leaves(fruitLeaves.getDefaultState()).create());
        });
        juices = new ItemJuices();
        GameRegistry.register(juices);
        sodas = new ItemSodas();
        GameRegistry.register(sodas);
        icecreams = new ItemIcecreams();
        GameRegistry.register(icecreams);
        cakes = new ItemCakes();
        GameRegistry.register(cakes);
        for (int i = 0; i < EnumFruitType.values().length; i++) {
            OreDictionary.registerOre("listAllfruit", new ItemStack(fruits, 1, i));
            OreDictionary.registerOre(NameBuilder.buildUnlocalizedName("crop", EnumFruitType.values()[i].toString()), new ItemStack(fruits, 1, i));
            OreDictionary.registerOre(NameBuilder.buildUnlocalizedName("fruit", EnumFruitType.values()[i].toString()), new ItemStack(fruits, 1, i));
            OreDictionary.registerOre("listAlljuice", new ItemStack(juices, 1, i));
            OreDictionary.registerOre(NameBuilder.buildUnlocalizedName("food", EnumFruitType.values()[i].toString()) + "juice", new ItemStack(juices, 1, i));
            OreDictionary.registerOre("listAllicecream", new ItemStack(icecreams, 1, i));
            OreDictionary.registerOre(NameBuilder.buildUnlocalizedName("food", EnumFruitType.values()[i].toString()) + "icecream", new ItemStack(juices, 1, i));
        }
    }

    @Load(side = Side.CLIENT)
    public void loadRenders() {
        for (int i = 0; i < EnumFruitType.values().length; i++) {
            registerRender(fruits, i, new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName("fruit", EnumFruitType.values()[i].toString())), "inventory"));
            registerRender(juices, i, new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "juice"), "inventory"));
            registerRender(sodas, i, new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "soda"), "inventory"));
            registerRender(icecreams, i, new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "ice_cream"), "inventory"));
            registerRender(cakes, i, new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "cake"), "inventory"));
            ModelLoader.setCustomStateMapper(leavesMap.get(EnumFruitType.values()[i]), blockIn -> Collections.singletonMap(blockIn.getDefaultState(), new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "fruit_leaves"), "normal")));
            registerRender(Item.getItemFromBlock(leavesMap.get(EnumFruitType.values()[i])), 0, new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "fruit_leaves"), "inventory"));
            ModelLoader.setCustomStateMapper(saplingMap.get(EnumFruitType.values()[i]), blockIn -> Collections.singletonMap(blockIn.getDefaultState(), new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "fruit_sapling"), "normal")));
            registerRender(Item.getItemFromBlock(saplingMap.get(EnumFruitType.values()[i])), 0, new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "fruit_sapling"), "inventory"));
            registerFluidRender(fluidJuiceMap.get(EnumFruitType.values()[i]), fluidJuiceMap.get(EnumFruitType.values()[i]).getRegistryName().getResourcePath());
        }
    }

    @SideOnly(Side.CLIENT)
    private void registerFluidRender(BlockFluidBase blockFluid, String blockStateName) {
        final String location = FoodCraftReloaded.MODID + ":" + blockStateName;
        final Item itemFluid = Item.getItemFromBlock(blockFluid);
        ModelLoader.setCustomMeshDefinition(itemFluid, stack -> new ModelResourceLocation(location, "fluid"));
        ModelLoader.setCustomStateMapper(blockFluid, new StateMapperBase() {
            @Nonnull
            @Override
            protected ModelResourceLocation getModelResourceLocation(@Nonnull IBlockState state) {
                return new ModelResourceLocation(location, "fluid");
            }
        });
    }

    @Load(value = LoaderState.POSTINITIALIZATION, side = Side.CLIENT)
    public void loadColors() {
        leavesMap.values().forEach((leaves) -> Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, worldIn, pos, tintIndex) -> tintIndex == 0 ? BiomeColorHelper.getFoliageColorAtPos(worldIn, pos) : tintIndex == 1 ? ((BlockFruitLeaves) state.getBlock()).getFruitType().getColor().getRGB() : -1, leaves));
        fluidJuiceMap.values().forEach(block -> Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, worldIn, pos, tintIndex) -> (tintIndex == 0 ? ((BlockFluidJuice) state.getBlock()).getFruitType().getColor().getRGB() : -1)));
        saplingMap.values().forEach((sapling) -> Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
            if (stack.getItem() == Item.getItemFromBlock(sapling) && tintIndex == 0)
                return EnumFruitType.values()[stack.getMetadata()].getColor().getRGB();
            return -1;
        }, sapling));
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
            if (tintIndex == 1 && stack.getItem() instanceof ItemJuices)
                return EnumFruitType.values()[stack.getMetadata()].getColor().getRGB();
            else return -1;
        }, juices);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
            if (tintIndex == 1 && stack.getItem() instanceof ItemSodas)
                return EnumFruitType.values()[stack.getMetadata()].getColor().getRGB();
            else return -1;
        }, sodas);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
            if (tintIndex == 1 && stack.getItem() instanceof ItemIcecreams)
                return EnumFruitType.values()[stack.getMetadata()].getColor().getRGB();
            else return -1;
        }, icecreams);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
            if (tintIndex == 0 && stack.getItem() instanceof ItemCakes)
                return EnumFruitType.values()[stack.getMetadata()].getColor().getRGB();
            else return -1;
        }, cakes);
    }

    private void registerRender(Item item, int meta, ModelResourceLocation location) {
        ModelLoader.setCustomModelResourceLocation(item, meta, location);
    }

    public ItemFruits getFruits() {
        return fruits;
    }

    public ItemJuices getJuices() {
        return juices;
    }

    public ItemSodas getSodas() {
        return sodas;
    }

    public ItemIcecreams getIcecreams() {
        return icecreams;
    }

    public ItemCakes getCakes() {
        return cakes;
    }

    public Map<EnumFruitType, BlockFruitLeaves> getLeavesMap() {
        return leavesMap;
    }

    public Map<EnumFruitType, FluidJuice> getJuiceMap() {
        return juiceMap;
    }

    public Map<EnumFruitType, BlockFruitSapling> getSaplingMap() {
        return saplingMap;
    }

    public Map<EnumFruitType, GeneratorBasicTree> getGeneratorTreeMap() {
        return generatorTreeMap;
    }

    public Map<EnumFruitType, BlockFluidJuice> getFluidJuiceMap() {
        return fluidJuiceMap;
    }
}
