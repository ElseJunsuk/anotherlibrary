package com.anotherspectrum.anotherlibrary.menu;

import com.anotherspectrum.anotherlibrary.utils.StringUtil;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.TestOnly;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@TestOnly
class ItemBuilderTest {

    @Test
    void test() {
        ItemStack item = ItemBuilder.builder()
                .setType(Material.ACACIA_BOAT)
                .setAmount(1)
                .setDisplayName("<orange><bold>A-KEI-SIA-BOU-T!!!")
                .setLore(StringUtil.ellipsis("<gray><italic>The Boat."))
                .addEnchant(Enchantment.ARROW_INFINITE, 1)
                .addEnchant(Enchantment.MENDING, 5)
                .addEnchant(Enchantment.DURABILITY, 3)
                .addFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_PLACED_ON)
                .build();

        ItemStack someItem = ItemBuilder.builder()
                .setType(Material.ACACIA_BOAT)
                .setAmount(1)
                .setDisplayName("<orange><bold>A-KEI-SIA-BOU-T!!!")
                .setLore(StringUtil.ellipsis("<gray><italic>The Boat."))
                .addEnchant(Enchantment.DURABILITY, 3)
                .setGlowing()
                .addAllFlags()
                .build();
    }
}