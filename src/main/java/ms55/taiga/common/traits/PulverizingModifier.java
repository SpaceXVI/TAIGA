package ms55.taiga.common.traits;

import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.util.Direction;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

//Deletes 60% of anything you mine but mines stuff faster
public class PulverizingModifier extends Modifier {

    public PulverizingModifier() {
        super(TextFormatting.DARK_GRAY.getColor());
    }

    //If the tool is effective on a specific block, then increase the mining speed
    @Override
    public void onBreakSpeed(IModifierToolStack tool, int level, BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
        if (isEffective) {
            event.setNewSpeed((float) (event.getNewSpeed() * calcBonus(tool)));
        }
    }

    //60% Chance to drop nothing from blocks
    @Override
    public void afterBlockBreak(IModifierToolStack tool, int level, ToolHarvestContext context) {
        LootContext.Builder lcb = (new LootContext.Builder(context.getWorld()).withParameter(LootParameters.TOOL, new ItemStack(tool.getItem()))
        		.withParameter(LootParameters.ORIGIN, context.getLiving().position()));
        if (RANDOM.nextFloat() < 0.6) {
            context.getState().getDrops(lcb).clear();
        }
    }

    private double calcBonus(IModifierToolStack tool) {
		int durability = tool.getCurrentDurability();
        int maxDurability = tool.getCurrentDurability() + tool.getDamage();
        return (1 + 0.9f * (maxDurability - durability) / maxDurability);
        // Min 1.0; Max 1.9
    }
}