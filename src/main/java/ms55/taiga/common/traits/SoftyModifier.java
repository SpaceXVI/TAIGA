package ms55.taiga.common.traits;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

//Mines soft blocks faster
public class SoftyModifier extends Modifier {
    private static final float chance = 0.1f;
    private static final float speedmulti = 1.3f;

    public SoftyModifier() {
        super(TextFormatting.GRAY.getColor());
        MinecraftForge.EVENT_BUS.register(this);
    }

	@SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        float r = RANDOM.nextFloat();
        float hardness = event.getWorld().getBlockState(event.getPos()).getDestroySpeed(event.getWorld(), event.getPos());
        ItemStack tool = event.getPlayer().getMainHandItem();
        if (ModifierUtil.getModifierLevel(tool, this) > 0) {
            if (!event.getWorld().isClientSide() && r <= chance && hardness >= 1.0f) {
                event.setCanceled(true);
                event.getPlayer().playSound(SoundEvents.UI_STONECUTTER_TAKE_RESULT, 1.0F, 1.0F);
                ToolDamageUtil.damage(ToolStack.from(tool), RANDOM.nextInt(3) + 1, event.getPlayer(), null);
            }
        }
    }

    @Override
    public void onBreakSpeed(IModifierToolStack tool, int level, BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
        World world = event.getEntity().level;
        BlockState state = world.getBlockState(event.getPos());
        float speed = event.getOriginalSpeed();
        if (!world.isClientSide) {
            if (state.getDestroySpeed(world, event.getPos()) <= 1.0f) {
                event.setNewSpeed(speed * speedmulti);
            }
        }
    }
}