package ms55.taiga.common.traits;

import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class NatureBoundModifier extends Modifier {

	public NatureBoundModifier() {
		super(TextFormatting.GREEN.getColor());
	}

	@Override
	public float getRepairFactor(IModifierToolStack toolStack, int level, float factor) {
        return (float) (factor * 0.1);
	}

	@Override
	public void onInventoryTick(IModifierToolStack tool, int level, World world, LivingEntity holder, int itemSlot, boolean isSelected, boolean isCorrectSlot, ItemStack stack) {
        Material material = world.getBlockState(holder.blockPosition().below()).getMaterial();
        if (!world.isClientSide && RANDOM.nextFloat() <= 0.3) {
            if (material.equals(Material.GRASS) || material.equals(Material.LEAVES)) {
                ToolDamageUtil.repair(tool, RANDOM.nextInt(2) + 1);
            } else {
            	ToolDamageUtil.damage(tool, 1, holder, null);
            }
        }
	}
}