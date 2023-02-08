package ms55.taiga.common.world.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class MeteoriteFeatureConfig implements IFeatureConfig {
	public static final Codec<MeteoriteFeatureConfig> CODEC = RecordCodecBuilder.create((p_236568_0_) -> {
	      return p_236568_0_.group(BlockState.CODEC.fieldOf("center").forGetter((config) -> {
	         return config.center;
	      }), BlockState.CODEC.fieldOf("hull").forGetter((config) -> {
	         return config.hull;
	      })).apply(p_236568_0_, MeteoriteFeatureConfig::new);
	});
	public final BlockState center;
	public final BlockState hull;

	public MeteoriteFeatureConfig(BlockState center, BlockState hull) {
		this.center = center;
		this.hull = hull;
	}
}