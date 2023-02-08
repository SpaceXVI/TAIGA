package ms55.taiga.common.traits;

import java.util.Random;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class BlindModifier extends Modifier {
	private Random random = new Random();

	public BlindModifier() {
		super(TextFormatting.GRAY.getColor());
	}

	//There's a 1% chance (or a 3% chance if it's night time) that after breaking a block that you'll either get blindness or weakness from 100 to 300 ticks
	@Override
	public void afterBlockBreak(IModifierToolStack tool, int level, ToolHarvestContext context) {
        if (random.nextFloat() <= 0.01 || (random.nextFloat() <= 0.03 && context.getWorld().isNight())) {
            if (random.nextBoolean()) {
                context.getPlayer().addEffect(new EffectInstance(Effects.BLINDNESS, random.nextInt(201) + 100));
            } else {
            	context.getPlayer().addEffect(new EffectInstance(Effects.WEAKNESS, random.nextInt(201) + 100));
            }
        }
	}

	//There's a 1% chance (or a 3% chance if it's night time) that after attacking an entity that you'll either get blindness or weakness from 200 to 600 ticks
	@Override
	public int afterEntityHit(IModifierToolStack tool, int level, ToolAttackContext context, float damageDealt) {
		if (context.getPlayerAttacker() != null && !context.getPlayerAttacker().level.isClientSide) {
			if (random.nextFloat() <= 0.01 || (random.nextFloat() <= 0.03 && context.getPlayerAttacker().level.isNight())) {
	            if (random.nextBoolean()) {
	            	context.getPlayerAttacker().addEffect(new EffectInstance(Effects.BLINDNESS, random.nextInt(401) + 200));
	            } else {
	            	context.getPlayerAttacker().addEffect(new EffectInstance(Effects.WEAKNESS, random.nextInt(401) + 200));
	            }
	        }
		}

		return 0;
	}
}