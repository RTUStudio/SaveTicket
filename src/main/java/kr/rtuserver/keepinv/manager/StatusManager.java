package kr.rtuserver.keepinv.manager;

import kr.rtuserver.framework.bukkit.api.storage.Storage;
import kr.rtuserver.framework.bukkit.api.utility.platform.JSON;
import kr.rtuserver.keepinv.RSKeepInv;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public class StatusManager {

    private final RSKeepInv plugin;
    @Getter
    private final Map<UUID, Boolean> map = new HashMap<>();

    public void addPlayer(UUID uuid) {
        Storage storage = plugin.getStorage();
        storage.add("Status", JSON.of("uuid", uuid.toString()).append("keep", false).get());
        map.put(uuid, false);
    }

    public void removePlayer(UUID uuid) {
        map.remove(uuid);
    }

    public void activate(UUID uuid) {
        Storage storage = plugin.getStorage();
        storage.set("Status", Pair.of("uuid", uuid.toString()), Pair.of("keep", true));
        map.put(uuid, true);
    }

    public void deactivate(UUID uuid) {
        Storage storage = plugin.getStorage();
        storage.set("Status", Pair.of("uuid", uuid.toString()), Pair.of("keep", false));
        map.put(uuid, false);
    }

}
