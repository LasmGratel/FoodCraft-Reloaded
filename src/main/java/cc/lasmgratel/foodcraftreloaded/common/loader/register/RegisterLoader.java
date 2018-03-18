/*
 * FoodCraft Mod - Add more food to your Minecraft.
 * Copyright (C) 2017 Lasm Gratel
 *
 * This file is part of FoodCraft Mod.
 *
 * FoodCraft Mod is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FoodCraft Mod is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with FoodCraft Mod.  If not, see <http://www.gnu.org/licenses/>.
 */

package cc.lasmgratel.foodcraftreloaded.common.loader.register;

import cc.lasmgratel.foodcraftreloaded.api.capability.liqueur.LiqueurType;
import cc.lasmgratel.foodcraftreloaded.api.capability.liqueur.LiqueurTypes;
import cc.lasmgratel.foodcraftreloaded.client.util.masking.CustomModelMasking;
import cc.lasmgratel.foodcraftreloaded.common.FoodCraftReloaded;
import cc.lasmgratel.foodcraftreloaded.common.item.food.ItemGeneratedLiqueur;
import cc.lasmgratel.foodcraftreloaded.common.loader.LiqueurLoader;
import cc.lasmgratel.foodcraftreloaded.common.util.loader.annotation.Load;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.StringUtils;

import java.util.stream.Collectors;

public class RegisterLoader {
    @Load
    public void load() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Load(LoaderState.AVAILABLE)
    public void registerOre() {
        RegisterManager.getInstance().registerOre();
    }

    @SubscribeEvent
    public void register(RegistryEvent.Register event) {
        RegisterManager.getInstance().register(event.getGenericType(), event.getRegistry());
        if (event.getGenericType() == Item.class)
            detectAndRegisterLiqueur(event);
    }

    public void detectAndRegisterLiqueur(RegistryEvent.Register<Item> event) {
        event.getRegistry().getKeys().stream().filter(s -> s.getResourcePath().contains("liqueur")).map(ForgeRegistries.ITEMS::getValue).collect(Collectors.toList()).forEach(liqueur -> {
            for (LiqueurType liqueurType : LiqueurTypes.values()) {
                if (liqueurType == LiqueurTypes.NORMAL)
                    continue;
                ItemGeneratedLiqueur typedLiqueur = new ItemGeneratedLiqueur(MathHelper.floor(liqueurType.getHealModifier() * ((ItemFood) liqueur).getHealAmount(new ItemStack(liqueur))));
                typedLiqueur.setLiqueurType(liqueurType);
                typedLiqueur.setItemStackDisplayNameCallback(liqueur::getItemStackDisplayName);
                typedLiqueur.setRegistryName(liqueur.getRegistryName().getResourceDomain(), liqueurType.getUnlocalizedName() + "_" + liqueur.getRegistryName().getResourcePath());
                typedLiqueur.setUnlocalizedName(liqueur.getUnlocalizedName());
                event.getRegistry().register(typedLiqueur);
                OreDictionary.registerOre("listAll" + StringUtils.capitalize(liqueurType.getUnlocalizedName()) + "liqueur", typedLiqueur);
                OreDictionary.registerOre("listAllliqueur", typedLiqueur);
                OreDictionary.registerOre("listAllfoods", typedLiqueur);
                if (liqueur instanceof CustomModelMasking)
                    FoodCraftReloaded.getLoader(LiqueurLoader.class).get().getLiqueurCustomModelMap().put(typedLiqueur, (CustomModelMasking) liqueur);
            }
        });
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void registerModel(ModelRegistryEvent event) {
        RegisterManager.getInstance().registerRender();
        FoodCraftReloaded.getLoader(LiqueurLoader.class).get().loadRenders();
    }
}
