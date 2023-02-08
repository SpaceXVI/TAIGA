package ms55.taiga.common.traits;

import java.util.Random;

import ms55.taiga.TAIGA;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

//This trait is just so dumb I swear LOL
public class AnalysingModifier extends Modifier {
	private Random random = new Random();

	public AnalysingModifier() {
		super(TextFormatting.GREEN.getColor());
        MinecraftForge.EVENT_BUS.register(this);
	}

    @SubscribeEvent
    public void onExpDrop(LivingExperienceDropEvent event) {
        PlayerEntity player = event.getAttackingPlayer();
        if (player != null && ModifierUtil.getModifierLevel(player.getMainHandItem(), this) > 0 && event.getDroppedExperience() > 0) {
            event.setDroppedExperience(this.getUpdatedEXP(event.getDroppedExperience()));
        }
    }

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
    	PlayerEntity player = event.getPlayer();
        if (!event.getWorld().isClientSide() && player != null && ModifierUtil.getModifierLevel(player.getMainHandItem(), this) > 0 && event.getExpToDrop() > 0) {
            event.setExpToDrop(this.getUpdatedEXP(event.getExpToDrop()));
        }
    }

    @SubscribeEvent
    public void onMobDrops(LivingDropsEvent event) {
        World world = event.getEntity().level;
        if (random.nextFloat() < 0.1f && event.getSource().getEntity() instanceof PlayerEntity) {
        	PlayerEntity player = (PlayerEntity) event.getSource().getEntity();
            if (!world.isClientSide() && event.getEntity() instanceof MobEntity && ModifierUtil.getModifierLevel(player.getMainHandItem(), this) > 0) {
                event.getDrops().clear();
            }
        }
    }

    private int getUpdatedEXP(int xp) {
        float exp = random.nextFloat() * random.nextFloat() * random.nextFloat() * (xp + random.nextInt(xp) * (1 + random.nextFloat()));
        return Math.round(exp);
    }

    //Use GLM's instead	
    @Override
    public void afterBlockBreak(IModifierToolStack tool, int level, ToolHarvestContext context) {
    	LootContext.Builder lcb = (new LootContext.Builder(context.getWorld()).withParameter(LootParameters.TOOL, new ItemStack(tool.getItem()))
        		.withParameter(LootParameters.ORIGIN, context.getLiving().position()));
        if (random.nextFloat() < 0.1 || TAIGA.DEBUG) {
            context.getState().getDrops(lcb).clear();
        }
    }
}
