package ms55.taiga.common.traits;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class GlimmerModifier extends Modifier {

	public GlimmerModifier() {
		super(TextFormatting.DARK_GRAY.getColor());
	}

	//Makes the player have a glow effect.
	@Override
	public void onInventoryTick(IModifierToolStack tool, int level, World world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
		if (holder instanceof PlayerEntity) {
			holder.addEffect(new EffectInstance(Effects.GLOWING, 100));
        }
	}

	//Has a 5% chance to make the player get night vision after breaking blocks
	@Override
	public void afterBlockBreak(IModifierToolStack tool, int level, ToolHarvestContext context) {
		if (RANDOM.nextFloat() <= 0.05) {
            context.getPlayer().addEffect(new EffectInstance(Effects.NIGHT_VISION, RANDOM.nextInt(600) + 300));
        }
	}

	//Has a 5% chance to give the attacker night vision after hitting an entity
	@Override
	public int afterEntityHit(IModifierToolStack tool, int level, ToolAttackContext context, float damageDealt) {
		if (RANDOM.nextFloat() <= 0.05) {
            context.getAttacker().addEffect(new EffectInstance(Effects.NIGHT_VISION, RANDOM.nextInt(600) + 300));
        }
		return 0;
	}
}