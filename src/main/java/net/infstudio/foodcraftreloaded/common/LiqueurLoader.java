package net.infstudio.foodcraftreloaded.common;

import net.infstudio.foodcraftreloaded.item.food.ItemLiqueur;
import net.infstudio.foodcraftreloaded.item.food.LiqueurType;
import net.infstudio.foodcraftreloaded.util.loader.annotation.Load;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;

public class LiqueurLoader {
    @Load
    public void loadLiqueurs() {
        ForgeRegistries.ITEMS.getKeys().stream().filter(s -> s.getResourcePath().contains("liqueur")).map(ForgeRegistries.ITEMS::getValue).forEach(liqueur -> {
            Item agedLiqueur = new ItemLiqueur(6)
                .setType(LiqueurType.AGED)
                .setRegistryName(liqueur.getRegistryName().getResourceDomain(), "aged_" + liqueur.getRegistryName().getResourcePath())
                .setUnlocalizedName(liqueur.getUnlocalizedName());
            ForgeRegistries.ITEMS.register(agedLiqueur);
            OreDictionary.registerOre("foodAgedLiqueur" + agedLiqueur.getUnlocalizedName().substring(0, agedLiqueur.getUnlocalizedName().lastIndexOf("Liqueur")), agedLiqueur);
            OreDictionary.registerOre("listAllAgedliqueur", agedLiqueur);
            OreDictionary.registerOre("listAllliqueur", agedLiqueur);
            OreDictionary.registerOre("listAllfoods", agedLiqueur);
            Item cocktailLiqueur = new ItemLiqueur(7)
                .setType(LiqueurType.COCKTAIL)
                .setRegistryName(liqueur.getRegistryName().getResourceDomain(), "cocktail_" + liqueur.getRegistryName().getResourcePath())
                .setUnlocalizedName(liqueur.getUnlocalizedName());
            ForgeRegistries.ITEMS.register(cocktailLiqueur);
            OreDictionary.registerOre("foodCocktailLiqueur" + cocktailLiqueur.getUnlocalizedName().substring(0, cocktailLiqueur.getUnlocalizedName().lastIndexOf("Liqueur")), cocktailLiqueur);
            OreDictionary.registerOre("listAllCocktailliqueur", cocktailLiqueur);
            OreDictionary.registerOre("listAllliqueur", cocktailLiqueur);
            OreDictionary.registerOre("listAllfoods", cocktailLiqueur);
        });
    }
}
