package ms55.taiga.common.traits;

import java.util.Random;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class BrightModifier extends Modifier {
	private Random random = new Random();

	public BrightModifier() {
		super(TextFormatting.DARK_GRAY.getColor());
	}

	//After breaking a block there's a 10% chance you'll get a glowing effect for 20 seconds.
	@Override
	public void afterBlockBreak(IModifierToolStack tool, int level, ToolHarvestContext context) {
		if (random.nextFloat() >= 0.9f) {
            context.getPlayer().addEffect(new EffectInstance(Effects.GLOWING, 200));
        }
	}

	//During daytime you'll deal up to 1.5x more damage, during nighttime, however, you'll deal up to 1.3x less damage
	@Override
	public float getEntityDamage(IModifierToolStack tool, int level, ToolAttackContext context, float baseDamage, float damage) {
		if (context.getPlayerAttacker() != null && !context.getPlayerAttacker().level.isClientSide) {
			if (!context.getPlayerAttacker().level.isNight()) {
	        	damage *= (1 + random.nextFloat() / 2f); //instead of baseDamage, we used damage (reason for using a shorthand)
	        } else {
	        	damage /= (1 + random.nextFloat() / 3f);
			}
		}

        return damage;
	}
}
