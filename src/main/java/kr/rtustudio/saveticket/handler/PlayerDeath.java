package kr.rtustudio.saveticket.handler;

import kr.rtustudio.framework.bukkit.api.listener.RSListener;
import kr.rtustudio.framework.bukkit.api.registry.CustomItems;
import kr.rtustudio.saveticket.SaveTicket;
import kr.rtustudio.saveticket.configuration.GlobalConfig;
import kr.rtustudio.saveticket.manager.StatusManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("unused")
public class PlayerDeath extends RSListener<SaveTicket> {

    private final GlobalConfig config;
    private final StatusManager manager;

    public PlayerDeath(SaveTicket plugin) {
        super(plugin);
        this.config = plugin.getConfiguration(GlobalConfig.class);
        this.manager = plugin.getStatusManager();
    }

    @EventHandler
    private void onPlayerDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();
        String item = config.getItem();
        
        if (config.isAutoProtect()) {
            ItemStack itemStack = CustomItems.from(item);
            if (itemStack == null) return;
            if (!player.getInventory().containsAtLeast(itemStack, 1)) return;
            
            player.getInventory().removeItem(itemStack);
        } else {
            if (!manager.getMap().getOrDefault(player.getUniqueId(), false)) return;
        }

        manager.deactivate(player.getUniqueId());
        e.setKeepInventory(config.isKeepInventory());
        e.setKeepLevel(config.isKeepLevel());
        e.getDrops().clear();
        e.setDroppedExp(0);
        notifier.announce(player, message.get(player, "trigger"));
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
