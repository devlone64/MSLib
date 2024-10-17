package io.github.devlone64.MSLib.command.annotation;

import io.github.devlone64.MSLib.command.enums.SenderType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Sender {
    SenderType type() default SenderType.CONSOLE;
}