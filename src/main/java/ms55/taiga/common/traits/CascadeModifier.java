package ms55.taiga.common.traits;

import java.util.Random;

import ms55.taiga.TAIGA;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class CascadeModifier extends Modifier {
	private Random random = new Random();

	public CascadeModifier() {
		super(TextFormatting.DARK_GRAY.getColor());
	}

	@Override
	public void afterBlockBreak(IModifierToolStack tool, int level, ToolHarvestContext context) {
        float rand = random.nextFloat();
        World world = context.getWorld();
        BlockPos pos = context.getPos();

        if (!world.isClientSide && context.canHarvest() && (TAIGA.DEBUG || rand <= 0.3)) {
            double x, y, z, sx, sy, sz;
            sx = x = pos.getX();
            sy = y = pos.getY();
            sz = z = pos.getZ();
            int i = random.nextInt(Math.abs((int) Math.min(300f * (float) tool.getCurrentDurability() / (tool.getDamage() + tool.getCurrentDurability()), 50f)));
            for (int a = i; a > 0; a--) {
                int d = random.nextBoolean() ? 1 : -1;
                switch (random.nextInt(3)) {
				case 0:
					x += d;
					break;
				case 1:
					y += d;
					break;
				case 2:
					z += d;
					break;
				}

                BlockPos nextBlock = new BlockPos(x, y, z);
                if (world.getBlockState(nextBlock) == context.getState()) {
                    world.destroyBlock(nextBlock, true);
                    sx = x = nextBlock.getX();
                    sy = y = nextBlock.getY();
                    sz = z = nextBlock.getZ();
                    ToolDamageUtil.damage(tool, 1, context.getPlayer(), null);
                } else {
                    x = sx;
                    y = sy;
                    z = sz;
                }
            }
        }
	}
}
