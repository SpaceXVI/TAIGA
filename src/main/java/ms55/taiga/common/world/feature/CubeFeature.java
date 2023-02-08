package ms55.taiga.common.world.feature;

import java.util.Random;

import com.mojang.serialization.Codec;

import ms55.taiga.common.world.feature.config.CubeFeatureConfig;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

public class CubeFeature extends Feature<CubeFeatureConfig> {
	public CubeFeature(Codec<CubeFeatureConfig> codec) {
	   super(codec);
   	}

	@Override
   	public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, CubeFeatureConfig config) {
   		if (rand.nextFloat() < 0.02) {
   			//why the hell is this -1 then +1 whats the point? just say call nextInt on config.maxSize and keep the outer +1
   			int outer = rand.nextInt((config.maxSize - 1) + 1) + 1;
   			//I mean this isnt bad but this only makes it possible for inner cubes to be 5 blocks wide
   	        int inner = rand.nextInt(2);
   	        boolean fly = true;  //TODO set a fly option
   	        if (!fly) {
   	            if (reader.getBlockState(pos).equals(Blocks.AIR.defaultBlockState()) && reader.getBlockState(pos.below()).equals(Blocks.AIR.defaultBlockState())) {
   	                // we are in mid air, go down
   	                while (reader.getBlockState(pos.below()).equals(Blocks.AIR.defaultBlockState())) {
   	                	pos = pos.below();
   	                }
   	            }
   	        }
   	        //2 : 5 * 
   	        pos = pos.below((rand.nextInt(4) + 2) * outer);
   	        for (int x = -inner; x <= inner; x++) {
   	            for (int y = -inner; y <= inner; y++) {
   	                for (int z = -inner; z <= inner; z++) {
   	                    if (!reader.getBlockState(pos).equals(Blocks.AIR.defaultBlockState()))
   	                        continue;
   	                    reader.setBlock(new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z), config.center, 2);
   	                }
   	            }
   	        }

   	        for (int x = -outer; x <= outer; x++) {
   	            for (int y = -outer; y <= outer; y++) {
   	                for (int z = -outer; z <= outer; z++) {
   	                    BlockPos nPos = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
   	                    if (reader.getBlockState(nPos).equals(config.center) || !reader.getBlockState(nPos).equals(Blocks.AIR.defaultBlockState()))
   	                        continue;
   	                    reader.setBlock(nPos, config.hull, 2);
   	                }
   	            }
   	        }
   	        return true;
   		}

		return false;
   	}
}