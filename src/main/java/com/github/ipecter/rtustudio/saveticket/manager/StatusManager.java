package com.github.ipecter.rtustudio.saveticket.manager;

import com.github.ipecter.rtustudio.saveticket.SaveTicket;
import kr.rtuserver.framework.bukkit.api.platform.JSON;
import kr.rtuserver.framework.bukkit.api.storage.Storage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public class StatusManager {

    private final SaveTicket plugin;
    @Getter
    private final Map<UUID, Boolean> map = new HashMap<>();

    public void addPlayer(UUID uuid) {
        Storage storage = plugin.getStorage();
        storage.get("Status", Pair.of("uuid", uuid.toString())).thenAccept(result -> {
            if (result == null || result.isEmpty()) {
                storage.add("Status", JSON.of("uuid", uuid.toString()).append("keep", false).get());
                map.put(uuid, false);
            } else map.put(uuid, result.get(0).get("keep").getAsBoolean());
        });
    }

    public void removePlayer(UUID uuid) {
        map.remove(uuid);
    }

    public void activate(UUID uuid) {
        Storage storage = plugin.getStorage();
        storage.set("Status", Pair.of("uuid", uuid.toString()), JSON.of("keep", true).get());
        map.put(uuid, true);
    }

    public void deactivate(UUID uuid) {
        Storage storage = plugin.getStorage();
        storage.set("Status", Pair.of("uuid", uuid.toString()), JSON.of("keep", false).get());
        map.put(uuid, false);
    }

}
