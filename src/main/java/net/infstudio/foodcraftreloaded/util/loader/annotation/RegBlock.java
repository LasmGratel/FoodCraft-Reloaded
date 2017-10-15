package net.infstudio.foodcraftreloaded.util.loader.annotation;

import net.infstudio.foodcraftreloaded.util.NameBuilder;
import net.minecraft.item.ItemBlock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RegBlock {
    /**
     * The params to build registryName and unlocalizedName.
     * @see NameBuilder
     */
    String[] value();

    /**
     * All {@link net.minecraftforge.oredict.OreDictionary} values to be registered.
     */
    String[] oreDict() default {};


    //String prefix() default "block";

    /**
     *
     */
    Class<? extends ItemBlock> itemClass() default ItemBlock.class;
}
