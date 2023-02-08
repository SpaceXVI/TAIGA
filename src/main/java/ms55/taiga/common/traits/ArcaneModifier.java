package ms55.taiga.common.traits;

import java.util.Random;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class ArcaneModifier extends Modifier {
	private Random random = new Random();

	public ArcaneModifier() {
		super(TextFormatting.DARK_PURPLE.getColor());
	}

	//5% chance to repair a tool from 1 : 8 points (night-time only!)
	@Override
	public void afterBlockBreak(IModifierToolStack tool, int level, ToolHarvestContext context) {
        if (random.nextFloat() <= 0.05 && context.getWorld().isNight()) {
        	ToolDamageUtil.repair(tool, random.nextInt(8) + 1);
        }
	}

	//5% chance to repair a tool from 1 : 8 points (night-time only!)
	@Override
	public int afterEntityHit(IModifierToolStack tool, int level, ToolAttackContext context, float damageDealt) {
		if (context.getPlayerAttacker() != null && !context.getPlayerAttacker().level.isClientSide) {
	        if (random.nextFloat() <= 0.05 && context.getPlayerAttacker().level.isNight()) {
	        	ToolDamageUtil.repair(tool, random.nextInt(8) + 1);
	        }
		}

		return 0;
    }

	//less than 10% chance to repair a tool from 0 : 16 points (night-time only!)
    @SubscribeEvent
    public void onEntityKill(LivingDeathEvent event) {
        World world = event.getEntity().level;
        if (!world.isClientSide && event.getSource().getEntity() != null) {
            if (event.getSource().getEntity() instanceof PlayerEntity && event.getEntity() instanceof PlayerEntity) {
                ItemStack tool = ((PlayerEntity) event.getSource().getEntity()).getMainHandItem();
                if (world.isNight() && random.nextFloat() < 0.1 && ModifierUtil.getModifierLevel(tool, this) > 0) {
                	ToolDamageUtil.repair(ToolStack.from(tool), random.nextInt(16));
                }
            }
        }
    }
}
