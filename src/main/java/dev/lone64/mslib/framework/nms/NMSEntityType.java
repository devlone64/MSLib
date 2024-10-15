package dev.lone64.mslib.framework.nms;

import com.cryptomorin.xseries.XEntityType;
import org.bukkit.entity.EntityType;

public class NMSEntityType {

    public static EntityType getType(XEntityType entityType) {
        return entityType.get();
    }

    public static XEntityType or(XEntityType entityType, XEntityType orEntityType) {
        return entityType.or(orEntityType);
    }

    public static boolean isSupported(XEntityType entityType) {
        return entityType.isSupported();
    }

}