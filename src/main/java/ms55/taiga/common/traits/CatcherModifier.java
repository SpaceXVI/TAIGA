package ms55.taiga.common.traits;

import ms55.taiga.common.util.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
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

public class CatcherModifier extends Modifier {
	private static int chance = 3;
	private static float costMulti = 0.25f;

    public CatcherModifier() {
        super(TextFormatting.RED.getColor());
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
	public void addVolatileData(ToolRebuildContext context, int level, ModDataNBT volatileData) {
		volatileData.putBoolean(IModifiable.SHINY, true);
	}

    @Override
    public void onInventoryTick(IModifierToolStack tool, int level, World world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        if (!world.isClientSide && holder instanceof PlayerEntity) {
        	ModDataNBT tag = tool.getPersistentData();
            if (tag != null) {
                Utils.GeneralNBTData data = Utils.GeneralNBTData.read(tag);
                if (data.id != 0) {
                	if (!stack.getTag().getCompound(ToolStack.TAG_VOLATILE_MOD_DATA).getBoolean(IModifiable.SHINY.toString())) {
                    	stack.getTag().getCompound(ToolStack.TAG_VOLATILE_MOD_DATA).putBoolean(IModifiable.SHINY.toString(), true);
    				}
                } else {
                	if (stack.getTag().getCompound(ToolStack.TAG_VOLATILE_MOD_DATA).getBoolean(IModifiable.SHINY.toString())) {
                    	stack.getTag().getCompound(ToolStack.TAG_VOLATILE_MOD_DATA).putBoolean(IModifiable.SHINY.toString(), false);
                	}
                }
            }
        }
    }

    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent event) {
		if (event.getSource().getEntity() instanceof PlayerEntity) {
			World world = event.getSource().getEntity().level;
	    	PlayerEntity player = (PlayerEntity) event.getSource().getEntity();
	    	if (!world.isClientSide) {
	            if (event.getEntityLiving() instanceof PlayerEntity) {
	                return;
	            }

	            if (ModifierUtil.getModifierLevel(player.getMainHandItem(), this) > 0) {
	            	LivingEntity target = event.getEntityLiving();
	            	ToolStack toolStack = ToolStack.from(player.getMainHandItem());
	                Utils.GeneralNBTData data = Utils.GeneralNBTData.read(toolStack.getPersistentData());

	                if (data.id == 0 && target != null) {
	                	if (RANDOM.nextInt((int) target.getMaxHealth()) <= chance && target instanceof LivingEntity) {
	                        event.setCanceled(true);
	                        data.id = target.getId();
	                        data.name = target.getDisplayName().getString();
	                        data.write(toolStack.getPersistentData());
	                        player.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
	                        target.getPersistentData().putBoolean("taiga:no_drops", true);
	                        target.die(new DamageSource("Catcher"));
	                    }
	                }
	            }
			}
		}
    }

    @SubscribeEvent
    public void removeLoot(LivingDropsEvent event) {
    	if (event.getSource().getEntity() != null) {
    		World world = event.getSource().getEntity().level;
        	if (!world.isClientSide) {
        		if (event.getEntityLiving().getPersistentData().getBoolean("taiga:no_drops")) {
    				event.setCanceled(true);
    			}
        	}
		}
    }

    @SubscribeEvent
    public void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
    	World world = event.getWorld();
    	BlockPos pos = event.getPos();
        ItemStack tool = event.getItemStack();
        if (!world.isClientSide && ModifierUtil.getModifierLevel(tool, this) > 0 /*&& altKey.isKeyDown()*/) {
        	ToolStack toolStack = ToolStack.from(tool);
            Utils.GeneralNBTData data = Utils.GeneralNBTData.read(toolStack.getPersistentData());
        	Entity entity = world.getEntity(data.id);
        	if (entity != null) {
        		entity.setPos(pos.getX(), pos.getY(), pos.getZ());
        		float rot = event.getPlayer().xRot + 180;
        		entity.xRot = (rot > 360 ? rot - 360 : rot);
        		entity.getPersistentData().putBoolean("taiga:no_drops", true); //Is this needed? I have no idea if world#getEntity keeps the NBT data
        		world.addFreshEntity(entity);
        		event.getPlayer().level.playSound(null, pos, SoundEvents.ENDERMAN_TELEPORT, SoundCategory.AMBIENT, 1.0F, 1.0F);
        		data.id = 0;
        		data.name = "";
                data.write(toolStack.getPersistentData());
        		ToolDamageUtil.damage(toolStack, RANDOM.nextInt((int) (toolStack.getCurrentDurability() * costMulti)), event.getPlayer(), tool);
            }
        }
    }

    @Override
    public ITextComponent getDisplayName(IModifierToolStack tool, int level) {
		ModDataNBT tag = tool.getPersistentData();
		if (tag != null) {
			Utils.GeneralNBTData data = Utils.GeneralNBTData.read(tag);
            if (data.id != 0) {
    	        return getDisplayName().copy().append(TextFormatting.DARK_PURPLE  + " [" + "Captured: " + TextFormatting.LIGHT_PURPLE + data.name + "]");
            }
		}

		return getDisplayName(level);
    }
}
