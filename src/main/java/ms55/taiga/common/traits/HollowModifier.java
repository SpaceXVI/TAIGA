package ms55.taiga.common.traits;

import ms55.taiga.TAIGA;
import ms55.taiga.common.util.Utils;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class HollowModifier extends Modifier {

	public HollowModifier() {
		super(TextFormatting.DARK_GRAY.getColor());
        MinecraftForge.EVENT_BUS.register(this);
	}

	//Too overpowered.
	@Override
	public int afterEntityHit(IModifierToolStack tool, int level, ToolAttackContext context, float damageDealt) {
		if (context.getPlayerAttacker() != null && context.getLivingTarget() != null && context.getLivingTarget() instanceof MobEntity) {
			int time = (int) context.getPlayerAttacker().level.getDayTime();
			MobEntity target = (MobEntity) context.getLivingTarget();
	        if (RANDOM.nextFloat() <= 0.01 || (RANDOM.nextFloat() <= 0.03 && Utils.isNight(time)) || TAIGA.DEBUG) {
	        	target.setNoAi(true);
	            if (!target.level.isClientSide) {
	            	context.getPlayerAttacker().level.playSound(null, target.blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundCategory.AMBIENT,
	            			1.0F, 1.0F);
				}
	       
	            if (target.getMaxHealth() < 250) {
	                target.setHealth(target.getMaxHealth() * (1.8f - RANDOM.nextFloat() * 0.4f));
	            }
	        }
		}

		return 0;
	}

    @SubscribeEvent
    public void onMobDrops(LivingDropsEvent event) {
        World world = event.getEntity().level;
        if (!world.isClientSide && event.getSource().getEntity() instanceof PlayerEntity) {
        	PlayerEntity player = (PlayerEntity) event.getSource().getEntity();
            if (RANDOM.nextFloat() <= 0.9 && event.getEntity() instanceof MobEntity && ModifierUtil.getModifierLevel(player.getMainHandItem(), this) > 0) {
                event.getDrops().clear();
            }
        }
    }
}