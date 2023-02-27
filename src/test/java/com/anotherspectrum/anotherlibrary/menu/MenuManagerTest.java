package com.anotherspectrum.anotherlibrary.menu;

import com.anotherspectrum.anotherlibrary.menu.content.InventoryContent;
import com.anotherspectrum.anotherlibrary.utils.StringUtil;
import org.bukkit.entity.Player;

class MenuManagerTest extends MenuManager {

    public MenuManagerTest(Player player) {
        super(6, StringUtil.format("TEST"));

    }

    @Override
    public void contents(Player player, InventoryContent content) {
    }

}