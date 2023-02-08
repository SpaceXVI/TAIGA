package ms55.taiga.common.traits;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IModifierToolStack;

public class UnstableModifier extends Modifier {
    public UnstableModifier() {
        super(TextFormatting.DARK_RED.getColor());
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void afterBlockBreak(IModifierToolStack tool, int level, ToolHarvestContext context) {
        if (RANDOM.nextFloat() <= 0.03) {
            if (!context.getWorld().isClientSide()) {
                if (RANDOM.nextBoolean()) {
                    explode(context.getWorld(), context.getLiving(), context.getPos().getX(), context.getPos().getY(), context.getPos().getZ());
                } else explode(context.getWorld(), null, context.getPos().getX(), context.getPos().getY(), context.getPos().getZ());
            }
            ToolDamageUtil.damage(tool, RANDOM.nextInt(10) + 2, context.getLiving(), null);
        }
    }

    @Override
    public int afterEntityHit(IModifierToolStack tool, int level, ToolAttackContext context, float damageDealt) {
    	LivingEntity attacker = context.getAttacker();
    	LivingEntity target = context.getLivingTarget();

    	if (target != null) {
            BlockPos pos = target.blockPosition();
            if (RANDOM.nextFloat() <= 0.04) {
                if (!attacker.level.isClientSide) {
                    if (RANDOM.nextBoolean()) {
                        explode(attacker.level, attacker, pos.getX(), pos.getY(), pos.getZ());
                    } else explode(attacker.level, target, pos.getX(), pos.getY(), pos.getZ());
                }
                ToolDamageUtil.damage(tool, 2 + RANDOM.nextInt(10), attacker, null);
            }
		}

		return 0;
    }

	@SubscribeEvent
    public void onMobDrops(LivingDropsEvent event) {
        World world = event.getEntity().level;
        if (RANDOM.nextFloat() < 0.05 && !world.isClientSide && event.getSource().getEntity() instanceof PlayerEntity) {
        	PlayerEntity player = (PlayerEntity) event.getSource().getEntity();
            if (event.getEntity() instanceof MobEntity && ModifierUtil.getModifierLevel(player.getMainHandItem(), this) > 0) {
                ItemStack i = new ItemStack(Items.GUNPOWDER, RANDOM.nextInt(2));
                event.getDrops().add(new ItemEntity(world, event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), i));
            }
        }
    }

    private void explode(World world, Entity entity, double x, double y, double z) {
    	world.explode(entity, x, y, z, 1.2f + RANDOM.nextFloat() * 5, RANDOM.nextBoolean(), Explosion.Mode.DESTROY); //Break?
    }
}