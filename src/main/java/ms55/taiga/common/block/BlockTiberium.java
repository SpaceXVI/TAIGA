package ms55.taiga.common.block;

import static ms55.taiga.TAIGA.RAND;
import static ms55.taiga.common.util.Utils.PREFIX_ORE;
import static slimeknights.tconstruct.library.utils.HarvestLevels.STONE;

import javax.annotation.Nullable;

import ms55.taiga.common.util.ExplosionManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class BlockTiberium extends BasicBlock {

    public BlockTiberium() {
    	super(Block.Properties.of(Material.STONE)
    		.requiresCorrectToolForDrops().strength(10.0f, 2.0f).sound(SoundType.STONE).harvestLevel(STONE).lightLevel((x) -> {return 1;}), PREFIX_ORE);
    }

    @Override
    public int getExpDrop(BlockState state, IWorldReader world, BlockPos pos, int fortune, int silktouch) {
        if (silktouch == 0 && RAND.nextBoolean()) {
            return RAND.nextInt(10) + fortune;
        } else return 0;
    }

    //Adding a delay between explosions would help
    @Override
    public void onBlockExploded(BlockState state, World world, BlockPos pos, Explosion explosion) {
        if (!world.isClientSide) {
            if (RAND.nextBoolean()) {
            	ExplosionManager.addExplosion(world, pos);
            }
        }

        super.onBlockExploded(state, world, pos, explosion);
    }

    @Override
    public void playerDestroy(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity tileEntity, ItemStack stack) {
        if (!world.isClientSide) {
        	if (RAND.nextFloat() < 0.15) {
            	ExplosionManager.addExplosion(world, pos);
            }
        }

        super.playerDestroy(world, player, pos, state, tileEntity, stack);
    }
}