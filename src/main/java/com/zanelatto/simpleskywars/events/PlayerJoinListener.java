package com.zanelatto.simpleskywars.events;

import com.zanelatto.simpleskywars.commands.SetLobbyCommand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    private final SetLobbyCommand setLobbyCommand;

    public PlayerJoinListener(SetLobbyCommand setLobbyCommand) {
        this.setLobbyCommand = setLobbyCommand;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (setLobbyCommand.getLobbyLocation() != null) {
            // Teleporta o jogador para o lobby definido
            event.getPlayer().teleport(setLobbyCommand.getLobbyLocation());
            event.getPlayer().sendMessage("Você foi teleportado para o lobby definido!");
        } else {
            event.getPlayer().sendMessage("O lobby não foi definido ainda.");
        }
    }
}
