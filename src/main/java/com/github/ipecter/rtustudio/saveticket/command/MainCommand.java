package com.github.ipecter.rtustudio.saveticket.command;

import com.github.ipecter.rtustudio.saveticket.SaveTicket;
import com.github.ipecter.rtustudio.saveticket.configuration.KeepInventoryConfig;
import kr.rtuserver.framework.bukkit.api.command.RSCommand;
import kr.rtuserver.framework.bukkit.api.command.RSCommandData;

public class MainCommand extends RSCommand<SaveTicket> {

    private final KeepInventoryConfig config;

    public MainCommand(SaveTicket plugin) {
        super(plugin, "saveticket");
        this.config = plugin.getKeepInventoryConfig();
    }

    @Override
    public void reload(RSCommandData data) {
        config.reload();
    }

}
