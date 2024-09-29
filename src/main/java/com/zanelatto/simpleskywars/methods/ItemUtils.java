package com.zanelatto.simpleskywars.methods;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtils {

    public static void addItemToPlayer(Player player, String itemName, Material material, int slot) {
        // Cria o item
        ItemStack item = new ItemStack(material);

        // Define o nome do item
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(itemName);
            item.setItemMeta(meta);
        }

        // Adiciona o item na posição especificada
        player.getInventory().setItem(slot, item);
    }
}

