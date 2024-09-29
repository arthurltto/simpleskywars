package com.zanelatto.simpleskywars.methods;

import com.comphenix.protocol.ProtocolLibrary;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class Protection implements Listener {

    private final PlayerManager playerManager;

    public Protection(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }


    @EventHandler
    public void PlayerQuit(PlayerQuitEvent e) {

        Player p = e.getPlayer();
        PlayerState state = playerManager.getPlayerState(p);

        switch (state) {
            case LOBBY:
                e.setQuitMessage(null);
                break;
            case WAITING:
                e.setQuitMessage(null);
                break;
            case IN_GAME:
                e.setQuitMessage(null);
                break;
            default:
                e.setQuitMessage(null);
                break;
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        PlayerState state = playerManager.getPlayerState(p);

        switch (state) {
            case LOBBY:
                if (p.getGameMode() == GameMode.CREATIVE) return;
                 e.setCancelled(true);
                break;
            case WAITING:
                e.setCancelled(true);
                break;
            case IN_GAME:
                e.setCancelled(false);
                break;
            default:
                e.setCancelled(true);
                break;
        }
    }

    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        PlayerState state = playerManager.getPlayerState(p);

        switch (state) {
            case LOBBY:
                if (p.getGameMode() == GameMode.CREATIVE) return;
                e.setCancelled(true);
                break;
            case WAITING:
                e.setCancelled(true);
                break;
            case IN_GAME:
                e.setCancelled(false);
                break;
            default:
                e.setCancelled(true);
                break;
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        Player p = (Player)e.getEntity();
        PlayerState state = playerManager.getPlayerState(p);
                if (e.getEntity() instanceof Player) {
                    Player player = (Player)e.getEntity();
                    e.setCancelled(true);
                }
    }

    @EventHandler
    public void onDropEvent(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        PlayerState state = playerManager.getPlayerState(p);

        switch (state) {
            case LOBBY:
                e.setCancelled(true);
                break;
            case WAITING:
                e.setCancelled(true);
                break;
            case IN_GAME:
                e.setCancelled(false);
                break;
            default:
                e.setCancelled(true);
                break;
        }
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        if (e.toWeatherState()) {
            e.setCancelled(true);
        }

    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        String p = e.getPlayer().getDisplayName();
        String m = e.getMessage();
        String formattedMessage;
        formattedMessage = p + "§f: " + m;
        e.setFormat(formattedMessage);

    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.COMPASS) {
            event.setCancelled(true);
        }

    }
}
