package kr.rtuserver.keepinv;

import kr.rtuserver.framework.bukkit.api.RSPlugin;
import kr.rtuserver.keepinv.commands.Command;
import kr.rtuserver.keepinv.config.KeepInventoryConfig;
import kr.rtuserver.keepinv.dependency.PlaceholderAPI;
import kr.rtuserver.keepinv.listeners.ItemInteract;
import kr.rtuserver.keepinv.listeners.PlayerDeath;
import kr.rtuserver.keepinv.listeners.PlayerJoinQuit;
import kr.rtuserver.keepinv.manager.StatusManager;
import lombok.Getter;

public class RSKeepInv extends RSPlugin {

    @Getter
    private static RSKeepInv instance;
    @Getter
    private KeepInventoryConfig keepInventoryConfig;
    @Getter
    private StatusManager statusManager;

    private PlaceholderAPI placeholder;

    @Override
    public void load() {
        instance = this;
    }

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
