package kr.rtustudio.saveticket.command;

import kr.rtustudio.framework.bukkit.api.command.CommandArgs;
import kr.rtustudio.framework.bukkit.api.command.RSCommand;
import kr.rtustudio.saveticket.SaveTicket;
import kr.rtustudio.saveticket.configuration.GlobalConfig;

public class MainCommand extends RSCommand<SaveTicket> {

    public MainCommand(SaveTicket plugin) {
        super(plugin, "saveticket");
    }

    @Override
    public void reload(CommandArgs args) {
        plugin.reloadConfiguration(GlobalConfig.class);
    }

}
