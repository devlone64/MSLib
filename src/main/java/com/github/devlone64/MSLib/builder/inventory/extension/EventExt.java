package com.github.devlone64.MSLib.builder.inventory.extension;

import com.github.devlone64.MSLib.builder.inventory.event.ClickEvent;
import com.github.devlone64.MSLib.builder.inventory.event.CloseEvent;

public interface EventExt {
    default void onClick(ClickEvent event) { }
    default void onClose(CloseEvent event) { }
}