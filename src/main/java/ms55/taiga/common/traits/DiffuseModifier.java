package ms55.taiga.common.traits;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class DiffuseModifier extends Modifier {

	public DiffuseModifier() {
		super(TextFormatting.GREEN.getColor());
        MinecraftForge.EVENT_BUS.register(this);
	}

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        PlayerEntity player = event.getPlayer();
        if (!player.level.isClientSide && ModifierUtil.getModifierLevel(player.getMainHandItem(), this) > 0) {
        	event.setExpToDrop((int) this.getUpdateXP(event.getExpToDrop()));
        }
    }

    @SubscribeEvent
    public void onMobDrops(LivingDropsEvent event) {
        World world = event.getEntity().level;
        if (!world.isClientSide && event.getSource().getEntity() instanceof PlayerEntity) {
        	PlayerEntity player = (PlayerEntity) event.getSource().getEntity();
            if (event.getEntity() instanceof CreatureEntity && ModifierUtil.getModifierLevel(player.getMainHandItem(), this) > 0) {
                event.getDrops().clear();
            }
        }
    }

    private float getUpdateXP(int xp) {
        float exp = RANDOM.nextFloat() * RANDOM.nextFloat() * RANDOM.nextFloat() * (1 + RANDOM.nextFloat() * xp);
        if (RANDOM.nextFloat() <= 0.25)
            return exp;
        else return 0;
    }

    //TODO : Use GLM
    @Override
	public void afterBlockBreak(IModifierToolStack tool, int level, ToolHarvestContext context) {
        if (RANDOM.nextFloat() < 0.35) {
    		LootContext.Builder lcb = (new LootContext.Builder(context.getWorld()).withParameter(LootParameters.TOOL, new ItemStack(tool.getItem()))
            		.withParameter(LootParameters.ORIGIN, context.getLiving().position()));
            context.getState().getDrops(lcb).clear();
        }
    }
}