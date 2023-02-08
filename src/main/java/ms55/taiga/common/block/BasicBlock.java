package ms55.taiga.common.block;

import javax.annotation.Nullable;

import net.minecraft.block.Block;

public class BasicBlock extends Block {
    private String oreDictPrefix;

    public BasicBlock(Properties properties, @Nullable String oreDictPrefix) {
        super(properties);
        this.oreDictPrefix = oreDictPrefix;
    }

    public boolean isOreDict() {
        return this.oreDictPrefix != null;
    }

    @Nullable
    public String getOreDictPrefix() {
        return this.oreDictPrefix;
    }
}