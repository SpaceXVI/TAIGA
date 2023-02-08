package ms55.taiga;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ms55.taiga.TAIGATab.TAIGATabBlock;
import ms55.taiga.TAIGATab.TAIGATabItem;
import ms55.taiga.client.config.TAIGAConfig;
import ms55.taiga.common.block.TAIGABlocks;
import ms55.taiga.common.fluid.TAIGAFluids;
import ms55.taiga.common.item.TAIGAItems;
import ms55.taiga.common.traits.TAIGAModifiers;
import ms55.taiga.common.world.feature.CustomFeatures;
import ms55.taiga.common.world.feature.GenFeatures;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(TAIGA.MODID)
public class TAIGA {
    public static final String MODID = "taiga";
	public static final String NAME = "TAIGA";

    public static final Logger LOGGER = LogManager.getLogger();
    public static boolean DEBUG = false; //Don't forget to switch this...

	public static final ItemGroup BLOCK = new TAIGATabBlock();
	public static final ItemGroup ITEM = new TAIGATabItem();

	public static Random RAND = new Random();

    public TAIGA() {
    	IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(this);
        //MinecraftForge.EVENT_BUS.register(new BiomeGenEvent());
        //MinecraftForge.EVENT_BUS.register(new ExplosionManager());
        //MinecraftForge.EVENT_BUS.register(DataGenerators.class);

        ModLoadingContext.get().registerConfig(Type.COMMON, TAIGAConfig.COMMON_SPEC, "taiga.toml");

        FMLJavaModLoadingContext.get().getModEventBus().register(this);

        TAIGAFluids.FLUIDS.register(eventBus);
        TAIGABlocks.BLOCKS.register(eventBus);
        TAIGAItems.ITEMS.register(eventBus);
        TAIGAModifiers.MODIFIERS.register(eventBus);
        CustomFeatures.FEATURES.register(eventBus);

        //subscribeEvents();
    }

    /*private void subscribeEvents() {
        MinecraftForge.EVENT_BUS.register(new AnalysingModifier());
        MinecraftForge.EVENT_BUS.register(new ArcaneModifier());
        //MinecraftForge.EVENT_BUS.register(new BerserkModifier());
        MinecraftForge.EVENT_BUS.register(new CatcherModifier());
        MinecraftForge.EVENT_BUS.register(new CongenialModifier());
        MinecraftForge.EVENT_BUS.register(new CursedModifier()); //Try client for tooltip events? dfk man
        MinecraftForge.EVENT_BUS.register(new CurvatureModifier());
        MinecraftForge.EVENT_BUS.register(new DiffuseModifier());
        MinecraftForge.EVENT_BUS.register(new DissolvingModifier());
        MinecraftForge.EVENT_BUS.register(new GarishlyModifier());
        MinecraftForge.EVENT_BUS.register(new HollowModifier());
        MinecraftForge.EVENT_BUS.register(new NatureBoundModifier());
        MinecraftForge.EVENT_BUS.register(new PortedModifier());
        MinecraftForge.EVENT_BUS.register(new ResonanceModifier());
        MinecraftForge.EVENT_BUS.register(new RevivingModifier());
        MinecraftForge.EVENT_BUS.register(new SlaughteringModifier());
        MinecraftForge.EVENT_BUS.register(new SoftyModifier());
        MinecraftForge.EVENT_BUS.register(new SoulEaterModifier());
        //MinecraftForge.EVENT_BUS.register(new SuperHeavyModifier());
        MinecraftForge.EVENT_BUS.register(new TantrumModifier());
        MinecraftForge.EVENT_BUS.register(new WhirlModifier());
	}*/

	private void setup(final FMLCommonSetupEvent event) {
    	GenFeatures.registerAllCFs();
    }
}