package com.zanelatto.simpleskywars.methods;

import org.bukkit.entity.Player;

public class Game {
    private PlayerManager playerManager;

    public Game(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    public void startGame() {
        for (Player player : playerManager.getAllPlayers()) {
            playerManager.setPlayerState(player, PlayerState.IN_GAME);
            // Lógica adicional para iniciar o jogo
        }
    }

    public void enterWaitingRoom(Player player) {
        playerManager.setPlayerState(player, PlayerState.WAITING);
        // Lógica adicional para a sala de espera
    }
}
