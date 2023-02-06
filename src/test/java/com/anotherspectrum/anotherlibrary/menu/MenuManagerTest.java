package com.anotherspectrum.anotherlibrary.menu;

import com.anotherspectrum.anotherlibrary.menu.content.InventoryContent;
import com.anotherspectrum.anotherlibrary.menu.item.ClickableItem;
import com.anotherspectrum.anotherlibrary.menu.pagination.SlotIterator;
import com.anotherspectrum.anotherlibrary.utils.StringUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

class MenuManagerTest extends MenuManager {

    public MenuManagerTest(Player player) {
        super(player, 6, StringUtil.format("TEST"));

    }

    @Override
    public void contents(Player player, InventoryContent content) {

    }

}