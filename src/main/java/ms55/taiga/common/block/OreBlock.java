package ms55.taiga.common.block;

import static ms55.taiga.TAIGA.RAND;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class OreBlock extends BasicBlock {
	public OreBlock(Material material, float hardness, float resistance, int harvestLevel, int lightLevel) {
		super(Block.Properties.of(material)
				.requiresCorrectToolForDrops().strength(hardness, resistance).sound(SoundType.STONE).harvestLevel(harvestLevel).lightLevel((x) -> {return lightLevel;}), null);
	}

	@Override
    public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) {
        return silktouch <= 1 ? 0 : RAND.nextInt(10) + fortune;
    }

	@Override
    public void onBlockExploded(BlockState state, World worldIn, BlockPos pos, Explosion explosionIn) {
        if (!worldIn.isClientSide()) {
            if (RAND.nextFloat() < 0.5) {
                worldIn.explode(null, pos.getX(), pos.getY(), pos.getZ(), RAND.nextFloat() * 4f + 1.5f, Explosion.Mode.BREAK);
            }
        }
    }
}