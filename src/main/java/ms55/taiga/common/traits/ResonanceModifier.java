package ms55.taiga.common.traits;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class ResonanceModifier extends Modifier {

    public ResonanceModifier() {
        super(TextFormatting.AQUA.getColor());
    }

    //33% Chance to make mobs lunge towards you (useless)
    @Override
    public int afterEntityHit(IModifierToolStack tool, int level, ToolAttackContext context, float damageDealt) {
    	LivingEntity target = context.getLivingTarget();
    	LivingEntity attacker = context.getAttacker();
        if (attacker != null && target != null && RANDOM.nextFloat() <= 0.33) {
            context.getLivingTarget().knockback(RANDOM.nextFloat() * RANDOM.nextFloat() * 10, attacker.getX() - target.getX(), attacker.getZ() - target.getZ());
        }
        return 0;
    }
}