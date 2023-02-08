package ms55.taiga.common.world.feature;

import ms55.taiga.TAIGA;
import ms55.taiga.common.world.feature.config.CubeFeatureConfig;
import ms55.taiga.common.world.feature.config.MeteoriteFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class CustomFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, TAIGA.MODID);

	public static final RegistryObject<Feature<OreFeatureConfig>> ORE_SWIMMING = register("ore_swimming", new SwimmingOreFeature(OreFeatureConfig.CODEC));
	public static final RegistryObject<Feature<OreFeatureConfig>> ORE_BOTTOM = register("ore_bottom", new BottomOreFeature(OreFeatureConfig.CODEC));
	public static final RegistryObject<Feature<MeteoriteFeatureConfig>> METEORITE = register("meteorite", new MeteoriteFeature(MeteoriteFeatureConfig.CODEC));
	public static final RegistryObject<Feature<CubeFeatureConfig>> CUBE = register("cube", new CubeFeature(CubeFeatureConfig.CODEC));

	private static <T extends IFeatureConfig> RegistryObject<Feature<T>> register(String key, Feature<T> value) {
		return FEATURES.register(key, () -> value);
	}
}