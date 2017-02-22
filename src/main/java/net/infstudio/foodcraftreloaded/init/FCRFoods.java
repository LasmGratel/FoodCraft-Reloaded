package net.infstudio.foodcraftreloaded.init;

import net.infstudio.foodcraftreloaded.item.food.ItemPFood;
import net.infstudio.foodcraftreloaded.utils.loader.annotation.RegFood;

public interface FCRFoods {
    @RegFood(modifier = {0.2f, 0.6f, 0f, 0f, 0.7f}, name = {"tomato", "and", "eggs"})
    ItemPFood TOMATO_AND_EGGS = new ItemPFood();
}
