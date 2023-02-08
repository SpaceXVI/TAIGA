package ms55.taiga.common.traits;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;

public class MeltingModifier extends Modifier {

	public MeltingModifier() {
		super(TextFormatting.YELLOW.getColor());
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void blockBreak(BlockEvent.BreakEvent event) {
        Block block = event.getWorld().getBlockState(event.getPos()).getBlock();
        if (ModifierUtil.getModifierLevel(event.getPlayer().getMainHandItem(), this) > 0) {
            if (!event.getWorld().isClientSide() && RANDOM.nextFloat() <= 0.025 && (block == Blocks.STONE || block == Blocks.COBBLESTONE || block == Blocks.NETHERRACK ||
            		block == Blocks.OBSIDIAN)) {
                event.setCanceled(true);
                event.getWorld().setBlock(event.getPos(), Blocks.LAVA.defaultBlockState(), 2);
            }
        }
    }
}