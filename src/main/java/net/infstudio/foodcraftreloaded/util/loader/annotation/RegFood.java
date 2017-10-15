package net.infstudio.foodcraftreloaded.util.loader.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RegFood {
    int amount() default Integer.MIN_VALUE;
    float[] modifier() default {};
    String[] name();
    String[] oreDict() default {};
}
