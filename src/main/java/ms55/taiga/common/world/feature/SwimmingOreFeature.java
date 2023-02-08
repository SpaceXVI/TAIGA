package ms55.taiga.common.world.feature;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class SwimmingOreFeature extends Feature<OreFeatureConfig> {
	public SwimmingOreFeature(Codec<OreFeatureConfig> codec) {
		super(codec);
	}

	@Override
   	public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, OreFeatureConfig config) {	
		//nextInt?
		if (config.target.test(reader.getBlockState(pos), rand)) {
			if (Blocks.AIR.defaultBlockState().equals(reader.getBlockState(pos.above()))) {
				//System.out.println(config.state.toString() + " : " + pos);
				return reader.setBlock(pos, config.state, 2);
			} else {
				while (config.target.test(reader.getBlockState(pos.above()), rand) && pos.above().getY() <= 112) {
					pos = pos.above();
				}

				if (config.target.test(reader.getBlockState(pos), rand) /*&& Blocks.AIR.defaultBlockState().equals(reader.getBlockState(pos.above()))*/) {
					//System.out.println(config.state.toString() + " : " + pos);
					return reader.setBlock(pos, config.state, 2);
				}
			}
		} else if (Blocks.AIR.defaultBlockState().equals(reader.getBlockState(pos))) {
			while (Blocks.AIR.defaultBlockState().equals(reader.getBlockState(pos.below())) && pos.below().getY() > 0) {
				pos = pos.below();
			}

			if (config.target.test(reader.getBlockState(pos.below()), rand) && Blocks.AIR.defaultBlockState().equals(reader.getBlockState(pos))) {
				//System.out.println(config.state.toString() + " : " + pos.below());
				return reader.setBlock(pos.below(), config.state, 2);
			}
		} else if (!config.target.test(reader.getBlockState(pos), rand)) {
			while (!config.target.test(reader.getBlockState(pos.below()), rand) && pos.getY() > 12) {
				pos = pos.below();
			}

			if (config.target.test(reader.getBlockState(pos.below()), rand) /* && Blocks.AIR.defaultBlockState().equals(reader.getBlockState(pos))) */) {
				//System.out.println(config.state.toString() + " : " + pos.below());
				return reader.setBlock(pos.below(), config.state, 2);
			}

			while (!config.target.test(reader.getBlockState(pos.above()), rand) && pos.getY() <= 112) {
				pos = pos.above();
			}

			pos = pos.above();
			if (config.target.test(reader.getBlockState(pos), rand)) {
				if (Blocks.AIR.defaultBlockState().equals(reader.getBlockState(pos.above()))) {
					//System.out.println(config.state.toString() + " : " + pos);
					return reader.setBlock(pos, config.state, 2);
				} else {
					while (config.target.test(reader.getBlockState(pos.above()), rand) && pos.above().getY() <= 112) {
						pos = pos.above();
					}

					if (config.target.test(reader.getBlockState(pos), rand) /*&& Blocks.AIR.defaultBlockState().equals(reader.getBlockState(pos.above())*/) {
						//System.out.println(config.state.toString() + " : " + pos);
						return reader.setBlock(pos, config.state, 2);
					}
				}
			}
		}

		return false;
	}
}