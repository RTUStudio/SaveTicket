package kr.rtustudio.saveticket.listener;

import kr.rtustudio.saveticket.SaveTicket;
import kr.rtustudio.saveticket.configuration.GlobalConfig;
import kr.rtustudio.saveticket.manager.StatusManager;
import kr.rtustudio.framework.bukkit.api.listener.RSListener;
import kr.rtustudio.framework.bukkit.api.registry.CustomItems;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@SuppressWarnings("unused")
public class ItemInteract extends RSListener<SaveTicket> {

    private final GlobalConfig config;
    private final StatusManager manager;

    public ItemInteract(SaveTicket plugin) {
        super(plugin);
        this.config = plugin.getConfiguration(GlobalConfig.class);
        this.manager = plugin.getStatusManager();
    }

    @EventHandler
    public void onItemInteract(PlayerInteractEvent e) {
        if (!List.of(Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK).contains(e.getAction())) return;
        if (config.isAutoProtect()) return;
        Player player = e.getPlayer();
        ItemStack itemStack = e.getItem();
        if (itemStack != null) {
            if (CustomItems.to(itemStack).equalsIgnoreCase(config.getItem())) {
                if (manager.getMap().getOrDefault(player.getUniqueId(), false)) {
                    chat().announce(player, getMessage().get(player, "alreadyUsed"));
                } else {
                    manager.activate(player.getUniqueId());
                    itemStack.setAmount(itemStack.getAmount() - 1);
                    chat().announce(player, getMessage().get(player, "useItem"));
                }
            }
        }
    }
}
