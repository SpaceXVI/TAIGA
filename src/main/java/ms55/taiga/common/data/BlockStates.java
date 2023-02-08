package ms55.taiga.common.data;

import java.util.function.Supplier;

import ms55.taiga.TAIGA;
import ms55.taiga.common.block.TAIGABlocks;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

public class BlockStates extends BlockStateProvider {

    public BlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, TAIGA.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
    	for (RegistryObject<Block> block : TAIGABlocks.BLOCKS.getEntries()) {
    		if (block.get() instanceof FlowingFluidBlock) {
    			simpleBlock(block.get(), models().cubeAll(block.get().getRegistryName().getPath(), new ResourceLocation("block/water_still"))); //new ResourceLocation("tconstruct", "blocks/fluids/molten_metal")
    		} else {
    			simpleBlock(block);
    		}
    	}
    }

	public void simpleBlock(Supplier<? extends Block> blockSupplier) {
		simpleBlock(blockSupplier.get());
	}

	@Override
	public void simpleBlock(Block block, ModelFile model) {
		super.simpleBlock(block, model);
		this.simpleBlockItem(block, model);
	}

    @Override
	public String getName() {
		return "TAIGA Block States";
	}
}