package com.github.ipecter.rtustudio.saveticket.listener;

import com.github.ipecter.rtustudio.saveticket.SaveTicket;
import com.github.ipecter.rtustudio.saveticket.configuration.KeepInventoryConfig;
import com.github.ipecter.rtustudio.saveticket.manager.StatusManager;
import kr.rtuserver.framework.bukkit.api.listener.RSListener;
import kr.rtuserver.framework.bukkit.api.registry.CustomItems;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemInteract extends RSListener<SaveTicket> {

    private final KeepInventoryConfig config;
    private final StatusManager manager;

    public ItemInteract(SaveTicket plugin) {
        super(plugin);
        this.config = plugin.getKeepInventoryConfig();
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
