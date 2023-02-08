package ms55.taiga.common.traits;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class HeroicModifier extends Modifier {

	public HeroicModifier() {
		super(TextFormatting.DARK_GRAY.getColor());
	}

	//Literally changes little to nothing in damage, wtf?
	@Override
	public float getEntityDamage(IModifierToolStack tool, int level, ToolAttackContext context, float baseDamage, float damage) {
		if (context.getPlayerAttacker() != null && context.getLivingTarget() != null) {
			PlayerEntity player = context.getPlayerAttacker();
			LivingEntity target = context.getLivingTarget();
			int durability = tool.getCurrentDurability();
	        int durabilitymax = tool.getCurrentDurability() + tool.getDamage();

	        int safeDenominator = Math.max(durabilitymax - durability - 1, 1);
	        int test = Math.max(durabilitymax - durability, 1);

	        float calc;
	        if ((durability * durabilitymax / (test / safeDenominator)) != 0) {
	            calc = damage + (damage / 2) / (durability * durabilitymax / safeDenominator);
	        } else {
	            calc = damage + (damage / 2) / ((durability * durabilitymax / safeDenominator) + 1);
	        }

	        if ((float) durability < (float) (0.1 * durabilitymax) || player.getHealth() < player.getMaxHealth() / 8 || (target.getHealth() == target.getMaxHealth()
	        		&& RANDOM.nextFloat() > 0.8)) {
	    		return calc;
	        } else {
	    		return damage * 0.9f;
	        }
		}
		return damage;
	}
}