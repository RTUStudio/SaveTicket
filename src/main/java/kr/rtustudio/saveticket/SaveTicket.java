package kr.rtustudio.saveticket;

import kr.rtustudio.saveticket.command.MainCommand;
import kr.rtustudio.saveticket.configuration.GlobalConfig;
import kr.rtustudio.saveticket.handler.ItemInteract;
import kr.rtustudio.saveticket.handler.PlayerDeath;
import kr.rtustudio.saveticket.handler.PlayerJoinQuit;
import kr.rtustudio.saveticket.manager.StatusManager;
import kr.rtustudio.saveticket.integration.PlaceholderAPI;
import kr.rtustudio.configurate.model.ConfigPath;
import kr.rtustudio.framework.bukkit.api.RSPlugin;
import lombok.Getter;

public class SaveTicket extends RSPlugin {

    @Getter
    private static SaveTicket instance;

    @Getter
    private StatusManager statusManager;

    @Override
    protected void load() {
        instance = this;
    }

    @Override
    protected void enable() {
        registerStorage("Status");

        registerConfiguration(GlobalConfig.class, ConfigPath.of("Global"));

        statusManager = new StatusManager(this);

        registerCommand(new MainCommand(this), true);

        registerEvent(new PlayerJoinQuit(this));
        registerEvent(new ItemInteract(this));
        registerEvent(new PlayerDeath(this));

        registerIntegration(new PlaceholderAPI(this));
    }

}
