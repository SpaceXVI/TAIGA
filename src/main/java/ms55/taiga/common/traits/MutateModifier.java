package ms55.taiga.common.traits;

import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class MutateModifier extends Modifier {

	public MutateModifier() {
		super(TextFormatting.YELLOW.getColor());
	}

	@Override
	public Boolean removeBlock(IModifierToolStack tool, int level, ToolHarvestContext context) {
		World world = context.getWorld();
		if (!world.isClientSide && RANDOM.nextFloat() >= 0.7) {
            BlockState state = context.getState();
            List<Block> blist = Arrays.asList(Blocks.STONE, Blocks.COBBLESTONE, Blocks.DIRT, Blocks.SAND, Blocks.GRASS, Blocks.CLAY, Blocks.NETHERRACK, Blocks.ICE,
            		Blocks.SNOW, Blocks.BONE_BLOCK, Blocks.LAVA, Blocks.WATER, Blocks.WHEAT);
            if (blist.contains(state.getBlock())) {
                Block newBlock = blist.get(RANDOM.nextInt(blist.size()));
                BlockState newState = newBlock.defaultBlockState();
                world.setBlock(context.getPos(), newState, 2);
                return false;
            }
        }
		return null;
	}
}