package ms55.taiga.common.traits;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class FractureModifier extends Modifier {

	public FractureModifier() {
		super(TextFormatting.DARK_GRAY.getColor());
	}

	@Override
	public void afterBlockBreak(IModifierToolStack tool, int level, ToolHarvestContext context) {
        float f = RANDOM.nextFloat();
        float b = 0.99F * calcBonus(tool);
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        if (!world.isClientSide && context.canHarvest() && f <= b) {            
        	for (int i = RANDOM.nextInt(9) + 1; i >= 0; i--) {
                switch (context.getSideHit()) {
                    case UP:
                        BlockPos next1 = new BlockPos(pos.getX(), pos.getY() - i, pos.getZ());
                        if (context.getPlayer().hasCorrectToolForDrops(world.getBlockState(next1)) && !world.getBlockState(next1).equals(Blocks.BEDROCK.defaultBlockState()))
                            world.destroyBlock(next1, true);
                        break;
                    case DOWN:
                        BlockPos next2 = new BlockPos(pos.getX(), pos.getY() + i, pos.getZ());
                        if (context.getPlayer().hasCorrectToolForDrops(world.getBlockState(next2)) && !world.getBlockState(next2).equals(Blocks.BEDROCK.defaultBlockState()))
                            world.destroyBlock(next2, true);
                        break;
                    case WEST:
                        BlockPos next3 = new BlockPos(pos.getX() + i, pos.getY(), pos.getZ());
                        if (context.getPlayer().hasCorrectToolForDrops(world.getBlockState(next3)) && !world.getBlockState(next3).equals(Blocks.BEDROCK.defaultBlockState()))
                            world.destroyBlock(next3, true);
                        break;
                    case EAST:
                        BlockPos next4 = new BlockPos(pos.getX() - i, pos.getY(), pos.getZ());
                        if (context.getPlayer().hasCorrectToolForDrops(world.getBlockState(next4)) && !world.getBlockState(next4).equals(Blocks.BEDROCK.defaultBlockState()))
                            world.destroyBlock(next4, true);
                        break;
                    case SOUTH:
                        BlockPos next5 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - i);
                        if (context.getPlayer().hasCorrectToolForDrops(world.getBlockState(next5)) && !world.getBlockState(next5).equals(Blocks.BEDROCK.defaultBlockState()))
                            world.destroyBlock(next5, true);
                        break;
                    case NORTH:
                        BlockPos next6 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + i);
                        if (context.getPlayer().hasCorrectToolForDrops(world.getBlockState(next6)) && !world.getBlockState(next6).equals(Blocks.BEDROCK.defaultBlockState()))
                            world.destroyBlock(next6, true);
                        break;
                }
            }

            if (RANDOM.nextBoolean()) {
            	ToolDamageUtil.damage(tool, RANDOM.nextInt(5), context.getPlayer(), null);
            }
        }
    }

    private float calcBonus(IModifierToolStack tool) {
        int durability = tool.getCurrentDurability();
        int maxDurability = tool.getDamage() + tool.getCurrentDurability();
        return (0.4f) / (maxDurability - 50) * (durability) + 0.55f;
    }
}