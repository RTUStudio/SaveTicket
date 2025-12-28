package kr.rtustudio.saveticket.configuration;

import kr.rtustudio.configurate.objectmapping.meta.Comment;
import kr.rtustudio.framework.bukkit.api.configuration.ConfigurationPart;
import lombok.Getter;

@Getter
@SuppressWarnings({"unused", "FieldMayBeFinal"})
public class GlobalConfig extends ConfigurationPart {

    @Comment("""
            Inventory protection trigger item
            인벤토리 보호 트리거 아이템

            Vanilla: minecraft:item_type
            Itemsadder: itemsadder:category:item
            Oraxen: oraxen:item
            Nexo: nexo:item""")
    private String item = "minecraft:barrier";

    @Comment("""
            true: Consume the item by right-clicking to register inventory save
            true: 아이템을 우클릭으로 소모하여 인벤 세이브를 등록합니다

            false: Automatically consume the item in the inventory to protect the inventory
            false: 인벤토리에 가지고 있으면 자동으로 소모하여 인벤토리를 보호합니다""")
    private boolean autoProtect = true;

    @Comment("""
            Protects inventory items
            인벤토리 아이템을 보호합니다""")
    private boolean keepInventory = true;

    @Comment("""
            Protects player experience
            플레이어 경험치를 보호합니다""")
    private boolean keepLevel = true;
}
