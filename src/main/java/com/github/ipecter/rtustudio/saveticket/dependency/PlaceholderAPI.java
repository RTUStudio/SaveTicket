package com.github.ipecter.rtustudio.saveticket.dependency;

import com.github.ipecter.rtustudio.saveticket.SaveTicket;
import com.github.ipecter.rtustudio.saveticket.manager.StatusManager;
import kr.rtuserver.framework.bukkit.api.dependency.RSPlaceholder;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PlaceholderAPI extends RSPlaceholder<SaveTicket> {

    private final StatusManager manager;

    public PlaceholderAPI(SaveTicket plugin) {
        super(plugin);
        this.manager = plugin.getStatusManager();
    }

    @Override
    public String request(OfflinePlayer offlinePlayer, String[] params) {
        if ("status".equalsIgnoreCase(params[0])) {
            if (manager.getMap().getOrDefault(offlinePlayer.getUniqueId(), false)) {
                if (offlinePlayer instanceof Player player) {
                    return message().get(player, "placeholder.active");
                } else return message().get("placeholder.active");
            } else {
                if (offlinePlayer instanceof Player player) {
                    return message().get(player, "placeholder.inactive");
                } else return message().get("placeholder.inactive");
            }
        }
        return "ERROR";
    }

}
