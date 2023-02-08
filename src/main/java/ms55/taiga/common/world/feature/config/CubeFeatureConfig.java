package ms55.taiga.common.world.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class CubeFeatureConfig implements IFeatureConfig {
	public static final Codec<CubeFeatureConfig> CODEC = RecordCodecBuilder.create((p_236568_0_) -> {
	      return p_236568_0_.group(BlockState.CODEC.fieldOf("center").forGetter((config) -> {
	         return config.center;
	      }), BlockState.CODEC.fieldOf("hull").forGetter((config) -> {
	         return config.hull;
	      }), Codec.intRange(0, 64).fieldOf("maxSize").forGetter((config) -> {
	          return config.maxSize;
	      })).apply(p_236568_0_, CubeFeatureConfig::new);
	});
	public final BlockState center;
	public final BlockState hull;
	public final int maxSize;

	public CubeFeatureConfig(BlockState center, BlockState hull, int maxSize) {
		this.center = center;
		this.hull = hull;
		this.maxSize = maxSize;
	}
}