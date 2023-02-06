package com.anotherspectrum.anotherlibrary.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

/**
 * 아이템 관련 도구가 제공됩니다.
 *
 * @since 0.2.5 - UPDATE FOR 0.3.0
 */
public final class ItemUtil {

    /**
     * 아이템의 이름을 문자열 형태로 가져옵니다.
     *
     * @param item 타겟 아이템
     * @return 문자열 {@link String} 형태의 아이템 이름
     */
    public static String getStringItemName(ItemStack item) {
        if (!item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
            return null;
        }
        return ((TextComponent) (Objects.requireNonNull(item.getItemMeta().displayName()))).content();
    }

    /**
     * 아이템의 이름을 {@link Component} 형태로 가져옵니다.
     *
     * @param item 타겟 아이템
     * @return {@link Component} 형태의 아이템 이름
     */
    public static Component getComponentItemName(ItemStack item) {
        if (!item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
            return null;
        }
        return StringUtil.format(getStringItemName(item));
    }

}
