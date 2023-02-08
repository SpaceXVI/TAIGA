package ms55.taiga;

import ms55.taiga.common.block.TAIGABlocks;
import ms55.taiga.common.item.TAIGAItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class TAIGATab {
	public static class TAIGATabBlock extends ItemGroup {
		public TAIGATabBlock() {
			super(TAIGA.MODID + "blocks");
		}

		@Override
		public ItemStack makeIcon() {
			return new ItemStack(TAIGABlocks.ADAMANT_BLOCK.get());
		}
	}

	public static class TAIGATabItem extends ItemGroup {
		public TAIGATabItem() {
			super(TAIGA.MODID + "items");
		}

		@Override
		public ItemStack makeIcon() {
			return new ItemStack(TAIGAItems.SOLARIUM_INGOT.get());
		}
	}
}