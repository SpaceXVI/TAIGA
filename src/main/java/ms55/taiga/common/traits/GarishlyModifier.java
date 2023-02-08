package ms55.taiga.common.traits;

import net.minecraft.block.material.Material;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class GarishlyModifier extends Modifier {

	public GarishlyModifier() {
		super(TextFormatting.YELLOW.getColor());
        MinecraftForge.EVENT_BUS.register(this);
	}

    @SubscribeEvent
    public void onMobDrops(LivingDropsEvent event) {
        World world = event.getEntity().level;
        if (!world.isClientSide && event.getSource().getEntity() instanceof PlayerEntity) {
        	PlayerEntity player = (PlayerEntity) event.getSource().getEntity();
            if (event.getEntity() instanceof MobEntity && ModifierUtil.getModifierLevel(player.getMainHandItem(), this) > 0) {
                int r = RANDOM.nextInt(2);
                ItemStack i = null;
                switch (r) {
                    case 0:
                        i = new ItemStack(Items.BLAZE_POWDER, RANDOM.nextInt(3));
                        break;
                    case 1:
                        i = new ItemStack(Items.BLAZE_ROD, RANDOM.nextInt(3));
                        break;
                    case 2:
                        i = new ItemStack(Items.COAL, RANDOM.nextInt(3));
                        break;
                }

                event.getDrops().add(new ItemEntity(world, event.getEntity().blockPosition().getX(), event.getEntity().blockPosition().getY(),
                		event.getEntity().blockPosition().getZ(), i));
            }
        }
    }

    //TODO : Use GLMs
    @Override
	public void afterBlockBreak(IModifierToolStack tool, int level, ToolHarvestContext context) {
    	float r = RANDOM.nextFloat();
    	LootContext.Builder lcb = (new LootContext.Builder(context.getWorld()).withParameter(LootParameters.TOOL, new ItemStack(tool.getItem()))
        		.withParameter(LootParameters.ORIGIN, context.getLiving().position()));
        if (RANDOM.nextBoolean()) {
        	context.getState().getDrops(lcb).clear();
        } else if (r <= 0.25 && context.getState().getMaterial() == Material.STONE) {
            @SuppressWarnings("deprecation")
			ItemStack stack = new ItemStack(Item.byBlock(context.getWorld().getBlockState(context.getPos()).getBlock()), RANDOM.nextInt(3));
            context.getState().getDrops(lcb).add(0, stack);
            ToolDamageUtil.damage(tool, RANDOM.nextInt(6) + 1, context.getPlayer(), null);
        }
    }
}