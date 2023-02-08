package ms55.taiga.common.item;

import ms55.taiga.TAIGA;
import net.minecraft.item.Item;

public class BasicItem extends Item {
    private String oreDictPrefix;

    public BasicItem(String oreDictPrefix) {
    	super(new Item.Properties().tab(TAIGA.ITEM).stacksTo(64));
        this.oreDictPrefix = oreDictPrefix;
    }

    public boolean isOreDict() {
        return this.oreDictPrefix != null;
    }

    public String getOreDictPrefix() {
        return oreDictPrefix;
    }
}