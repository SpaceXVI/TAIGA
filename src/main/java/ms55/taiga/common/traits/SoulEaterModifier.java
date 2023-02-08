package ms55.taiga.common.traits;

import ms55.taiga.common.util.Utils;
import net.minecraft.entity.LivingEntity;
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

public class SoulEaterModifier extends Modifier {
	@SuppressWarnings("unused")
	private float maxbonus = 30f; 
	private float divisor = 25000f;

    public SoulEaterModifier() {
        super(TextFormatting.RED.getColor());
        MinecraftForge.EVENT_BUS.register(this);
    }

	@SubscribeEvent
    public void onTargetKilled(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof PlayerEntity && event.getEntity() instanceof LivingEntity) {
            World world = event.getSource().getEntity().level;
            ItemStack tool = ((PlayerEntity) event.getSource().getEntity()).getMainHandItem();
            if (!world.isClientSide && ModifierUtil.getModifierLevel(tool, this) > 0) {
                CompoundNBT tag = tool.getTag();
                Utils.GeneralNBTData data = Utils.GeneralNBTData.read(tag);
                float health = ((LivingEntity) event.getEntity()).getMaxHealth();
                data.killcount += 1;
                data.health = health;
                float bonus = Math.round(RANDOM.nextFloat() * health * 100) / divisor;
                data.bonus += bonus;
                data.bonus = (float) Math.round(data.bonus * 100f) / 100f;
                data.write(tag);
                data.write(ToolStack.from(tool).getPersistentData());
            }
        }
    }

    @Override
	public float getEntityDamage(IModifierToolStack tool, int level, ToolAttackContext context, float baseDamage, float damage) {
    	ModDataNBT tag = tool.getPersistentData();
    	if (tag != null) {
            Utils.GeneralNBTData data = Utils.GeneralNBTData.read(tag);
            float bonus = data.bonus;
            return damage + bonus;
		}

        return damage;
    }

    @Override
    public ITextComponent getDisplayName(IModifierToolStack tool, int level) {
    	ModDataNBT tag = tool.getPersistentData();

    	if (tag != null) {
    		Utils.GeneralNBTData data = Utils.GeneralNBTData.read(tag);
            if (data.killcount > 0) {
    			return getDisplayName().copy().append(TextFormatting.WHITE + " [" + "Killed: " + TextFormatting.WHITE + data.killcount + "]")
    										  .append(TextFormatting.WHITE + " [" + "Bonus: " + TextFormatting.WHITE + data.bonus + "]");
            }
		}

        return getDisplayName(level);
    }
}