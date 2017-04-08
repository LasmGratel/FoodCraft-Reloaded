package net.infstudio.foodcraftreloaded.utils.loader.annotation;

import net.infstudio.foodcraftreloaded.utils.NameBuilder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RegItem {
    /**
     * The params to build registryName and unlocalizedName.
     * @see NameBuilder
     */
    String[] value();

    /**
     * All {@link net.minecraftforge.oredict.OreDictionary} values to be registered.
     */
    String[] oreDict() default {};

    /**
     * Add prefix on modifier
     * Example: wandIron -> itemWandIron
     */
    //String prefix() default "item";
}
