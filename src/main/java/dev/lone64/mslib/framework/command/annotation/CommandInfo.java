package dev.lone64.mslib.framework.command.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandInfo {
    String name();
    String usage() default "";
    String prefix() default "";
    String description() default "";
    String[] aliases() default {};
}