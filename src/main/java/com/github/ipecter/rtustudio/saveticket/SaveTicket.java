package com.github.ipecter.rtustudio.saveticket;

import com.github.ipecter.rtustudio.saveticket.config.KeepInventoryConfig;
import com.github.ipecter.rtustudio.saveticket.dependency.PlaceholderAPI;
import com.github.ipecter.rtustudio.saveticket.listeners.ItemInteract;
import com.github.ipecter.rtustudio.saveticket.listeners.PlayerDeath;
import kr.rtuserver.framework.bukkit.api.RSPlugin;
import com.github.ipecter.rtustudio.saveticket.commands.Command;
import com.github.ipecter.rtustudio.saveticket.listeners.PlayerJoinQuit;
import com.github.ipecter.rtustudio.saveticket.manager.StatusManager;
import lombok.Getter;

public class SaveTicket extends RSPlugin {

    @Getter
    private KeepInventoryConfig keepInventoryConfig;
    @Getter
    private StatusManager statusManager;

    private PlaceholderAPI placeholder;

    @Override
    public void enable() {
        getConfigurations().initStorage("Status");

        keepInventoryConfig = new KeepInventoryConfig(this);
        statusManager = new StatusManager(this);

        registerCommand(new Command(this), true);

        registerEvent(new PlayerJoinQuit(this));
        registerEvent(new ItemInteract(this));
        registerEvent(new PlayerDeath(this));

        if (getFramework().isEnabledDependency("PlaceholderAPI")) {
            placeholder = new PlaceholderAPI(this);
            placeholder.register();
        }
    }

    @Override
    public void disable() {
        if (getFramework().isEnabledDependency("PlaceholderAPI")) {
            placeholder.unregister();
        }
    }

}
