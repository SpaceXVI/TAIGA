package ms55.taiga.common.traits;

import ms55.taiga.common.block.TAIGABlocks;
import ms55.taiga.common.util.Utils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import slimeknights.tconstruct.common.Sounds;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class TantrumModifier extends Modifier {
    public static float max_charges = 12f;
    public static float max_power = 5;

    public TantrumModifier() {
        super(TextFormatting.RED.getColor());
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
	public void addVolatileData(ToolRebuildContext context, int level, ModDataNBT volatileData) {
		volatileData.putBoolean(IModifiable.SHINY, true);
	}

    @Override
    public void afterBlockBreak(IModifierToolStack tool, int level, ToolHarvestContext context) {
        if (!context.getWorld().isClientSide()) {
            if (context.getState().getBlock().equals(TAIGABlocks.TIBERIUM_ORE.get())) {
                LootContext.Builder lcb = (new LootContext.Builder((ServerWorld) context.getWorld()).withParameter(LootParameters.TOOL, new ItemStack(tool.getItem()))
                		.withParameter(LootParameters.ORIGIN, context.getLiving().position()));
            	context.getState().getDrops(lcb).clear();
            	ItemStack stack = new ItemStack(tool.getItem());
    			ModDataNBT tag = tool.getPersistentData();
    	        Utils.GeneralNBTData data = Utils.GeneralNBTData.read(tag);
                if (data.amount >= max_charges) {
                	if (!stack.getTag().getCompound(ToolStack.TAG_VOLATILE_MOD_DATA).getBoolean(IModifiable.SHINY.toString())) {
            			stack.getTag().getCompound(ToolStack.TAG_VOLATILE_MOD_DATA).putBoolean(IModifiable.SHINY.toString(), true);
					}
                    return;
                } else {
                	if (stack.getTag().getCompound(ToolStack.TAG_VOLATILE_MOD_DATA).getBoolean(IModifiable.SHINY.toString())) {
            			stack.getTag().getCompound(ToolStack.TAG_VOLATILE_MOD_DATA).putBoolean(IModifiable.SHINY.toString(), false);
					}
				}
                data.amount += (0.25f + Utils.round2(RANDOM.nextDouble() / 4));
                if (data.amount >= max_charges) {
                	if (!stack.getTag().getCompound(ToolStack.TAG_VOLATILE_MOD_DATA).getBoolean(IModifiable.SHINY.toString())) {
            			stack.getTag().getCompound(ToolStack.TAG_VOLATILE_MOD_DATA).putBoolean(IModifiable.SHINY.toString(), true);
					}
                	if (context.getLiving() instanceof PlayerEntity) {
                    	context.getWorld().playSound(null, context.getLiving().blockPosition(), Sounds.DISCHARGE.getSound(), SoundCategory.AMBIENT, 1f,
                    			0.8f + .2f * RANDOM.nextFloat());
                    }
                }
                data.write(tag);
            }
        }
    }

	@SubscribeEvent
    public void rightClickItem(PlayerInteractEvent.RightClickItem event) {
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        ItemStack tool = event.getItemStack();
        if (!world.isClientSide && ModifierUtil.getModifierLevel(tool, this) > 0 /*&& altKey.isKeyDown()*/) {
        	ToolStack stack = ToolStack.from(tool);
	        Utils.GeneralNBTData data = Utils.GeneralNBTData.read(stack.getPersistentData());
            if (data.amount > 1f) {
                double d = Math.min(Utils.round2(RANDOM.nextDouble() * data.amount), max_power);
                world.explode(event.getPlayer(), pos.getX(), pos.getY(), pos.getZ(), (float) Math.pow((double) 1.2f, d), false, Explosion.Mode.BREAK); //Break?
                data.amount -= d;
                data.write(stack.getPersistentData());
//                if (tool.getTag().getCompound(ToolStack.TAG_VOLATILE_MOD_DATA).getBoolean(IModifiable.SHINY.toString())) {
//                	tool.getTag().getCompound(ToolStack.TAG_VOLATILE_MOD_DATA).putBoolean(IModifiable.SHINY.toString(), false);
//				}
            }
        }
    }

    @Override
    public ITextComponent getDisplayName(IModifierToolStack tool, int level) {
		ModDataNBT tag = tool.getPersistentData();
		if (tag != null) {
	        Utils.GeneralNBTData data = Utils.GeneralNBTData.read(tag);
	        return getDisplayName().copy().append(TextFormatting.RED + " [" + "Charge: " + data.amount + "]");
		}

		return getDisplayName(level);
    }
}