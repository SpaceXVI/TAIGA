package ms55.taiga.common.traits;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class FragileModifier extends Modifier {

	public FragileModifier() {
		super(TextFormatting.DARK_GRAY.getColor());
	}

	@Override
	public void afterBlockBreak(IModifierToolStack tool, int level, ToolHarvestContext context) {
        float f = RANDOM.nextFloat();
        float b = 0.99F * calcBonus(tool);
        World world = context.getWorld();
        if (RANDOM.nextBoolean()) {
            if (!world.isClientSide && context.canHarvest() && f <= b) {
                if (RANDOM.nextBoolean()) {
                	ToolDamageUtil.damage(tool, RANDOM.nextInt(3), context.getPlayer(), null);
                } else {
                	ToolDamageUtil.repair(tool, RANDOM.nextInt(3));
                }
            }
        } else {
            if (!world.isClientSide && context.getState().getBlock() == Blocks.STONE && f <= b) {
                double x, y, z, sx, sy, sz;
                sx = x = context.getPos().getX();
                sy = y = context.getPos().getY();
                sz = z = context.getPos().getZ();
                for (int i = RANDOM.nextInt(10) + 9; i > 0; i--) {
                    int r = RANDOM.nextInt(3);
                    int d = RANDOM.nextBoolean() ? 1 : -1;
                    if (r == 0) x += d;
                    if (r == 1) y += d;
                    if (r == 2) z += d;
                    BlockPos nextBlock = new BlockPos(x, y, z);
                    if (world.getBlockState(nextBlock) == context.getState()) {
                        Block block = null;
                        int ib = RANDOM.nextInt(2);
                        switch (ib) {
                            case 0:
                                block = Blocks.COBBLESTONE;
                                break;
                            case 1:
                                if (RANDOM.nextFloat() <= 0.9) {
                                	block = Blocks.GRAVEL;
                                } else {
                                	block = Blocks.MOSSY_COBBLESTONE;
                                }
                        }
                        f = RANDOM.nextFloat();
                        assert block != null;
                        if (f < 0.85) {
                            world.setBlock(nextBlock, block.defaultBlockState(), 2);
                        } else if (f > 95) {
                            world.destroyBlock(nextBlock, true);
                        }
                        sx = x = nextBlock.getX();
                        sy = y = nextBlock.getY();
                        sz = z = nextBlock.getZ();
                        if (RANDOM.nextBoolean()) ToolDamageUtil.damage(tool, 1, context.getPlayer(), null);
                    } else {
                        x = sx;
                        y = sy;
                        z = sz;
                    }

                }

            }

        }
    }

    private float calcBonus(IModifierToolStack tool) {
    	int durability = tool.getCurrentDurability();
        int maxDurability = tool.getDamage() + tool.getCurrentDurability();
        return (0.4f) / (maxDurability - 50) * (durability) + 0.55f;
    }
}