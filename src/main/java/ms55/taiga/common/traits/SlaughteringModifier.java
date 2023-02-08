package ms55.taiga.common.traits;

import com.google.common.collect.Iterables;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;

//TODO : Should make this also do stuff to XP and block drops
public class SlaughteringModifier extends Modifier {

    public SlaughteringModifier() {
        super(TextFormatting.DARK_RED.getColor());
        MinecraftForge.EVENT_BUS.register(this);
    }

	@SubscribeEvent
    public void onMobDrops(LivingDropsEvent event) {
        World world = event.getEntity().level;
        if (event.getSource().getEntity() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getSource().getEntity();
            if (!world.isClientSide && event.getEntity() instanceof LivingEntity && !(event.getEntity() instanceof PlayerEntity) && 
            		ModifierUtil.getModifierLevel(player.getMainHandItem(), this) > 0) {
                ItemEntity itementity = Iterables.get(event.getDrops(), Math.max(event.getDrops().size() - 1, 0));
                ItemStack itemstack = itementity.getItem();
                event.getDrops().add(new ItemEntity(world, event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(),
                		new ItemStack(itemstack.getItem(), RANDOM.nextInt(4) + 1, itemstack.getTag())));
            }
        }
    }
}