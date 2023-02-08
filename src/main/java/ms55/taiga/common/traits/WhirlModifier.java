package ms55.taiga.common.traits;

import ms55.taiga.common.util.Utils;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class WhirlModifier extends Modifier {
    private static final int TICK_PER_STAT = 36;

    public WhirlModifier() {
        super(TextFormatting.DARK_BLUE.getColor());
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
	public void addVolatileData(ToolRebuildContext context, int level, ModDataNBT volatileData) {
		volatileData.putBoolean(IModifiable.SHINY, true);
	}

    @Override
    public void onInventoryTick(IModifierToolStack tool, int level, World world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        if (holder instanceof FakePlayer || world.isClientSide || holder.tickCount % TICK_PER_STAT != 0) {
            return;
        }

        //The fuck does this do?
		ModDataNBT tag = tool.getPersistentData();
        Utils.GeneralNBTData data = Utils.GeneralNBTData.read(tag);
        data.radius += RANDOM.nextFloat() * 0.5f;
        if (data.radius >= 1) {
        	if (!stack.getTag().getCompound(ToolStack.TAG_VOLATILE_MOD_DATA).getBoolean(IModifiable.SHINY.toString())) {
    			stack.getTag().getCompound(ToolStack.TAG_VOLATILE_MOD_DATA).putBoolean(IModifiable.SHINY.toString(), true);
			}
        } else {
        	if (stack.getTag().getCompound(ToolStack.TAG_VOLATILE_MOD_DATA).getBoolean(IModifiable.SHINY.toString())) {
    			stack.getTag().getCompound(ToolStack.TAG_VOLATILE_MOD_DATA).putBoolean(IModifiable.SHINY.toString(), false);
    		}
		}
        data.write(tag);
    }

	@SubscribeEvent
    public void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        World world = event.getWorld();
        ItemStack tool = event.getItemStack();
        if (!world.isClientSide && ModifierUtil.getModifierLevel(tool, this) > 0 /*&& altKey.isKeyDown()*/) {
        	ToolStack stack = ToolStack.from(tool);
            Utils.GeneralNBTData data = Utils.GeneralNBTData.read(stack.getPersistentData());
            if (data.radius >= 1) {
                int r = Math.min((int) data.radius, 8);
                for (int x = -r; x <= r; x++) {
                    for (int y = -r; y <= r; y++) {
                        for (int z = -r; z <= r; z++) {
                            if (MathHelper.sqrt(x * x + y * y + z * z) > r) {
                                continue;
                            }
                            BlockPos nPos = new BlockPos(event.getPos().getX() + x, event.getPos().getY() + y, event.getPos().getZ() + z);
                            if (!(event.getWorld().getBlockState(nPos).equals(Blocks.WATER.defaultBlockState()) ||
                                    event.getWorld().getBlockState(nPos).equals(Fluids.FLOWING_WATER.defaultFluidState().createLegacyBlock())))
                                continue;
                            event.getWorld().destroyBlock(nPos, false);
                            event.getWorld().sendBlockUpdated(nPos, event.getWorld().getBlockState(nPos), Blocks.AIR.defaultBlockState(), 3);
                        }
                    }
                }
                data.radius -= r;
                data.write(stack.getPersistentData());
                ToolDamageUtil.damage(ToolStack.from(tool), 2 * r, event.getPlayer(), null);
            }
        }
    }

    @Override
    public ITextComponent getDisplayName(IModifierToolStack tool, int level) {
		ModDataNBT tag = tool.getPersistentData();
		if (tag != null) {
	    	Utils.GeneralNBTData data = Utils.GeneralNBTData.read(tag);

	        if (data.radius > 0) {
	        	return getDisplayName().copy().append(TextFormatting.BLUE + " [" + "Actual Radius: " + TextFormatting.WHITE + Math.round(data.radius * 100) / 100 + "]");
	        }
		}

        return getDisplayName(level);
    }
}