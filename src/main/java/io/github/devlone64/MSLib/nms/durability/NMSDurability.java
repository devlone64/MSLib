package io.github.devlone64.MSLib.nms.durability;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.iface.ReadWriteNBT;
import io.github.devlone64.MSLib.MSLib;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class NMSDurability {

    public static void damageItem(ItemStack item, int amount) {
        var damageAmount = getDurability(item) - amount;
        setDurability(item, damageAmount);
    }

    public static void repairItem(ItemStack item, int amount) {
        var repairAmount = getDurability(item) + amount;
        setDurability(item, repairAmount);
    }

    public static void createDurability(ItemStack item, int durability, int maxDurability) {
        try {
            if (item.getType().getMaxDurability() == 0) {
                throw new IllegalAccessException("Durability cannot be applied to this item.");
            }
        } catch (IllegalAccessException e) {
            MSLib.LOGGER.severe(e.getMessage());
            return;
        }

        var max = Math.max(durability, maxDurability);
        var cur = Math.max(0, Math.min(durability, max));
        final var itemMaxDurability = item.getType().getMaxDurability();
        NBT.modify(item, nbt -> {
            ReadWriteNBT compound = nbt.getOrCreateCompound(Constants.COMPOUND_TAG);
            compound.setInteger(Constants.DURABILITY, cur);
            compound.setInteger(Constants.MAX_DURABILITY, max);
            nbt.setInteger(Constants.DAMAGE, itemMaxDurability - (itemMaxDurability * cur / max));
        });
    }

    public static void setDurability(ItemStack item, int durability) {
        if (hasDurability(item)) {
            int max, cur;
            max = getMaxDurability(item);
            cur = Math.min(durability, max);
            final var itemMaxDurability = item.getType().getMaxDurability();
            if (cur <= 0 && item.getType() == Material.TRIDENT && itemMaxDurability != 249) {
                NBT.modify(item, nbt -> {
                    Objects.requireNonNull(nbt.getCompound(Constants.COMPOUND_TAG)).setInteger(Constants.DURABILITY, 1);
                    nbt.setInteger(Constants.DAMAGE, itemMaxDurability -1);
                });
                return;
            }
            NBT.modify(item, nbt -> {
                Objects.requireNonNull(nbt.getCompound(Constants.COMPOUND_TAG)).setInteger(Constants.DURABILITY, cur);
                nbt.setInteger(Constants.DAMAGE, itemMaxDurability - (itemMaxDurability * cur / max));
            });
            if (cur <= 0) item.setAmount(0);
        }
    }

    public static int getDurability(ItemStack item) {
        return NBT.get(item, nbt -> (int) Objects.requireNonNull(nbt.getCompound(Constants.COMPOUND_TAG)).getInteger(Constants.DURABILITY));
    }

    public static int getMaxDurability(ItemStack item) {
        return NBT.get(item, nbt -> (int) Objects.requireNonNull(nbt.getCompound(Constants.COMPOUND_TAG)).getInteger(Constants.MAX_DURABILITY));
    }

    public static boolean hasDurability(ItemStack item) {
        return item.getType().getMaxDurability() != 0 && NBT.get(item, nbt -> (boolean) nbt.hasTag(Constants.COMPOUND_TAG));
    }

}