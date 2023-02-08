package ms55.taiga.common.traits;

import java.util.List;

import com.google.common.collect.Lists;

import ms55.taiga.common.util.Utils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class CurvatureModifier extends Modifier {
    public static int chance = 5;
    public static int distance = 10;

    public CurvatureModifier() {
        super(TextFormatting.DARK_PURPLE.getColor());
        MinecraftForge.EVENT_BUS.register(this);
    }

    //TODO : Use GLMs
    @Override
	public void afterBlockBreak(IModifierToolStack tool, int level, ToolHarvestContext context) {
        if (!context.getWorld().isClientSide() && RANDOM.nextFloat() < 0.05) {
    		LootContext.Builder lcb = (new LootContext.Builder(context.getWorld()).withParameter(LootParameters.TOOL, new ItemStack(tool.getItem()))
            		.withParameter(LootParameters.ORIGIN, context.getLiving().position()));
            List<BlockState> blockstates = Lists.newArrayList(Blocks.STONE.defaultBlockState(), Blocks.NETHERRACK.defaultBlockState(), Blocks.END_STONE.defaultBlockState(),
            		Blocks.AIR.defaultBlockState(), Blocks.DIRT.defaultBlockState());
            BlockState mainstate = context.getState();

            if (blockstates.contains(mainstate)) {
            	return;
            }

            for (int i = 0; i < chance; i++) {
                int x = context.getPos().getX() + Utils.nextInt(RANDOM, -distance, distance);
                int y = context.getPos().getY() + Utils.nextInt(RANDOM, -distance, distance);
                int z = context.getPos().getZ() + Utils.nextInt(RANDOM, -distance, distance);
                BlockPos cPos = new BlockPos(x, y, z);
                BlockState state = context.getWorld().getBlockState(cPos);
                if (blockstates.contains(state)) {
                	context.getState().getDrops(lcb).clear();
                	context.getWorld().setBlockAndUpdate(cPos, mainstate);
                	context.getWorld().playSound(null, context.getPlayer().blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundCategory.AMBIENT, 1.0F, 1.0F);
                	context.getPlayer().sendMessage(new StringTextComponent("Teleported to: " + x + " " + y + " " + z), Util.NIL_UUID);
                	context.getPlayer().setPos(x, y, z);
                    return;
                }
            }
        }
    }

    @Override
    public int afterEntityHit(IModifierToolStack tool, int level, ToolAttackContext context, float damageDealt) {
    	if (RANDOM.nextFloat() <= 0.15) {
            context.getLivingTarget().playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
            changePos(context.getPlayerAttacker(), context.getLivingTarget());
        }
    	return 0;
    }

    @SubscribeEvent
    public void onMobDrops(LivingDropsEvent event) {
        World world = event.getEntity().level;
        if (!world.isClientSide && event.getSource().getEntity() instanceof PlayerEntity) {
        	PlayerEntity player = (PlayerEntity) event.getSource().getEntity();
            if (event.getEntity() instanceof MobEntity && ModifierUtil.getModifierLevel(player.getMainHandItem(), this) > 0) {
                ItemStack item = new ItemStack(Items.ENDER_PEARL, RANDOM.nextInt(2));
                event.getDrops().add(new ItemEntity(world, event.getEntity().blockPosition().getX(), event.getEntity().blockPosition().getY(),
                		event.getEntity().blockPosition().getZ(), item));
            }
        }
    }

    private void changePos(LivingEntity player, LivingEntity target) {
        BlockPos pp = new BlockPos(player.blockPosition());
        BlockPos tp = new BlockPos(target.blockPosition());
        player.setPos(tp.getX(), tp.getY(), tp.getZ());
        target.setPos(pp.getX(), pp.getY(), pp.getZ());
    }
}