package ms55.taiga.common.traits;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class CrushingModifier extends Modifier {

	public CrushingModifier() {
		super(TextFormatting.GRAY.getColor());
	}

	//TODO : Use GLM's
	@Override
	public void afterBlockBreak(IModifierToolStack tool, int level, ToolHarvestContext context) {
    	if (!context.getWorld().isClientSide()) {
    		LootContext.Builder lcb = (new LootContext.Builder(context.getWorld()).withParameter(LootParameters.TOOL, new ItemStack(tool.getItem()))
            		.withParameter(LootParameters.ORIGIN, context.getLiving().position()));
    		BlockState state = context.getState();
            float f = RANDOM.nextFloat();
    		if (state.equals(Blocks.STONE.defaultBlockState())) {
    			context.getState().getDrops(lcb).clear();
                if (f < 0.3f) {
                	context.getState().getDrops(lcb).add(new ItemStack(Blocks.SAND));
                } else if (f < 0.6f) {
                	context.getState().getDrops(lcb).add(new ItemStack(Blocks.GRAVEL));
                } else if (f <= 0.9f) {
                	context.getState().getDrops(lcb).add(new ItemStack(Blocks.COBBLESTONE));
                } else {
                	context.getState().getDrops(lcb).add(new ItemStack(Blocks.STONE));
                }
            }
		}
	}
}