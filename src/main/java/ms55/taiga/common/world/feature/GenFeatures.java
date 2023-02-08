package ms55.taiga.common.world.feature;

import static ms55.taiga.client.config.TAIGAConfig.GENERATION;

import java.util.ArrayList;
import java.util.List;

import ms55.taiga.common.block.TAIGABlocks;
import ms55.taiga.common.world.feature.config.CubeFeatureConfig;
import ms55.taiga.common.world.feature.config.MeteoriteFeatureConfig;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.ReplaceBlockConfig;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.BlockStateMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;

public class GenFeatures {
	public static List<ConfiguredFeature<?, ?>> worldList = new ArrayList<ConfiguredFeature<?, ?>>();
	public static List<ConfiguredFeature<?, ?>> netherList = new ArrayList<ConfiguredFeature<?, ?>>();
	public static List<ConfiguredFeature<?, ?>> endList = new ArrayList<ConfiguredFeature<?, ?>>();

	public static ConfiguredFeature<?, ?> BASALT_BLOCK;
	public static ConfiguredFeature<?, ?> ORE_EEZO;
	public static ConfiguredFeature<?, ?> ORE_KARMESINE;
	public static ConfiguredFeature<?, ?> ORE_OVIUM;
	public static ConfiguredFeature<?, ?> ORE_JAUXUM;
	public static ConfiguredFeature<?, ?> ORE_VIBRANIUM;
	public static ConfiguredFeature<?, ?> ORE_DILITHIUM;
	public static ConfiguredFeature<?, ?> ORE_VIBRANIUM_2;
	public static ConfiguredFeature<?, ?> ORE_IRON;
	public static ConfiguredFeature<?, ?> METEORITE;

	public static ConfiguredFeature<?, ?> ORE_TIBERIUM;
	public static ConfiguredFeature<?, ?> ORE_PROMETHEUM;
	public static ConfiguredFeature<?, ?> ORE_VALYRIUM;
	public static ConfiguredFeature<?, ?> ORE_OSRAM;

	public static ConfiguredFeature<?, ?> ORE_URU;
	public static ConfiguredFeature<?, ?> ORE_AURORIUM;
	public static ConfiguredFeature<?, ?> ORE_PALLADIUM;
	public static ConfiguredFeature<?, ?> ORE_ABYSSUM;
	public static ConfiguredFeature<?, ?> ORE_ENDSTONE;

