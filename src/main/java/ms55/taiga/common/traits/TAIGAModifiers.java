package ms55.taiga.common.traits;

import ms55.taiga.TAIGA;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import slimeknights.tconstruct.library.modifiers.Modifier;

public final class TAIGAModifiers {
	public static final DeferredRegister<Modifier> MODIFIERS = DeferredRegister.create(Modifier.class, TAIGA.MODID);

	public static final RegistryObject<AnalysingModifier> ANALYSING = MODIFIERS.register("analysing", AnalysingModifier::new); //So useless.. can luckily be useful (1/10)
	public static final RegistryObject<ArcaneModifier> ARCANE = MODIFIERS.register("arcane", ArcaneModifier::new); //Works! (4/10)
	public static final RegistryObject<BerserkModifier> BERSERK = MODIFIERS.register("berserk", BerserkModifier::new); //More buffed than before! (8/10)
	public static final RegistryObject<BlindModifier> BLIND = MODIFIERS.register("blind", BlindModifier::new); //Pretty much useless (1/10)
	public static final RegistryObject<BrightModifier> BRIGHT = MODIFIERS.register("bright", BrightModifier::new); //Pretty much useless (1/10)
	public static final RegistryObject<CascadeModifier> CASCADE = MODIFIERS.register("cascade", CascadeModifier::new); //Cool (6/10)
	public static final RegistryObject<CatcherModifier> CATCHER = MODIFIERS.register("catcher", CatcherModifier::new); //WIP (NA/10)
	public static final RegistryObject<CongenialModifier> CONGENIAL = MODIFIERS.register("congenial", CongenialModifier::new); //Works! Can be useful (8/10)
	public static final RegistryObject<CrushingModifier> CRUSHING = MODIFIERS.register("crushing", CrushingModifier::new); //WIP (NA/10)
	public static final RegistryObject<CursedModifier> CURSED = MODIFIERS.register("cursed", CursedModifier::new); //Sinister, but I like it (2/10)
	public static final RegistryObject<CurvatureModifier> CURVATURE = MODIFIERS.register("curvature", CurvatureModifier::new); //Only good for ender pearls (needs sound fixing) (2/10)
	public static final RegistryObject<DarkModifier> DARK = MODIFIERS.register("dark", DarkModifier::new); //Pretty much useless (1/10)
	public static final RegistryObject<DecayModifier> DECAY = MODIFIERS.register("decay", DecayModifier::new); //WIP (NA/10)
	public static final RegistryObject<DiffuseModifier> DIFFUSE = MODIFIERS.register("diffuse", DiffuseModifier::new); //More exp for no drops (2/10)
	public static final RegistryObject<DissolvingModifier> DISSOLVING = MODIFIERS.register("dissolving", DissolvingModifier::new); //High chance to remove exp, low chance to 2x 3x or 4x it (3/10)
	public static final RegistryObject<FractureModifier> FRACTURE = MODIFIERS.register("fracture", FractureModifier::new); //Useful for mining tbh (8/10)
	public static final RegistryObject<FragileModifier> FRAGILE = MODIFIERS.register("fragile", FragileModifier::new); //I mean what it does is cool ig (2/10)
	public static final RegistryObject<GarishlyModifier> GARISHLY = MODIFIERS.register("garishly", GarishlyModifier::new); //Good for blaze items ig (7/10)
	public static final RegistryObject<GlimmerModifier> GLIMMER = MODIFIERS.register("glimmer", GlimmerModifier::new); //Pretty much useless (2/10)
	public static final RegistryObject<HeroicModifier> HEROIC = MODIFIERS.register("heroic", HeroicModifier::new); //Needs reworking tbh (NA/10)
	public static final RegistryObject<HollowModifier> HOLLOW = MODIFIERS.register("hollow", HollowModifier::new); //It works but like, its too overpowered (6/10)
	public static final RegistryObject<MeltingModifier> MELTING = MODIFIERS.register("melting", MeltingModifier::new); //Sinister and is kinda funny (2/10)
	public static final RegistryObject<MutateModifier> MUTATE = MODIFIERS.register("mutate", MutateModifier::new); //Can be useful, beware of the lava (6/10)
	public static final RegistryObject<NatureBoundModifier> NATUREBOUND = MODIFIERS.register("naturebound", NatureBoundModifier::new); //Not that useful, but atleast it heals the item (3/10)
	public static final RegistryObject<PortedModifier> PORTED = MODIFIERS.register("ported", PortedModifier::new); //Can be too overpowered if spammed... (7/10)
	public static final RegistryObject<PulverizingModifier> PULVERIZING = MODIFIERS.register("pulverizing", PulverizingModifier::new); //Doesnt do much tbh, useless (1/10)
	public static final RegistryObject<ResonanceModifier> RESONANCE = MODIFIERS.register("resonance", ResonanceModifier::new); //Gotta fix Heroic first to test
	public static final RegistryObject<RevivingModifier> REVIVING = MODIFIERS.register("reviving", RevivingModifier::new); //TODO : Delete.
	public static final RegistryObject<SlaughteringModifier> SLAUGHTERING = MODIFIERS.register("slaughtering", SlaughteringModifier::new); //Good for increased drops (8/10)
	public static final RegistryObject<SoftyModifier> SOFTY = MODIFIERS.register("softy", SoftyModifier::new); //Useless (2/10)
	public static final RegistryObject<SoulEaterModifier> SOULEATER = MODIFIERS.register("souleater", SoulEaterModifier::new); //Amazing (10/10)
	public static final RegistryObject<SuperHeavyModifier> SUPERHEAVY = MODIFIERS.register("superheavy", SuperHeavyModifier::new); //Makes you mine slower over time, kinda punishing (3/10)
	public static final RegistryObject<TantrumModifier> TANTRUM = MODIFIERS.register("tantrum", TantrumModifier::new); //I mean it's funny (5/10)
	public static final RegistryObject<UnstableModifier> UNSTABLE = MODIFIERS.register("unstable", UnstableModifier::new); //Useless, funny though (1/10)
	public static final RegistryObject<WhirlModifier> WHIRL = MODIFIERS.register("whirl", WhirlModifier::new); //TODO : Delete.
}