package com.anotherspectrum.anotherlibrary.menu;

import com.anotherspectrum.anotherlibrary.utils.StringUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * 아이템을 간편하게 제작할 수 있습니다.
 *
 * @since 0.1.2
 * @update 0.2.6-SNAPSHOT/10-11-22
 */
public class ItemCreator {

    /**
     * 해당 타입으로 아이템을 생성합니다.
     *
     * @param material 아이템 타입
     * @return {@link ItemStack}
     */
    public static ItemStack create(Material material) {
        return new ItemStack(material);
    }

    /**
     * 해당 타입으로 아이템을 생성합니다.
     *
     * @param material 아이템 타입
     * @return {@link ItemStack}
     */
    public static ItemStack create(Material material, int amount) {
        return new ItemStack(material, amount);
    }

    /**
     * 해당 타입의 아이템에 이름을 부여합니다.
     *
     * @param material
     * @param name     아이템 이름
     * @return {@link ItemStack}
     */
    public static ItemStack create(Material material, int amount, Component name) {
        ItemStack item = create(material, amount);
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return item;
        meta.displayName(name);
        item.setItemMeta(meta);
        return item;
    }

    /**
     * 아이템 타입과 이름, 설명을 부여합니다.
     *
     * @param material
     * @param name
     * @param lore     아이템 설명
     * @return {@link ItemStack}
     */
    public static ItemStack create(Material material, int amount, Component name, List<Component> lore) {
        ItemStack item = create(material, amount, name);
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return item;
        meta.lore(lore);
        item.setItemMeta(meta);
        return item;
    }

    /**
     * 아이템 타입과 이름, 설명을 부여하고
     * 부수적으로 플래그(Flags)를 숨길 것인지
     * 설정합니다.
     *
     * @param material
     * @param name
     * @param lore
     * @param hideFlags 플래그 하이딩
     * @return {@link ItemStack}
     */
    public static ItemStack create(Material material, int amount, Component name, List<Component> lore, boolean hideFlags) {
        ItemStack item = create(material, amount, name, lore);
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return item;
        if (hideFlags) meta.addItemFlags(ItemFlag.values());
        else meta.removeItemFlags(ItemFlag.values());
        item.setItemMeta(meta);
        return item;
    }

