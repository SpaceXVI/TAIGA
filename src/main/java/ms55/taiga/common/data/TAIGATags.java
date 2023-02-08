package ms55.taiga.common.data;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class TAIGATags {

	public static class Items {
		public static final Tags.IOptionalNamedTag<Item> INGOTS = forgeTag("ingots");
		public static final Tags.IOptionalNamedTag<Item> NUGGETS = forgeTag("nuggets");
		public static final Tags.IOptionalNamedTag<Item> DUSTS = forgeTag("dusts");
		public static final Tags.IOptionalNamedTag<Item> CRYSTALS = forgeTag("crystals");
		public static final Tags.IOptionalNamedTag<Item> ORES = forgeTag("ores");
		public static final Tags.IOptionalNamedTag<Item> STORAGE_BLOCKS = forgeTag("storage_blocks");

        public static Tags.IOptionalNamedTag<Item> createTag(String prefix, String name) {
        	return forgeTag(prefix + "/" + name);
        }

        private static Tags.IOptionalNamedTag<Item> forgeTag(String name) {
            return net.minecraft.tags.ItemTags.createOptional(new ResourceLocation("forge", name));
        }
	}

	public static class Blocks {
		public static final INamedTag<Block> STORAGE_BLOCKS = forgeTag("storage_blocks");
		public static final INamedTag<Block> ORES = forgeTag("ores");

        public static INamedTag<Block> createTag(String prefix, String name) {
        	return forgeTag(prefix + "/" + name);
        }

        private static INamedTag<Block> forgeTag(String name) {
            return net.minecraft.tags.BlockTags.createOptional(new ResourceLocation("forge", name));
        }
	}
}