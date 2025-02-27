package com.github.ipecter.rtustudio.saveticket.commands;

import com.github.ipecter.rtustudio.saveticket.SaveTicket;
import com.github.ipecter.rtustudio.saveticket.config.KeepInventoryConfig;
import kr.rtuserver.framework.bukkit.api.command.RSCommand;
import kr.rtuserver.framework.bukkit.api.command.RSCommandData;

public class Command extends RSCommand<SaveTicket> {

    private final KeepInventoryConfig config;

    public Command(SaveTicket plugin) {
        super(plugin, "rski");
        this.config = plugin.getKeepInventoryConfig();
    }

    @Override
    public void reload(RSCommandData data) {
        config.reload();
    }

}
