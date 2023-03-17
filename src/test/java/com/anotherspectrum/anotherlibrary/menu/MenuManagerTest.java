package com.anotherspectrum.anotherlibrary.menu;

import com.anotherspectrum.anotherlibrary.menu.action.ActionHandler;
import com.anotherspectrum.anotherlibrary.menu.content.InventoryContent;
import com.anotherspectrum.anotherlibrary.utils.StringUtil;
import org.bukkit.entity.Player;

class MenuManagerTest extends MenuManager {

    public MenuManagerTest(Player player) {
        super(6, StringUtil.format("TEST"));

        applyMenuAction(ActionHandler.MENU_CLICK);
    }

    @Override
    public void contents(Player player, InventoryContent content) {
    }
}