package com.github.ipecter.rtustudio.saveticket.listener;

import com.github.ipecter.rtustudio.saveticket.SaveTicket;
import kr.rtuserver.framework.bukkit.api.listener.RSListener;
import com.github.ipecter.rtustudio.saveticket.manager.StatusManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinQuit extends RSListener<SaveTicket> {

    private final StatusManager manager;

    public PlayerJoinQuit(SaveTicket plugin) {
        super(plugin);
        this.manager = plugin.getStatusManager();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        manager.addPlayer(e.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        manager.removePlayer(e.getPlayer().getUniqueId());
    }

}
