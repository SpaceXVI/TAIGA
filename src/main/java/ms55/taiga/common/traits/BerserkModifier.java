package ms55.taiga.common.traits;

import java.util.Random;

import ms55.taiga.common.util.Utils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.context.ToolRebuildContext;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class BerserkModifier extends Modifier {
	private Random random = new Random();
    private static int TICK_PER_STAT = 8;

	public BerserkModifier() {
		super(TextFormatting.RED.getColor());
        MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public void addVolatileData(ToolRebuildContext context, int level, ModDataNBT volatileData) {
		volatileData.putBoolean(IModifiable.SHINY, true);
	}

	//If active, your mining speed will be 4x times faster!
	@Override
	public void onBreakSpeed(IModifierToolStack tool, int level, BreakSpeed event, Direction sideHit, boolean isEffective, float miningSpeedModifier) {
		if (!event.getPlayer().level.isClientSide) {
			ModDataNBT tag = tool.getPersistentData();
	        Utils.GeneralNBTData data = Utils.GeneralNBTData.read(tag);
			if (data.active) {
		        event.setNewSpeed(event.getNewSpeed() * 4);
			}
		}
	}

	//If active, your attack will be 4x times stronger!
	@Override
	public float getEntityDamage(IModifierToolStack tool, int level, ToolAttackContext context, float baseDamage, float damage) {
		ModDataNBT tag = tool.getPersistentData();
        Utils.GeneralNBTData data = Utils.GeneralNBTData.read(tag);
		if (data.active) {
	        damage *= 4;
		}
		return damage;
	}

	//every 8 ticks there's a 5% chance that your tool will get damaged heavily while activated, and an 95% chance that it'll just get normally damaged.
    @Override
    public void onInventoryTick(IModifierToolStack tool, int level, World world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
    	if (!world.isClientSide && holder instanceof PlayerEntity) {
        	ModDataNBT tag = tool.getPersistentData();
            PlayerEntity player = (PlayerEntity) holder;
            if (tag != null) {
            	Utils.GeneralNBTData data = Utils.GeneralNBTData.read(tag);
            	if (data.active) {
            		if (!stack.getTag().getCompound(ToolStack.TAG_VOLATILE_MOD_DATA).getBoolean(IModifiable.SHINY.toString())) {
//            			System.out.println(tool.getPersistentData().getCopy().toString());
//            			System.out.println(stack.getTag().toString());
            			stack.getTag().getCompound(ToolStack.TAG_VOLATILE_MOD_DATA).putBoolean(IModifiable.SHINY.toString(), true);
//            			System.out.println(tool.getPersistentData().getCopy().toString());
//            			System.out.println(stack.getTag().toString());
					}

            		if (player.tickCount % TICK_PER_STAT != 0) {
            			return;
            		}

            		if (random.nextFloat() > 0.95f) {
            			ToolDamageUtil.directDamage(tool, tool.getCurrentDurability() / 3, player, stack);
            		} else {
            			ToolDamageUtil.damage(tool, 1, player, stack);
            		}
            	} else {
            		if (stack.getTag().getCompound(ToolStack.TAG_VOLATILE_MOD_DATA).getBoolean(IModifiable.SHINY.toString())) {
//            			System.out.println(tool.getPersistentData().getCopy().toString());
//            			System.out.println(stack.getTag().toString());
            			stack.getTag().getCompound(ToolStack.TAG_VOLATILE_MOD_DATA).putBoolean(IModifiable.SHINY.toString(), false);
//            			System.out.println(tool.getPersistentData().getCopy().toString());
//            			System.out.println(stack.getTag().toString());
            		}
            	}
            }
        }
    }

    @SubscribeEvent
    public void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        World world = event.getWorld();
        ItemStack tool = event.getPlayer().getMainHandItem();
        if (!world.isClientSide && ModifierUtil.getModifierLevel(tool, this) > 0 /*&& altKey.isKeyDown()*/) {
            CompoundNBT tag = tool.getTag();
            Utils.GeneralNBTData data = Utils.GeneralNBTData.read(tag);
            if (data.active) {
                data.active = false;
                data.write(tag);
                data.write(ToolStack.from(tool).getPersistentData());
            } else {
            	ToolDamageUtil.damage(ToolStack.from(tool), 10, event.getPlayer(), tool);
                data.active = true;
                data.write(tag);
                data.write(ToolStack.from(tool).getPersistentData());
            }
        }
    }
}