    /**
     * 아이템 타입과 이름, 설명을 부여하고
     * 플래그를 숨길 것인지 설정한 뒤 최종적으로
     * 아이템이 인첸트 효과를 보일 것인지 설정합니다.
     *
     * @param material
     * @param name
     * @param lore
     * @param hideFlags
     * @param isGlowing 아이템 인첸팅 효과
     * @return {@link ItemStack}
     */
    public static ItemStack create(Material material, int amount, Component name, List<Component> lore, boolean hideFlags, boolean isGlowing) {
        ItemStack item = create(material, amount, name, lore, hideFlags);
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return item;
        if (isGlowing) {
            item.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        return item;
    }

    /**
     * 아이템 타입과 이름, 설명을 부여하고
     * 플래그와 인첸트 효과를 보일 것인지 설정하고
     * 인첸트를 부여할 수 있습니다.
     *
     * @param material
     * @param name
     * @param lore
     * @param hideFlags
     * @param isGlowing
     * @param enchantment 인첸트
     * @return {@link ItemStack}
     */
    public static ItemStack create(Material material, int amount, Component name, List<Component> lore, boolean hideFlags, boolean isGlowing, Enchantment enchantment) {
        ItemStack item = create(material, amount, name, lore, hideFlags, isGlowing);
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return item;
        item.addUnsafeEnchantment(enchantment, 1);
        return item;
    }

    /**
     * 아이템 타입과 이름, 설명을 부여하고
     * 플래그와 인첸트 효과를 보일 것인지 설정하고
     * 인첸트와 그 레벨을 부여할 수 있습니다.
     *
     * @param material
     * @param name
     * @param lore
     * @param hideFlags
     * @param isGlowing
     * @param enchantment
     * @param level       인첸트 레벨
     * @return {@link ItemStack}
     */
    public static ItemStack create(Material material, int amount, Component name, List<Component> lore, boolean hideFlags, boolean isGlowing, Enchantment enchantment, int level) {
        ItemStack item = create(material, amount, name, lore, hideFlags, isGlowing);
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return item;
        item.addUnsafeEnchantment(enchantment, level);
        return item;
    }

                                                    /* ANCHOR_string_creator; */

    /**
     * 해당 타입의 아이템에 이름을 부여합니다.
     *
     * 심플하게 문자열로 아이템의 이름, 로어를 설정해주는 메소드입니다.
     * 아이템의 Flags 를 숨기거나, 인첸트를 부여하거나, Glowing 을 추가하려면
     * 추가 메소드를 사용하세요.
     *
     * @param material
     * @param name     아이템 이름
     * @return {@link ItemStack}
     */
    public static ItemStack create(Material material, int amount, String name) {
        ItemStack item = create(material, amount);
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return item;
        meta.displayName(StringUtil.format(name));
        item.setItemMeta(meta);
        return item;
    }

    /**
     * 아이템 타입과 이름, 설명을 부여합니다.
     *
     * 심플하게 문자열로 아이템의 이름, 로어를 설정해주는 메소드입니다.
     * 아이템의 Flags 를 숨기거나, 인첸트를 부여하거나, Glowing 을 추가하려면
     * 추가 메소드를 사용하세요.
     *
     * @param material
     * @param name
     * @param lore     아이템 설명
     * @return {@link ItemStack}
     */
    public static ItemStack create(Material material, int amount, String name, String... lore) {
        ItemStack item = create(material, amount, name);
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return item;
        List<Component> cList = new ArrayList<>();
        for (String list : lore)
            cList.add(StringUtil.format(list));
        meta.lore(cList);
        item.setItemMeta(meta);
        return item;
    }

                                                    /* ANCHOR_string_creator; */

    /**
     * 갑옷 메타데이터를 ItemStack[] (배열)형태로 컨버팅합니다.
     * ex) public static final ItemStack[] ARMOR = equipArmor(ItemStack...);
     * 사용할 땐 플레이어(Player) 클래스의 'equip' 메소드를 사용하세요.
     *
     * @param helmet     헬멧
     * @param chestplate 상의
     * @param leggings   하의
     * @param boots      신발
     * @return {@link ItemStack}
     */
    public static ItemStack[] equipArmor(ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
        ItemStack[] armor = new ItemStack[4];
        armor[3] = helmet;
        armor[2] = chestplate;
        armor[1] = leggings;
        armor[0] = boots;
        return armor;
    }

    /**
     * WRITTEN_BOOK 아이템에 내용을 삽입 할 수 있습니다.
     *
     * @param title   책의 제목
     * @param author  책의 저자
     * @param content 책의 내용
     * @return {@link ItemStack}
     */
    public static ItemStack createBook(String title, String author, List<Component> content) {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) book.getItemMeta();
        bookMeta.setAuthor(author);
        bookMeta.title(StringUtil.format(title));
        bookMeta.pages(content);
        book.setItemMeta(bookMeta);
        return book;
    }

    /**
     * 해당 플레이어(오프라인 가능)의 머리를 {@link ItemStack} 형태로
     * 반환합니다.
     *
     * @param player 타겟
     * @param name   아이템 이름
     * @param lore   아이템 로어
     * @return {@link ItemStack}
     */
    public static ItemStack createSkull(OfflinePlayer player, Component name, List<Component> lore) {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwningPlayer(player);
        meta.displayName(name);
        meta.lore(lore);
        skull.setItemMeta(meta);
        return skull;
    }

    /**
     * 아이템에 인첸트와 그 레벨을 부여합니다.
     *
     * @param item
     * @param enchantment
     * @param level
     * @return
     */
    public static ItemStack addEnchant(ItemStack item, Enchantment enchantment, int level) {
        item.addUnsafeEnchantment(enchantment, level);
        return item;
    }

    /**
     * 해당 아이템의 모든 Flags 를 가려줍니다.
     *
     * @see #removeFlag(ItemStack, ItemFlag...)
     * @param item 타겟 아이템
     * @return {@link ItemStack}
     */
    public static ItemStack removeFlag(ItemStack item) {
        return removeFlag(item,
                ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DYE, ItemFlag.HIDE_DESTROYS,
                ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE,
                ItemFlag.HIDE_POTION_EFFECTS);
    }

    /**
     * 해당 아이템의 특정한 Flags 를 가려줍니다.
     *
     * @param item
     * @param flags
     * @return
     */
    public static ItemStack removeFlag(ItemStack item, ItemFlag... flags) {
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(flags);
        item.setItemMeta(meta);
        return item;
    }

}
