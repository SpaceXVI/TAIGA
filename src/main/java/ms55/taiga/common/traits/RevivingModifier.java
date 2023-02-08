package ms55.taiga.common.traits;

import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import slimeknights.tconstruct.library.modifiers.Modifier;

//WIP, I am gonna see how to do this later ig
public class RevivingModifier extends Modifier {
	public final float chance = 0.15f;

    public RevivingModifier() {
        super(TextFormatting.DARK_PURPLE.getColor());
        MinecraftForge.EVENT_BUS.register(this);
    }

//	@SubscribeEvent
//    public void onEntityKill(LivingDeathEvent event) {
//        BlockPos pos = event.getEntity().blockPosition();
//        World world = event.getEntity().level;
//        if (!world.isClientSide && event.getSource().getEntity() != null) {
//            if (event.getSource().getEntity() instanceof PlayerEntity && event.getEntity() instanceof CreatureEntity) {
//                if (RANDOM.nextFloat() <= chance && ModifierUtil.getModifierLevel(event.getSource().getEntity(), this) > 0) {
//                	event.getEntity().setPosition(pos.getX(), pos.getY(), pos.getZ());
//                	world.addEntity(event.getEntity());
//                	event.getSource().getEntity().playSound(SoundEvents.AMBIENT_CAVE, 1.0F, 1.0F);
//                }
//            }
//        }
//    }
}