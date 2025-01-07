package kr.rtuserver.keepinv.commands;

import kr.rtuserver.framework.bukkit.api.command.RSCommand;
import kr.rtuserver.framework.bukkit.api.command.RSCommandData;
import kr.rtuserver.keepinv.RSKeepInv;
import kr.rtuserver.keepinv.config.KeepInventoryConfig;

public class Command extends RSCommand<RSKeepInv> {

    private final KeepInventoryConfig config;

    public Command(RSKeepInv plugin) {
        super(plugin, "rski");
        this.config = plugin.getKeepInventoryConfig();
    }

    @Override
    public void reload(RSCommandData data) {
        config.reload();
    }

}
