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

import java.util.ArrayList;
import java.util.List;

/**
 * 아이템 제작에 필요한 도구가 제공됩니다.
 *
 * @since 0.2.5 - UPDATE FOR 0.4.5
 */
public class ItemBuilder {

    /**
     * 만들어져 있던 아이템을 수정할 수 있는 빌더를 개행합니다.
     *
     * @param item 수정할 아이템
     * @return {@link Builder}
     */
    public static Builder editItem(ItemStack item) {
        return new Builder(item);
    }

    /**
     * 아이템 빌더를 개행합니다.
     *
     * @return {@link Builder}
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * 아이템 빌더를 위한 클래스입니다.
     * <p>빌드(build()) 시 {@link ItemStack} 를 반환합니다.</p>
     */
    public static final class Builder {

        private final ItemStack DEFAULT_ITEM = new ItemStack(Material.BARRIER);

        private final ItemStack item;
        private final ItemMeta meta;

        /**
         * 빌더 클래스에서 싱글톤을 사용했습니다.
         * <p>빈 {@link ItemStack} 에 기본 설정을 주입합니다.</p>
         */
        private Builder() {
            this.item = DEFAULT_ITEM;
            this.meta = item.getItemMeta();
        }

        private Builder(ItemStack item) {
            this.item = item;
            this.meta = item.getItemMeta();
            ;
        }

        /**
         * 아이템의 타입을 설정합니다.
         *
         * @param type {@link Material}
         * @return {@link Builder}
         */
        public Builder setType(Material type) {
            Preconditions.checkNotNull(type, "[AnotherLibrary] 아이템의 타입을 지정해야 합니다.");
            item.setType(type);
            return this;
        }

        /**
         * 아이템의 갯수를 설정합니다.
         *
         * @param amount 아이템 갯수
         * @return {@link Builder}
         */
        public Builder setAmount(int amount) {
            if (amount < 0) {
                return null;
            }
            item.setAmount(amount);
            return this;
        }

        /**
         * 아이템의 커스텀 이름을 설정합니다.
         *
         * @param name 설정할 아이템의 이름
         * @return {@link Builder}
         */
        public Builder setDisplayName(String name) {
            Preconditions.checkNotNull(name, "[AnotherLibrary] 아이템의 이름을 지정해야 합니다.");
            meta.displayName(StringUtil.format(name));
            return this;
        }

        /**
         * 아이템의 커스텀 이름을 설정합니다.
         *
         * @param name 설정할 아이템의 이름
         * @return {@link Builder}
         */
        public Builder setDisplayName(Component name) {
            Preconditions.checkNotNull(name, "[AnotherLibrary] 아이템의 이름을 지정해야 합니다.");
            meta.displayName(name);
            return this;
        }

        /**
         * 아이템에 로어(설명)을 추가합니다.
         *
         * @param lore {@link Component} 인자를 포함한 {@link List}
         * @return {@link Builder}
         */
        public Builder setLore(List<Component> lore) {
            Preconditions.checkNotNull(lore, "[AnotherLibrary] 아이템의 로어를 지정해야 합니다.");
            meta.lore(lore);
            return this;
        }

        /**
         * 아이템에 로어(설명)을 추가합니다.
         *
         * @param lore {@link Component} 인자를 포함한 {@link List}
         * @return {@link Builder}
         */
        public Builder setLore(String... lore) {
            Preconditions.checkNotNull(lore, "[AnotherLibrary] 아이템의 로어를 지정해야 합니다.");
            List<Component> list = new ArrayList<>();
            for (String key : lore)
                list.add(StringUtil.format(key));
            meta.lore(list);
            return this;
        }

        /**
         * 아이템에 인첸트를 추가합니다.
         *
         * @param enchantment {@link Enchantment}
         * @param level       해당 인첸트의 레벨
         * @return {@link Builder}
         */
        public Builder addEnchant(Enchantment enchantment, int level) {
            Preconditions.checkNotNull(enchantment, "[AnotherLibrary] 아이템에 추가할 인첸트를 지정해야 합니다.");
            item.addUnsafeEnchantment(enchantment, level);
            return this;
        }

        /**
         * 아이템에 플래그를 추가합니다.
         *
         * @param flags Ellipsis {@link ItemFlag}
         * @return {@link Builder}
         */
        public Builder addFlags(ItemFlag... flags) {
            Preconditions.checkNotNull(flags, "[AnotherLibrary] 아이템의 플레그를 지정해야 합니다.");
            meta.addItemFlags(flags);
            return this;
        }

        /**
         * 아이템에 인첸트 효과를 추가합니다.
         * <p>단, 자동적으로 {@link ItemFlag#HIDE_ENCHANTS} 플래그가 적용됩니다.</p>
         *
         * @return {@link Builder}
         */
        public Builder setGlowing() {
            addEnchant(Enchantment.DURABILITY, 1);
            return addFlags(ItemFlag.HIDE_ENCHANTS);
        }

        /**
         * 아이템에 모델 데이터를 적용합니다.
         *
         * @param value 모델 데이터
         * @return {@link Builder}
         */
        public Builder setResourceData(int value) {
            meta.setCustomModelData(value);
            return this;
        }

        /**
         * 아이템에 Vanilla 에 존재하는 모든 플래그를 추가합니다.
         *
         * @return {@link Builder}
         */
        public Builder addAllFlags() {
            return addFlags(
                    ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_PLACED_ON,
                    ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_DYE,
                    ItemFlag.HIDE_UNBREAKABLE);
        }

        /**
         * 빌드한 {@link ItemBuilder} 아이템을 최종적으로 {@link ItemStack} 형태로 반환합니다.
         *
         * @return {@link ItemStack}
         */
        public ItemStack build() {
            if (item == null)
                return DEFAULT_ITEM;
            item.setItemMeta(meta);
            return item;
        }
    }


}
