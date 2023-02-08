package ms55.taiga.common.data;

import ms55.taiga.TAIGA;
import ms55.taiga.common.block.TAIGABlocks;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

public class BlockTags extends BlockTagsProvider {
	public BlockTags(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
		super(generatorIn, TAIGA.MODID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		for (RegistryObject<Block> block : TAIGABlocks.BLOCKS.getEntries()) {
			String name = block.get().getRegistryName().getPath();
			if (name.contains("vibranium")) {
				name = name.replace("vibranium", "white_vibranium"); //CHANGE THIS FOR ALL TAGS
			}
			if (block.getId().getPath().contains("_block") && !(block.get() instanceof FlowingFluidBlock)) {
    	        INamedTag<Block> tag = TAIGATags.Blocks.createTag("storage_blocks", name.replace("_block", ""));
    	        tag(tag).add(block.get()).replace();
    	        tag(Tags.Blocks.STORAGE_BLOCKS).add(block.get()).replace();
			}
			if (block.getId().getPath().contains("_ore") && !block.getId().getPath().contains("_cobble_block") && !(block.get() instanceof FlowingFluidBlock)) {
    	        INamedTag<Block> tag = TAIGATags.Blocks.createTag("ores", name.replace("_ore", ""));
    	        tag(tag).add(block.get()).replace();
    	        tag(Tags.Blocks.ORES).add(block.get()).replace();
			}
		}
	}
}