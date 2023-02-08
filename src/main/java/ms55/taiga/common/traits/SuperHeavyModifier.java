package ms55.taiga.common.traits;

import ms55.taiga.common.util.Utils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;

public class SuperHeavyModifier extends Modifier {
    protected static int TICK_PER_STAT = 50;
    protected static float blockcount = 500f;

    public SuperHeavyModifier() {
        super(TextFormatting.DARK_GRAY.getColor());
    }

    @Override
    public void onBreakSpeed(IModifierToolStack tool, int level, BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
		ModDataNBT tag = tool.getPersistentData();
		if (tag != null) {
			Utils.GeneralNBTData data = Utils.GeneralNBTData.read(tag);
	        if (data.brokenblocks == 0) {
	        	return;
	        }
	        event.setNewSpeed(Math.max(event.getNewSpeed() - data.brokenblocks * event.getOriginalSpeed() / blockcount, 0.35f));
		}
    }

    @Override
    public void afterBlockBreak(IModifierToolStack tool, int level, ToolHarvestContext context) {
		ModDataNBT tag = tool.getPersistentData();
		if (tag != null) {
	        Utils.GeneralNBTData data = Utils.GeneralNBTData.read(tag);
	        data.brokenblocks += 1;
	        data.write(tag);
		}
    }

    @Override
    public void onInventoryTick(IModifierToolStack tool, int level, World world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        // every 3.6 seconds we distribute one stat. This means 1h = 1000 applications
        if (holder.tickCount % TICK_PER_STAT > 0) {
            return;
        }

        // we don't update if the player is currently breaking a block because that'd reset it
        //if (playerIsBreakingBlock(entity)) {
        //    return;
        //}

		ModDataNBT tag = tool.getPersistentData();
		if (tag != null) {
	        Utils.GeneralNBTData data = Utils.GeneralNBTData.read(tag);
	        if (data.brokenblocks > 0) {
	            data.brokenblocks -= 1;
	        }
	        data.write(tag);
		}
    }

    @Override
    public ITextComponent getDisplayName(IModifierToolStack tool, int level) {
		ModDataNBT tag = tool.getPersistentData();
		if (tag != null) {
			Utils.GeneralNBTData data = Utils.GeneralNBTData.read(tag);
	        return getDisplayName().copy().append(TextFormatting.RED + " [" + "Broken Blocks: " + TextFormatting.WHITE + data.brokenblocks + "]");
		}

		return getDisplayName(level);
    }
}