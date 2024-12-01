package kr.rtuserver.keepinv.config;

import kr.rtuserver.framework.bukkit.api.RSPlugin;
import kr.rtuserver.framework.bukkit.api.config.RSConfiguration;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KeepInventoryConfig extends RSConfiguration {

    private String item = "minecraft:barrier";
    private boolean autoProtect = true;
    private boolean keepInventory = true;
    private boolean keepLevel = true;

    public KeepInventoryConfig(RSPlugin plugin) {
        super(plugin, "KeepInventory.yml", 1);
        setup(this);
    }

    private void init() {
        item = getString("item", item, """
                Inventory protection trigger item
                인벤토리 보호 트리거 아이템
                
                Vanilla: minecraft:item_type
                Itemsadder: itemsadder:category:item
                Oraxen: oraxen:item
                Nexo: nexo:item""");
        autoProtect = getBoolean("autoProtect", autoProtect, """
                true: Consume the item by right-clicking to register inventory save
                true: 아이템을 우클릭으로 소모하여 인벤 세이브를 등록합니다
                
                false: Automatically consume the item in the inventory to protect the inventory
                false: 인벤토리에 가지고 있으면 자동으로 소모하여 인벤토리를 보호합니다""");
        keepInventory = getBoolean("keepInventory", keepInventory, """
                Protects inventory items
                인벤토리 아이템을 보호합니다""");
        keepLevel = getBoolean("keepLevel", keepLevel, """
                Protects player experience
                플레이어 경험치를 보호합니다""");
    }
}
