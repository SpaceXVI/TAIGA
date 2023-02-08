package ms55.taiga.common.util;

import static ms55.taiga.TAIGA.RAND;

import java.util.LinkedList;
import java.util.Queue;

import ms55.taiga.TAIGA;
import net.minecraft.util.Tuple;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.WorldTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TAIGA.MODID)
public class ExplosionManager {
	private static Queue<Tuple<World, BlockPos>> queue = new LinkedList<Tuple<World, BlockPos>>();

	public static void addExplosion(World world, BlockPos pos) {
		queue.add(new Tuple<World, BlockPos>(world, pos));
	}

	@SubscribeEvent
	public static void handleExplosions(WorldTickEvent event) {
		if (event.phase == Phase.START && !event.world.isClientSide) {
			Tuple<World, BlockPos> pair = queue.poll();
			if (pair != null) {
				ServerWorld world = (ServerWorld) pair.getA();
				BlockPos pos = pair.getB();
				if (TAIGA.RAND.nextBoolean()) {
					world.explode(null, null, null, pos.getX(), pos.getY(), pos.getZ(), RAND.nextFloat() * 2f + 1.5f, true, Explosion.Mode.BREAK);
				} else {
					world.explode(null, null, null, pos.getX(), pos.getY() + 1 / 16f, pos.getZ(), 1.5f, true, Explosion.Mode.BREAK);
				}
			}
		}
	}
}