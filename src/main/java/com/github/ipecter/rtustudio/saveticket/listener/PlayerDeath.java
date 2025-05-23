package com.github.ipecter.rtustudio.saveticket.listener;

import com.github.ipecter.rtustudio.saveticket.SaveTicket;
import com.github.ipecter.rtustudio.saveticket.configuration.KeepInventoryConfig;
import com.github.ipecter.rtustudio.saveticket.manager.StatusManager;
import kr.rtuserver.framework.bukkit.api.listener.RSListener;
import kr.rtuserver.framework.bukkit.api.registry.CustomItems;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerDeath extends RSListener<SaveTicket> {

    private final KeepInventoryConfig config;
    private final StatusManager manager;

    public PlayerDeath(SaveTicket plugin) {
        super(plugin);
        this.config = plugin.getKeepInventoryConfig();
        this.manager = plugin.getStatusManager();
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();
        String item = config.getItem();
        if (config.isAutoProtect()) {
            ItemStack itemStack = CustomItems.from(item);
            if (itemStack == null) return;
            if (player.getInventory().containsAtLeast(itemStack, 1)) {
                manager.deactivate(player.getUniqueId());
                player.getInventory().removeItem(itemStack);
                e.setKeepInventory(config.isKeepInventory());
                e.setKeepLevel(config.isKeepLevel());
                e.getDrops().clear();
                e.setDroppedExp(0);
                chat().announce(player, getMessage().get(player, "trigger"));
            }
        } else {
            if (manager.getMap().getOrDefault(player.getUniqueId(), false)) {
                manager.deactivate(player.getUniqueId());
                e.setKeepInventory(config.isKeepInventory());
                e.setKeepLevel(config.isKeepLevel());
                e.getDrops().clear();
                e.setDroppedExp(0);
                chat().announce(player, getMessage().get(player, "trigger"));
            }
        }
    }

//    private int first(ItemStack item, ItemStack[] inventory) {
//        if (item == null) return -1;
//        for (int i = 0; i < inventory.length; i++) {
//            if (inventory[i] == null) continue;
//            if (ItemCompat.isSimilar(item, inventory[i])) return i;
//        }
//        return -1;
//    }
//
//    private void removeItemAnySlot(Player player, ItemStack item) {
//        Inventory inventory = player.getInventory();
//        Preconditions.checkArgument(item != null, "ItemStack cannot be null");
//        int toDelete = item.getAmount();
//        while (true) {
//            ItemStack[] toSearch = inventory.getContents();
//            int first = this.first(item, toSearch);
//            if (first == -1) {
//                item.setAmount(toDelete);
//                break;
//            } else {
//                ItemStack itemStack = inventory.getItem(first);
//                int amount = itemStack.getAmount();
//                if (amount <= toDelete) {
//                    toDelete -= amount;
//                    inventory.clear(first);
//                } else {
//                    itemStack.setAmount(amount - toDelete);
//                    inventory.setItem(first, itemStack);
//                    toDelete = 0;
//                }
//            }
//            if (toDelete <= 0) break;
//        }
//    }
}
