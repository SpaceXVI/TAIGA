package ms55.taiga.client.config;

import ms55.taiga.TAIGA;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = TAIGA.MODID, bus = Bus.MOD)
public class TAIGAConfig {

	private static final int RESFAC_MIN_VALUE = 0;
    private static final int RESFAC_MAX_VALUE = 100;

    private static final int IRON_DEFAULT = 20;
    private static final int TIBERIUM_DEFAULT = 10;
    private static final int PROMETHEUM_DEFAULT = 18;
    private static final int VALYRIUM_DEFAULT = 10;
    private static final int DILITHIUM_DEFAULT = 12;
    private static final int OSRAM_DEFAULT = 1;
    private static final int DURANITE_DEFAULT = 1;
    private static final int BASALT_DEFAULT = 12;
    private static final int EEZO_DEFAULT = 5;
    private static final int KARMESINE_DEFAULT = 12;
    private static final int JAUXUM_DEFAULT = 12;
    private static final int OVIUM_DEFAULT = 12;
    private static final int VIBRANIUM_DEFAULT = 2;
    private static final int URU_DEFAULT = 1;
    private static final int AURORIUM_DEFAULT = 10;
    private static final int PALLADIUM_DEFAULT = 10;
    private static final int ABYSSUM_DEFAULT = 4;

	public static class Generation {

		public final IntValue IRON_VAL;
	    public final IntValue TIBERIUM_VAL;
	    public final IntValue PROMETHEUM_VAL;
	    public final IntValue VALYRIUM_VAL;
	    public final IntValue OSRAM_VAL;
	    public final IntValue DURANITE_VAL;
	    public final IntValue BASALT_VAL;
	    public final IntValue EEZO_VAL;
	    public final IntValue KARMESINE_VAL;
	    public final IntValue OVIUM_VAL;
	    public final IntValue JAUXUM_VAL;
	    public final IntValue VIBRANIUM_VAL;
	    public final IntValue DILITHIUM_VAL;
	    public final IntValue URU_VAL;
	    public final IntValue AURORIUM_VAL;
	    public final IntValue PALLADIUM_VAL;
	    public final IntValue ABYSSUM_VAL;

	    public final BooleanValue IRON_GEN;
	    public final BooleanValue END_GEN;

		public Generation(ForgeConfigSpec.Builder builder) {
			builder.push("Ore Generation")
				.comment("Change values here to change the count of ores per chunk and other stuff");

			IRON_GEN = builder
					.comment("set to true to enable extra iron ore generation")
					.define("ironGen", false);

			END_GEN = builder
					.comment("set to true to enable extra endstone on the bottom of end islands")
					.define("endGen", true);

			IRON_VAL = builder
				.comment("generation count per chunk (workd ONLY if ironGen is true)")
				.defineInRange("ironVal", IRON_DEFAULT, RESFAC_MIN_VALUE, RESFAC_MAX_VALUE);

			TIBERIUM_VAL = builder
				.comment("generation count per chunk")
				.defineInRange("tiberiumVal", TIBERIUM_DEFAULT, RESFAC_MIN_VALUE, RESFAC_MAX_VALUE);

			PROMETHEUM_VAL = builder
				.comment("generation count per chunk")
				.defineInRange("prometheumVal", PROMETHEUM_DEFAULT, RESFAC_MIN_VALUE, RESFAC_MAX_VALUE);

			VALYRIUM_VAL = builder
				.comment("generation count per chunk")
				.defineInRange("valyriumVal", VALYRIUM_DEFAULT, RESFAC_MIN_VALUE, RESFAC_MAX_VALUE);

			DILITHIUM_VAL = builder
				.comment("generation count per chunk")
				.defineInRange("dilithiumVal", DILITHIUM_DEFAULT, RESFAC_MIN_VALUE, RESFAC_MAX_VALUE);

			OSRAM_VAL = builder
				.comment("generation count per chunk")
				.defineInRange("osramVal", OSRAM_DEFAULT, RESFAC_MIN_VALUE, RESFAC_MAX_VALUE);

			DURANITE_VAL = builder
				.comment("generation count per chunk")
				.defineInRange("duraniteVal", DURANITE_DEFAULT, RESFAC_MIN_VALUE, RESFAC_MAX_VALUE);
				
			BASALT_VAL = builder
				.comment("generation count per chunk")
				.defineInRange("basaltVal", BASALT_DEFAULT, RESFAC_MIN_VALUE, RESFAC_MAX_VALUE);

			EEZO_VAL = builder
				.comment("generation count per chunk")
				.defineInRange("eezoVal", EEZO_DEFAULT, RESFAC_MIN_VALUE, RESFAC_MAX_VALUE);
			
			KARMESINE_VAL = builder
				.comment("generation count per chunk")
				.defineInRange("karmesineVal", KARMESINE_DEFAULT, RESFAC_MIN_VALUE, RESFAC_MAX_VALUE);

			JAUXUM_VAL = builder
				.comment("generation count per chunk")
				.defineInRange("jauxumVal", JAUXUM_DEFAULT, RESFAC_MIN_VALUE, RESFAC_MAX_VALUE);
				
			OVIUM_VAL = builder
				.comment("generation count per chunk")
				.defineInRange("oviumVal", OVIUM_DEFAULT, RESFAC_MIN_VALUE, RESFAC_MAX_VALUE);

			VIBRANIUM_VAL = builder
				.comment("generation count per chunk")
				.defineInRange("vibraniumVal", VIBRANIUM_DEFAULT, RESFAC_MIN_VALUE, RESFAC_MAX_VALUE);

			URU_VAL = builder
				.comment("generation count per chunk")
				.defineInRange("uruVal", URU_DEFAULT, RESFAC_MIN_VALUE, RESFAC_MAX_VALUE);

			AURORIUM_VAL = builder
				.comment("generation count per chunk")
				.defineInRange("auroriumVal", AURORIUM_DEFAULT, RESFAC_MIN_VALUE, RESFAC_MAX_VALUE);
	
			PALLADIUM_VAL = builder
				.comment("generation count per chunk")
				.defineInRange("palladiumVal", PALLADIUM_DEFAULT, RESFAC_MIN_VALUE, RESFAC_MAX_VALUE);

			ABYSSUM_VAL = builder
				.comment("generation count per chunk")
				.defineInRange("abyssumVal", ABYSSUM_DEFAULT, RESFAC_MIN_VALUE, RESFAC_MAX_VALUE);

			builder.pop();
		}
	}

	public static final ForgeConfigSpec COMMON_SPEC;
	public static final Generation GENERATION;

	static {
		ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
		GENERATION = new Generation(builder);

		COMMON_SPEC = builder.build();
	}
}