package ms55.taiga.common.traits;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;

public class DissolvingModifier extends Modifier {

	public DissolvingModifier() {
		super(TextFormatting.DARK_AQUA.getColor());
        MinecraftForge.EVENT_BUS.register(this);
	}

	//High chance to remove EXP, low chance to double, triple or quadruple dropped experience.
    @SubscribeEvent
    public void onExpDrop(LivingExperienceDropEvent event) {
        if (!event.getEntity().level.isClientSide) {
            PlayerEntity player = event.getAttackingPlayer();
            float rand = RANDOM.nextFloat();
            if (player != null && ModifierUtil.getModifierLevel(player.getMainHandItem(), this) > 0) {
                if (rand <= 0.8) {
                    event.setDroppedExperience(0);
                } else {
                    event.setDroppedExperience(event.getDroppedExperience() * (RANDOM.nextInt(3) + 2));
                }
            }
        }
    }
}