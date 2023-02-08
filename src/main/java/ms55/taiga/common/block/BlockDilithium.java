package ms55.taiga.common.block;

import static ms55.taiga.TAIGA.RAND;
import static ms55.taiga.common.util.Utils.PREFIX_ORE;
import static slimeknights.tconstruct.library.utils.HarvestLevels.STONE;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class BlockDilithium extends BasicBlock {

    public BlockDilithium() {
    	super(Block.Properties.of(Material.STONE)
    		.requiresCorrectToolForDrops().strength(10.0f, 2.0f).sound(SoundType.STONE).harvestLevel(STONE).lightLevel((x) -> {return 1;}), PREFIX_ORE);
    }

    @Override
    public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) {
        if (silktouch == 0 && RAND.nextBoolean()) {
            return RAND.nextInt(2) + fortune;
        } else return 0;
    }
}