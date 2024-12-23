package kr.rtuserver.keepinv.listeners;

import kr.rtuserver.framework.bukkit.api.listener.RSListener;
import kr.rtuserver.keepinv.RSKeepInv;
import kr.rtuserver.keepinv.manager.StatusManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinQuit extends RSListener<RSKeepInv> {

    private final StatusManager manager;

    public PlayerJoinQuit(RSKeepInv plugin) {
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
