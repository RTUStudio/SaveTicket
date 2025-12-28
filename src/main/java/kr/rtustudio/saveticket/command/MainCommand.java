package kr.rtustudio.saveticket.command;

import kr.rtustudio.saveticket.SaveTicket;
import kr.rtustudio.saveticket.configuration.GlobalConfig;
import kr.rtustudio.framework.bukkit.api.command.RSCommand;
import kr.rtustudio.framework.bukkit.api.command.RSCommandData;

public class MainCommand extends RSCommand<SaveTicket> {

    public MainCommand(SaveTicket plugin) {
        super(plugin, "saveticket");
    }

    @Override
    public void reload(RSCommandData data) {
        getPlugin().reloadConfiguration(GlobalConfig.class);
    }

}
