package ms55.taiga.common.data;

import ms55.taiga.TAIGA;
import ms55.taiga.common.block.TAIGABlocks;
import ms55.taiga.common.item.TAIGAItems;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.Tags.IOptionalNamedTag;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

public class ItemTags extends ItemTagsProvider {

	public ItemTags(DataGenerator dataGenerator, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
		super(dataGenerator, blockTagProvider, TAIGA.MODID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		for (RegistryObject<Item> item : TAIGAItems.ITEMS.getEntries()) {
			String name = item.get().getRegistryName().getPath();
//			if (name.contains("vibranium")) {
//				name = name.replace("vibranium", "white_vibranium");
//			}
			if (name.contains("ingot")) {
    	        IOptionalNamedTag<Item> tag = TAIGATags.Items.createTag("ingots", name.replace("_ingot", ""));
    	        tag(tag).add(item.get()).replace();
    	        tag(TAIGATags.Items.INGOTS).add(item.get()).replace();
			} else if (name.contains("nugget")) {
    	        IOptionalNamedTag<Item> tag = TAIGATags.Items.createTag("nuggets", name.replace("_nugget", ""));
    	        tag(tag).add(item.get()).replace();
    	        tag(TAIGATags.Items.NUGGETS).add(item.get()).replace();
			} else if (name.contains("dust")) {
    	        IOptionalNamedTag<Item> tag = TAIGATags.Items.createTag("dusts", name.replace("_dust", ""));
    	        tag(tag).add(item.get()).replace();
    	        tag(TAIGATags.Items.DUSTS).add(item.get()).replace();
			} else if (name.contains("crystal")) {
    	        IOptionalNamedTag<Item> tag = TAIGATags.Items.createTag("crystals", name.replace("_crystal", ""));
    	        tag(tag).add(item.get()).replace();
    	        tag(TAIGATags.Items.CRYSTALS).add(item.get()).replace();
			}
		}

		for (RegistryObject<Block> block : TAIGABlocks.BLOCKS.getEntries()) {
			String name = block.get().getRegistryName().getPath();
//			if (name.contains("vibranium")) {
//				name = name.replace("vibranium", "white_vibranium");
//			}
			if (block.getId().getPath().contains("_block") && !(block.get() instanceof FlowingFluidBlock)) {
				IOptionalNamedTag<Item> tag = TAIGATags.Items.createTag("storage_blocks", name.replace("_block", ""));
				tag(tag).add(block.get().asItem()).replace();
				tag(Tags.Items.STORAGE_BLOCKS).add(block.get().asItem()).replace();
			}
			if (block.getId().getPath().contains("_ore") && !block.getId().getPath().contains("_cobble_block") && !(block.get() instanceof FlowingFluidBlock)) {
				IOptionalNamedTag<Item> tag = TAIGATags.Items.createTag("ores", name.replace("_ore", ""));
				tag(tag).add(block.get().asItem()).replace();
				tag(Tags.Items.ORES).add(block.get().asItem()).replace();
			}
		}
	}
}