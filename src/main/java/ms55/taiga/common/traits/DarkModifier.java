package ms55.taiga.common.traits;

import java.util.Random;

import ms55.taiga.common.util.Utils;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class DarkModifier extends Modifier {
	private Random random = new Random();

	public DarkModifier() {
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

	//Makes you deal more damage at night, and less damage at daytime
	@Override
	public float getEntityDamage(IModifierToolStack tool, int level, ToolAttackContext context, float baseDamage, float damage) {
		int time = (int) context.getLivingTarget().level.getDayTime();
        if (Utils.isNight(time)) {
        	damage = baseDamage * (1 + random.nextFloat() / 2f);
        } else damage = baseDamage / (1 + random.nextFloat() / 3f);
		return super.getEntityDamage(tool, level, context, baseDamage, damage);
	}
}