	public static void registerAllCFs() {
		BASALT_BLOCK = register("world", "basalt_block", CustomFeatures.ORE_SWIMMING.get().configured(
				new OreFeatureConfig(CustomFiller.LAVA, TAIGABlocks.BASALT_BLOCK.get().defaultBlockState(), GENERATION.BASALT_VAL.get()))
				.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(0, 0, 64)))
				.squared().count(GENERATION.BASALT_VAL.get()));

		//Switch EMERALD_ORE to something else.
		ORE_EEZO = register("world", "ore_eezo", Feature.EMERALD_ORE.configured(
				new ReplaceBlockConfig(Blocks.BEDROCK.defaultBlockState(), TAIGABlocks.EEZO_ORE.get().defaultBlockState()))
				.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(0, 0, 10)))
				.squared().count(40));

		ORE_KARMESINE = register("world", "ore_karmesine", Feature.ORE.configured(
				new OreFeatureConfig(CustomFiller.ANDESITE, TAIGABlocks.KARMESINE_ORE.get().defaultBlockState(), GENERATION.KARMESINE_VAL.get()))
				.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(0, 0, 80)))
				.squared().count(GENERATION.KARMESINE_VAL.get()));

		ORE_OVIUM = register("world", "ore_ovium", Feature.ORE.configured(
				new OreFeatureConfig(CustomFiller.DIORITE, TAIGABlocks.OVIUM_ORE.get().defaultBlockState(), GENERATION.OVIUM_VAL.get()))
				.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(0, 0, 80)))
				.squared().count(GENERATION.OVIUM_VAL.get()));

		ORE_JAUXUM = register("world", "ore_jauxum", Feature.ORE.configured(
				new OreFeatureConfig(CustomFiller.GRANITE, TAIGABlocks.JAUXUM_ORE.get().defaultBlockState(), GENERATION.JAUXUM_VAL.get()))
				.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(0, 0, 80)))
				.squared().count(GENERATION.JAUXUM_VAL.get()));

		ORE_VIBRANIUM = register("world", "ore_vibranium", Feature.ORE.configured(
				new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, TAIGABlocks.VIBRANIUM_ORE.get().defaultBlockState(), 4))
				.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(0, 0, 64)))
				.squared().count(GENERATION.VIBRANIUM_VAL.get()));

		ORE_DILITHIUM = register("world", "ore_dilithium", Feature.ORE.configured(
				new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, TAIGABlocks.DILITHIUM_ORE.get().defaultBlockState(), 6))
				.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(0, 0, 64)))
				.squared().count(GENERATION.DILITHIUM_VAL.get()));

		ORE_VIBRANIUM_2 = register("world", "ore_vibranium_2", Feature.ORE.configured(
				new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, TAIGABlocks.VIBRANIUM_ORE.get().defaultBlockState(), 1))
				.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(0, 0, 128)))
				.squared().count(1));

		if (GENERATION.IRON_GEN.get()) {
			ORE_IRON = register("world", "ore_iron", Feature.ORE.configured(
					new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.IRON_ORE.defaultBlockState(), 6))
					.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(0, 0, 32)))
					.squared().count(GENERATION.IRON_VAL.get()));
		}

		METEORITE = register("world", "meteorite", CustomFeatures.METEORITE.get().configured(
				new MeteoriteFeatureConfig(TAIGABlocks.DURANITE_ORE.get().defaultBlockState(), TAIGABlocks.METEORITE_BLOCK.get().defaultBlockState()))
				.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(16, 0, 112)))
				.squared().count(5));

		//Nether
		ORE_TIBERIUM = register("nether", "ore_tiberium", Feature.ORE.configured(
				new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, TAIGABlocks.TIBERIUM_ORE.get().defaultBlockState(), 35))
				.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(32, 0, 128)))
				.squared().count(GENERATION.TIBERIUM_VAL.get()));

		ORE_PROMETHEUM = register("nether", "ore_prometheum", Feature.ORE.configured(
				new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, TAIGABlocks.PROMETHEUM_ORE.get().defaultBlockState(), 4))
				.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(0, 0, 64)))
				.squared().count(GENERATION.PROMETHEUM_VAL.get()));

		ORE_VALYRIUM = register("nether", "ore_valyrium", Feature.ORE.configured(
				new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, TAIGABlocks.VALYRIUM_ORE.get().defaultBlockState(), 4))
				.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(0, 0, 128)))
				.squared().count(GENERATION.VALYRIUM_VAL.get()));

		ORE_OSRAM = register("nether", "ore_osram", CustomFeatures.ORE_SWIMMING.get().configured(
				new OreFeatureConfig(CustomFiller.LAVA, TAIGABlocks.OSRAM_ORE.get().defaultBlockState(), GENERATION.OSRAM_VAL.get()))
				.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(16, 0, 112)))
				.squared().count(GENERATION.OSRAM_VAL.get()));

		//End
		ORE_URU = register("end", "ore_uru", CustomFeatures.CUBE.get().configured(
				new CubeFeatureConfig(TAIGABlocks.URU_ORE.get().defaultBlockState(), TAIGABlocks.OBSIDIORITE_BLOCK.get().defaultBlockState(), 3))
				.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(0, 0, 96)))
				.squared().count(GENERATION.URU_VAL.get()));

		ORE_AURORIUM = register("end", "ore_aurorium", Feature.ORE.configured(
				new OreFeatureConfig(CustomFiller.ENDSTONE, TAIGABlocks.AUROROIM_ORE.get().defaultBlockState(), 4))
				.squared().decorated(Placement.RANGE.configured(new TopSolidRangeConfig(32, 0, 48))).count(GENERATION.AURORIUM_VAL.get()));

		ORE_PALLADIUM = register("end", "ore_palladium", Feature.ORE.configured(
				new OreFeatureConfig(CustomFiller.ENDSTONE, TAIGABlocks.PALLADIUM_ORE.get().defaultBlockState(), 4))
				.squared().decorated(Placement.RANGE.configured(new TopSolidRangeConfig(48, 0, 64))).count(GENERATION.PALLADIUM_VAL.get()));

		ORE_ABYSSUM = register("end", "ore_abyssum", CustomFeatures.ORE_BOTTOM.get().configured(
				new OreFeatureConfig(CustomFiller.ENDSTONE, TAIGABlocks.ABYSSUM_ORE.get().defaultBlockState(), GENERATION.ABYSSUM_VAL.get())) //TODO make this more chanceous and revamp BOTTOM_ORE Logic
				.squared().decorated(Placement.RANGE.configured(new TopSolidRangeConfig(0, 0, 64))).chance(60).count(GENERATION.ABYSSUM_VAL.get()));

		if (GENERATION.END_GEN.get()) {
			ORE_ENDSTONE = register("end", "endstone", Feature.RANDOM_PATCH.configured((
					new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider())
					.add(Blocks.END_STONE.defaultBlockState(), 1)
					.add(Blocks.AIR.defaultBlockState(), 2), SimpleBlockPlacer.INSTANCE))
					.xspread(8).yspread(2).zspread(10).tries(2).build())
					.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(3, 0, 64))));
		}
	}

	private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String type, String key, ConfiguredFeature<FC, ?> configuredFeature) {
		ConfiguredFeature<FC, ?> CF = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, configuredFeature);
		switch (type) {
		case "world":
			worldList.add(CF);
			break;
		case "nether":
			netherList.add(CF);
			break;
		case "end":
			endList.add(CF);
			break;
		case "none":
			//do nothing
			break;
		}
		return CF;
	}

	public static final class CustomFiller {
	    public static final RuleTest LAVA = new BlockStateMatchRuleTest(Blocks.LAVA.defaultBlockState());
	    public static final RuleTest BEDROCK = new BlockMatchRuleTest(Blocks.BEDROCK);
	    public static final RuleTest AIR = new BlockMatchRuleTest(Blocks.AIR);
	    public static final RuleTest ENDSTONE = new BlockMatchRuleTest(Blocks.END_STONE);
	    public static final RuleTest ANDESITE = new BlockMatchRuleTest(Blocks.ANDESITE);
	    public static final RuleTest DIORITE = new BlockMatchRuleTest(Blocks.DIORITE);
	    public static final RuleTest GRANITE = new BlockMatchRuleTest(Blocks.GRANITE);
	}
}