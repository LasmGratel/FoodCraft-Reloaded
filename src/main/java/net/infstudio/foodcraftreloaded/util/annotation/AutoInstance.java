package net.infstudio.foodcraftreloaded.util.annotation;

import net.minecraftforge.fml.common.event.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AutoInstance {
    State value() default State.PREINIT;
    enum State {
        PREINIT(FMLPreInitializationEvent.class),
        INIT(FMLInitializationEvent.class),
        POSTINIT(FMLPostInitializationEvent.class),
        LOADCOMPLETE(FMLLoadCompleteEvent.class);

        Class<? extends FMLStateEvent> event;

        State(Class<? extends FMLStateEvent> event) {
            this.event = event;
        }

        public Class<? extends FMLStateEvent> getEvent() {
            return event;
        }
    }
}
