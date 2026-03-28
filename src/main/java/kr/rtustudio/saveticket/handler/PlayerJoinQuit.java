package kr.rtustudio.saveticket.handler;

import kr.rtustudio.saveticket.SaveTicket;
import kr.rtustudio.saveticket.manager.StatusManager;
import kr.rtustudio.framework.bukkit.api.listener.RSListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@SuppressWarnings("unused")
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
