package kr.rtustudio.saveticket;

import kr.rtustudio.saveticket.command.MainCommand;
import kr.rtustudio.saveticket.configuration.GlobalConfig;
import kr.rtustudio.saveticket.listener.ItemInteract;
import kr.rtustudio.saveticket.listener.PlayerDeath;
import kr.rtustudio.saveticket.listener.PlayerJoinQuit;
import kr.rtustudio.saveticket.manager.StatusManager;
import kr.rtustudio.saveticket.dependency.PlaceholderAPI;
import kr.rtustudio.framework.bukkit.api.RSPlugin;
import lombok.Getter;

public class SaveTicket extends RSPlugin {

    @Getter
    private StatusManager statusManager;

    @Override
    public void enable() {
        initStorage("Status");

        registerConfiguration(GlobalConfig.class, "Global");

        statusManager = new StatusManager(this);

        registerCommand(new MainCommand(this), true);

        registerEvent(new PlayerJoinQuit(this));
        registerEvent(new ItemInteract(this));
        registerEvent(new PlayerDeath(this));

        registerIntegration(new PlaceholderAPI(this));
    }

}
