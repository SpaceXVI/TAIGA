package ms55.taiga.common.traits;

import java.util.Random;

import ms55.taiga.TAIGA;
import ms55.taiga.common.util.Utils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;

public class CursedModifier extends Modifier{
	private Random random = new Random();

	public CursedModifier() {
		super(TextFormatting.RED.getColor());
	}

	@Override
	public void onInventoryTick(IModifierToolStack tool, int level, World world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
		if (!world.isClientSide) {
			ModDataNBT tag = tool.getPersistentData();
			Utils.GeneralNBTData data = Utils.GeneralNBTData.read(tag);
			if (TAIGA.DEBUG && data.curse == 0) {
				data.curse++;
			}

			//Why 1 specifically?
			if (random.nextInt((60000 + data.curse) / (data.curse + 1)) == 1) {
				if (isSelected) {
					data.curse += 10;
				} else {
					data.curse++;
				}
				holder.hurt(new DamageSource("Curse"), random.nextFloat() * holder.getHealth() / 2);
			}
			data.write(tag);
		}
	}

	@Override
    public ITextComponent getDisplayName(IModifierToolStack tool, int level) {
		ModDataNBT tag = tool.getPersistentData();
		if (tag != null) {
			Utils.GeneralNBTData data = Utils.GeneralNBTData.read(tag);
            if (data.curse != 0) {
    	        return getDisplayName().copy().append(TextFormatting.DARK_PURPLE  + " [" + "Curse: " + TextFormatting.WHITE + data.curse + "]");
            }
		}

		return getDisplayName(level);
    }
}