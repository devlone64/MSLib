package com.github.devlone64.MSLib.container;

import com.github.devlone64.MSLib.builder.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemContainer extends ItemBuilder {

    public ItemContainer(ItemStack item) {
        super(item);
    }

    public ItemContainer(Material type) {
        super(type);
    }

    public boolean isSameItem(ItemStack item) {
        if (item == null || getItemStack() == null)
            return false;
        ItemMeta metaOne = getItemStack().getItemMeta();
        ItemMeta metaTwo = item.getItemMeta();
        return metaOne != null && metaTwo != null
                && metaOne.hasDisplayName() && metaTwo.hasDisplayName()
                && metaOne.getDisplayName().equalsIgnoreCase(metaTwo.getDisplayName());
    }

}