package dev.lone64.mslib.framework.builder.inventory.extension;

import dev.lone64.mslib.framework.builder.inventory.event.ClickEvent;
import dev.lone64.mslib.framework.builder.inventory.event.CloseEvent;

public interface EventExt {
    default void onClick(ClickEvent event) { }
    default void onClose(CloseEvent event) { }
}