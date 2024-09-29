package com.zanelatto.simpleskywars.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemClickListener implements Listener {

    private final JavaPlugin plugin;

    public ItemClickListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        // Verifica se o jogador clicou com o botão direito
        if (event.getAction().toString().contains("RIGHT")) {
            ItemStack item = event.getItem();
            // Verifica se o item é o correto
            if (item != null && item.getType() == Material.STORAGE_MINECART && item.hasItemMeta()) {
                ItemMeta meta = item.getItemMeta();
                if (meta.hasDisplayName() && meta.getDisplayName().equals("§eMenu")) {
                    // Abre o inventário
                    openCustomInventory(event.getPlayer());
                }
            }
        }
    }

    private void openCustomInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 27, "Menu de Opções");

        // Cabeça (Perfil)
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        ItemMeta headMeta = head.getItemMeta();
        headMeta.setDisplayName("§aPerfil");
        head.setItemMeta(headMeta);
        inventory.setItem(10, head);

        // Item Frame (Info)
        ItemStack itemFrame = new ItemStack(Material.ITEM_FRAME);
        ItemMeta frameMeta = itemFrame.getItemMeta();
        frameMeta.setDisplayName("§bInformações");
        itemFrame.setItemMeta(frameMeta);
        inventory.setItem(11, itemFrame);

        // Disco (Recriação)
        ItemStack disk = new ItemStack(Material.RECORD_11);
        ItemMeta diskMeta = disk.getItemMeta();
        diskMeta.setDisplayName("§dRecriação");
        disk.setItemMeta(diskMeta);
        inventory.setItem(13, disk);

        // Esmeralda (Loja)
        ItemStack emerald = new ItemStack(Material.EMERALD);
        ItemMeta emeraldMeta = emerald.getItemMeta();
        emeraldMeta.setDisplayName("§eLoja");
        emerald.setItemMeta(emeraldMeta);
        inventory.setItem(15, emerald);

        // Papel (Estatísticas)
        ItemStack paper = new ItemStack(Material.PAPER);
        ItemMeta paperMeta = paper.getItemMeta();
        paperMeta.setDisplayName("§7Estatísticas");
        paper.setItemMeta(paperMeta);
        inventory.setItem(16, paper);

        // Abre o inventário para o jogador
        player.openInventory(inventory);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        // Verifica se o inventário clicado é o nosso
        if (event.getView().getTitle().equals("Menu de Opções")) {
            // Cancela o evento de clique
            event.setCancelled(true);
        }
    }
}
