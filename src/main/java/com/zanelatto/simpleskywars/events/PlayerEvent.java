package com.zanelatto.simpleskywars.events;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.zanelatto.simpleskywars.ActionBarRunnable;
import com.zanelatto.simpleskywars.Main;
import com.zanelatto.simpleskywars.methods.PlayerManager;
import com.zanelatto.simpleskywars.methods.PlayerState;
import com.zanelatto.simpleskywars.methods.SkyWarsScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class PlayerEvent implements Listener {
    private final PlayerManager playerManager;
    private final SkyWarsScoreboardManager scoreboardManager;
    private final ProtocolManager protocolManager;


    public PlayerEvent(PlayerManager playerManager, SkyWarsScoreboardManager scoreboardManager) {
        this.playerManager = playerManager;
        this.scoreboardManager = scoreboardManager;
        this.protocolManager = ProtocolLibrary.getProtocolManager();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        e.setJoinMessage(null);
        playerManager.setPlayerState(p, PlayerState.LOBBY);
        scoreboardManager.startUpdatingScoreboard(p, playerManager);
        updateTabList(p);
        new ActionBarRunnable(p, "§6www.simpleskywars.com.br", Main.getInstance()).runTaskTimer(Main.getInstance(), 0, 20);
    }

    public void updateTabList(Player player) {
        PlayerState state = playerManager.getPlayerState(player);
        String header;
        String footer;

        switch (state) {
            case LOBBY:
                header = "\n\n§fBem-vindo ao §6Lobby§f!\n\n";
                footer = "\n\n§6Informações sobre o servidor\n§bmcga.me\n§bmcga.me/forum\n";
                break;
            case WAITING:
                header = "\n\n§fBem-vindo a \n§6Sala de Espera§f!\n\n";
                footer = "\n\n§6Informações sobre o servidor\n§bmcga.me\n§bmcga.me/forum\n";
                break;
            case IN_GAME:
                header = "\n\n§fBem-vindo ao §6SkyWars§f!\n\n";
                footer = "\n\n§6Informações sobre o servidor\n§bmcga.me\n§bmcga.me/forum\n";
                break;
            default:
                header = "";
                footer = "";
                break;
        }

        // Criando o pacote para o cabeçalho e rodapé
        PacketContainer packet = protocolManager.createPacket(PacketType.Play.Server.PLAYER_LIST_HEADER_FOOTER);
        packet.getChatComponents().write(0, WrappedChatComponent.fromText(header));
        packet.getChatComponents().write(1, WrappedChatComponent.fromText(footer));

        try {
            protocolManager.sendServerPacket(player, packet);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
        private Class<?> getNMSClass(String name) throws ClassNotFoundException {
            String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
            return Class.forName("net.minecraft.server." + version + "." + name);
        }

}
