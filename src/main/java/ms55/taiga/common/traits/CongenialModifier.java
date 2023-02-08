package ms55.taiga.common.traits;

import java.util.Random;

import ms55.taiga.common.util.Utils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class CongenialModifier extends Modifier {
	private Random random = new Random();

	public CongenialModifier() {
		super(TextFormatting.RED.getColor());
        MinecraftForge.EVENT_BUS.register(this);
	}

    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent event) {
    	if (!event.getEntity().level.isClientSide && event.getSource().getEntity() instanceof PlayerEntity) {
    		PlayerEntity player = (PlayerEntity) event.getSource().getEntity();
    		if (ModifierUtil.getModifierLevel(player.getMainHandItem(), this) > 0) {
                ItemStack tool = player.getMainHandItem();
                String name = event.getEntity().getDisplayName().getString();
                CompoundNBT tag = tool.getTag();
                Utils.GeneralNBTData data = Utils.GeneralNBTData.read(tag);
                if (!data.name.isEmpty()) {
                    return;
                }
                data.name = name;
                data.write(tag);
                data.write(ToolStack.from(tool).getPersistentData());
            }
		}
    }

    @Override
    public float getEntityDamage(IModifierToolStack tool, int level, ToolAttackContext context, float baseDamage, float damage) {
        World world = context.getPlayerAttacker().level;
        if (!world.isClientSide) {
    		ModDataNBT tag = tool.getPersistentData();
            Utils.GeneralNBTData data = Utils.GeneralNBTData.read(tag);
            if (data.name.isEmpty()) {
            	return damage;
            }
            if (context.getLivingTarget() != null) {
            	if (!data.name.equals(context.getLivingTarget().getDisplayName().getString())) {
                    return damage / (random.nextInt(5) + 5);
                }
			}
            float x = (1 + random.nextFloat() * 9);
            return damage * x;
        }

        return damage;
    }

    @Override
    public ITextComponent getDisplayName(IModifierToolStack tool, int level) {
		ModDataNBT tag = tool.getPersistentData();
		if (tag != null) {
			Utils.GeneralNBTData data = Utils.GeneralNBTData.read(tag);
            if (data.name.isEmpty()) {
    	        return getDisplayName().copy().append(TextFormatting.LIGHT_PURPLE  + " [" + "Unbound]");
            } else {
    	        return getDisplayName().copy().append(TextFormatting.DARK_PURPLE  + " [" + "Bound to: " + TextFormatting.LIGHT_PURPLE + data.name + "]");
			}
		}

		return getDisplayName(level);
    }
}
