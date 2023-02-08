package ms55.taiga.common.block;

import static ms55.taiga.TAIGA.RAND;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CobbleBlock extends BasicBlock {

	public CobbleBlock(Material material, float hardness, float resistance, int harvestLevel, int light, String oreDictPrefix) {
        super(Block.Properties.of(material)
        		.requiresCorrectToolForDrops().strength(hardness, resistance).sound(SoundType.STONE).harvestLevel(harvestLevel).lightLevel((x) -> {return light;}), oreDictPrefix);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void breakMoonRock(BlockEvent.BreakEvent event) {
        if (event.getWorld().getBlockState(event.getPos()).getBlock().equals(this)) {
            if (!event.getWorld().isClientSide() && RAND.nextFloat() > 0.9) {
            	event.setCanceled(true);
                if (RAND.nextBoolean()) {
                	event.getWorld().setBlock(event.getPos(), Blocks.LAVA.defaultBlockState(), 2);
                } else {
                    ((World) event.getWorld()).explode(null, event.getPos().getX(), event.getPos().getY() + 1 / 16f, event.getPos().getZ(), 0.5f + RAND.nextFloat() * 1.5f, Explosion.Mode.DESTROY);
                }
            }
        }
    }
}
