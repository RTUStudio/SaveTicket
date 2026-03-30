package kr.rtustudio.saveticket.integration;

import kr.rtustudio.framework.bukkit.api.integration.wrapper.PlaceholderArgs;
import kr.rtustudio.framework.bukkit.api.integration.wrapper.PlaceholderWrapper;
import kr.rtustudio.saveticket.SaveTicket;
import kr.rtustudio.saveticket.manager.StatusManager;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PlaceholderAPI extends PlaceholderWrapper<SaveTicket> {

    private final StatusManager manager;

    public PlaceholderAPI(SaveTicket plugin) {
        super(plugin);
        this.manager = plugin.getStatusManager();
    }

    @Override
    public String onRequest(OfflinePlayer offlinePlayer, PlaceholderArgs args) {
        if ("status".equalsIgnoreCase(args.get(0))) {
            if (manager.getMap().getOrDefault(offlinePlayer.getUniqueId(), false)) {
                if (offlinePlayer instanceof Player player) {
                    return message.get(player, "placeholder.active");
                } else return message.get("placeholder.active");
            } else {
                if (offlinePlayer instanceof Player player) {
                    return message.get(player, "placeholder.inactive");
                } else return message.get("placeholder.inactive");
            }
        }
        return "ERROR";
    }

}
