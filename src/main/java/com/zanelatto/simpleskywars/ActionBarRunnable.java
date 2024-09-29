package com.zanelatto.simpleskywars;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.zanelatto.simpleskywars.Main;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ActionBarRunnable extends BukkitRunnable {

    private final Player player;
    private final String message;
    private final ProtocolManager protocolManager;

    public ActionBarRunnable(Player player, String message, Main plugin) {
        this.player = player;
        this.message = message;
        this.protocolManager = ProtocolLibrary.getProtocolManager();
    }

    @Override
    public void run() {
        sendActionBar();
    }

    private void sendActionBar() {
        try {
            PacketContainer packet = protocolManager.createPacket(PacketType.Play.Server.CHAT);
            packet.getChatComponents().write(0, WrappedChatComponent.fromText(message));
            packet.getBytes().write(0, (byte) 2); // 2 para ActionBar
            protocolManager.sendServerPacket(player, packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
