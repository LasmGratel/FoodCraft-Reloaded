package net.infstudio.foodcraftreloaded.init;

import net.infstudio.foodcraftreloaded.item.food.ItemPFood;
import net.infstudio.foodcraftreloaded.item.food.ItemPorridge;
import net.infstudio.foodcraftreloaded.util.loader.annotation.RegFood;

public interface FCRFoods {
    @RegFood(modifier = {0.2f, 0.6f, 0f, 0f, 0.7f}, name = {"tomato", "and", "eggs"})
    ItemPFood TOMATO_AND_EGGS = new ItemPFood();

    // Porridge
    ItemPorridge RICE_PORRIDGE = new ItemPorridge(3, "rice");
    ItemPorridge BLACK_RICE_PORRIDGE = new ItemPorridge(6, "black", "rice");
    ItemPorridge DATE_BEAN_MILK_PORRIDGE = new ItemPorridge(7, "date", "bean", "milk");
    ItemPorridge GREENS_PORRIDGE = new ItemPorridge(6, "greens", "porridge");
    ItemPorridge MINCED_PORK_AND_PRESERVED_EGG_PORRIDGE = new ItemPorridge(10, "minced", "pork", "and", "preserved", "egg");
    ItemPorridge SHRIMP_CELERY_PORRIDGE = new ItemPorridge(10, "shrimp", "celery");
    ItemPorridge MUSHROOM_CHICKEN_PORRIDGE = new ItemPorridge(10, "mushroom", "chicken");
    ItemPorridge LABA_PORRIDGE = new ItemPorridge(7, "laba");
    ItemPorridge BEEF_EGG_PORRIDGE = new ItemPorridge(10, "beef", "egg");
    ItemPorridge PUMPKIN_PORRIDGE = new ItemPorridge(6, "pumpkin");
    ItemPorridge RED_SWEET_POTATO_PORRIDGE = new ItemPorridge(6, "red", "sweet", "potato");
    ItemPorridge WHITE_SWEET_POTATO_PORRIDGE = new ItemPorridge(6, "white", "sweet", "potato");
    ItemPorridge TOMATO_AND_EGG_PORRIDGE = new ItemPorridge(7, "tomato", "and", "egg");
    ItemPorridge WHITE_RADISH_PORRIDGE = new ItemPorridge(6, "white", "radish");
}
