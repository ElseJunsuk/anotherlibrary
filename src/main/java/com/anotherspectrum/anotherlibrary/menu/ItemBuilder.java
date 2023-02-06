package com.anotherspectrum.anotherlibrary.menu;

import com.anotherspectrum.anotherlibrary.utils.StringUtil;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * 아이템 제작에 필요한 도구가 제공됩니다.
 *
 * @since 0.2.5 - UPDATE FOR 0.3.0
 */
public class ItemBuilder {

    private ItemBuilder() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private static final ItemStack DEFAULT_ITEM = new ItemStack(Material.BARRIER);

        private final ItemStack item;
        private final ItemMeta meta;

        private Builder() {
            this.item = DEFAULT_ITEM;
            this.meta = item.getItemMeta();
        }

        public Builder setType(Material type) {
            Preconditions.checkNotNull(type, "[AnotherLibrary] 아이템의 타입을 지정해야 합니다.");
            item.setType(type);
            return this;
        }

        public Builder setAmount(int amount) {
            if (amount < 1) {
                return null;
            }
            item.setAmount(amount);
            return this;
        }

        public Builder setDisplayName(String name) {
            Preconditions.checkNotNull(name, "[AnotherLibrary] 아이템의 이름을 지정해야 합니다.");
            meta.displayName(StringUtil.format(name));
            return this;
        }

        public Builder setLore(List<Component> lore) {
            Preconditions.checkNotNull(lore, "[AnotherLibrary] 아이템의 로어를 지정해야 합니다.");
            meta.lore(lore);
            return this;
        }

        public Builder addEnchant(Enchantment enchantment, int level) {
            Preconditions.checkNotNull(enchantment, "[AnotherLibrary] 아이템에 추가할 인첸트를 지정해야 합니다.");
            item.addUnsafeEnchantment(enchantment, level);
            return this;
        }

        public Builder addFlags(ItemFlag... flags) {
            Preconditions.checkNotNull(flags, "[AnotherLibrary] 아이템의 플레그를 지정해야 합니다.");
            meta.addItemFlags(flags);
            return this;
        }

        public Builder setGlowing() {
            addEnchant(Enchantment.DURABILITY, 1);
            return addFlags(ItemFlag.HIDE_ENCHANTS);
        }

        public Builder addAllFlags() {
            return addFlags(
                    ItemFlag.HIDE_ENCHANTS,   ItemFlag.HIDE_DESTROYS,       ItemFlag.HIDE_PLACED_ON,
                    ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_DYE,
                    ItemFlag.HIDE_UNBREAKABLE);
        }

        public ItemStack build() {
            if (item == null)
                return DEFAULT_ITEM;
            item.setItemMeta(meta);
            return item;
        }
    }


}
