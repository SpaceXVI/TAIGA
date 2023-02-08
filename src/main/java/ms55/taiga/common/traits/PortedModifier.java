package ms55.taiga.common.traits;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class PortedModifier extends Modifier {
    public static int distance = 10;

	public PortedModifier() {
		super(TextFormatting.DARK_PURPLE.getColor());
        MinecraftForge.EVENT_BUS.register(this);
	}

	//Right-clicking the item will teleport you randomly
	@SubscribeEvent
	public void onItemRightClick(PlayerInteractEvent.RightClickItem event) {
		ItemStack tool = event.getPlayer().getMainHandItem();
		if (ModifierUtil.getModifierLevel(tool, this) > 0 /*&& Keybindings.altKey.isKeyDown()*/) {
            teleport(ToolStack.copyFrom(tool), event.getPlayer(), event.getWorld());
		}
    }

	//0.5% chance after breaking a block to teleport you randomly.
	@Override
	public void afterBlockBreak(IModifierToolStack tool, int level, ToolHarvestContext context) {
		if (RANDOM.nextFloat() <= 0.005) {
			context.getPlayer().playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
			teleport(tool, context.getPlayer(), context.getWorld());
		}
	}

	//0.5% chance after hitting an entity to teleport you randomly.
	@Override
	public int afterEntityHit(IModifierToolStack tool, int level, ToolAttackContext context, float damageDealt) {
		if (context.getPlayerAttacker() != null && context.getLivingTarget() != null && RANDOM.nextFloat() <= 0.005) {
			if (!context.getPlayerAttacker().level.isClientSide) {
				context.getPlayerAttacker().level.playSound(null, context.getPlayerAttacker().blockPosition(), SoundEvents.ENDERMAN_TELEPORT,
						SoundCategory.AMBIENT ,1.0F, 1.0F);
			}
			teleport(tool, context.getPlayerAttacker(), context.getPlayerAttacker().level);
		}

		return 0;
	}

	private void teleport(IModifierToolStack tool, LivingEntity entity, World world) {
		BlockPos tPos = new BlockPos(entity.blockPosition().above(distance));
		if (entity.blockPosition().getY() >= 128) {
			return;
		}

		while (!world.getBlockState(tPos).equals(Blocks.AIR.defaultBlockState()) && tPos.getY() <= 128) {
			tPos = tPos.above();
		}

		if (!world.getBlockState(tPos).equals(Blocks.AIR.defaultBlockState())) {
			return;
		}

		entity.setPos(tPos.getX(), tPos.getY(), tPos.getZ());
		ToolDamageUtil.damage(tool, tool.getCurrentDurability() / 2 + 1, entity, null);
	}
}