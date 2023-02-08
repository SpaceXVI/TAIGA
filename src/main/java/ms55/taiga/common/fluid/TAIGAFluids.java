package ms55.taiga.common.fluid;

import static ms55.taiga.common.block.TAIGABlocks.BLOCKS;
import static ms55.taiga.common.item.TAIGAItems.ITEMS;

import java.util.HashMap;

import ms55.taiga.TAIGA;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import slimeknights.mantle.registration.object.FluidObject;

public class TAIGAFluids {
	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, TAIGA.MODID);
	public static HashMap<String, FluidObject<ForgeFlowingFluid>> map = new HashMap<String, FluidObject<ForgeFlowingFluid>>();
	public static HashMap<String, FluidRegistration> map2 = new HashMap<String, FluidRegistration>();

	public static FluidRegistration BASALT_FLUID = new FluidRegistration("basalt_fluid", 0xFFe4ddc3, 550, 10, 6000);
    public static FluidRegistration TIBERIUM_FLUID = new FluidRegistration("tiberium_fluid", 0xFFd4ff00, 400, 10, 8000);
    public static FluidRegistration AURORIUM_FLUID = new FluidRegistration("aurorium_fluid", 0xFFefae94, 750, 10, 10000);
    public static FluidRegistration PROMETHEUM_FLUID = new FluidRegistration("prometheum_fluid", 0xFF372c49, 850, 10, 10000);
    public static FluidRegistration DURANITE_FLUID = new FluidRegistration("duranite_fluid", 0xFFacddeb, 1400, 10, 10000);
    public static FluidRegistration VALYRIUM_FLUID = new FluidRegistration("valyrium_fluid", 0xFFe85c31, 1915, 10, 10000);
    public static FluidRegistration VIBRANIUM_FLUID = new FluidRegistration("vibranium_fluid", 0xFFbad2d9, 3050, 10, 10000);
    public static FluidRegistration KARMESINE_FLUID = new FluidRegistration("karmesine_fluid", 0xFFeb484a, 750, 10, 9000);
    public static FluidRegistration JAUXUM_FLUID = new FluidRegistration("jauxum_fluid", 0xFF68c663, 750, 10, 9000);
    public static FluidRegistration OVIUM_FLUID = new FluidRegistration("ovium_fluid", 0xFF7d77c3, 750, 10, 9000);
    public static FluidRegistration TERRAX_FLUID = new FluidRegistration("terrax_fluid", 0xFFa5978e, 850, 10, 9000);
    public static FluidRegistration PALLADIUM_FLUID = new FluidRegistration("palladium_fluid", 0xFFee8736, 690, 10, 10000);
    public static FluidRegistration URU_FLUID = new FluidRegistration("uru_fluid", 0xFFbfb9f0, 1200, 10, 10000);
    public static FluidRegistration OSRAM_FLUID = new FluidRegistration("osram_fluid", 0xFFffbc90, 800, 10, 4000);
    public static FluidRegistration ABYSSUM_FLUID = new FluidRegistration("abyssum_fluid", 0xFF21bcc2, 700, 10, 10000);
    public static FluidRegistration EEZO_FLUID = new FluidRegistration("eezo_fluid", 0xFF58798a, 450, 0, 1000);
    public static FluidRegistration TRIBERIUM_FLUID = new FluidRegistration("triberium_fluid", 0xFF66f136, 550, 10, 9000);
    public static FluidRegistration FRACTUM_FLUID = new FluidRegistration("fractum_fluid", 0xFFd2c583, 750, 10, 10000);
    public static FluidRegistration VIOLIUM_FLUID = new FluidRegistration("violium_fluid", 0xFFbfb0e2, 850, 10, 10000);
    public static FluidRegistration PROXII_FLUID = new FluidRegistration("proxii_fluid", 0xFFcefde1, 750, 10, 10000);
    public static FluidRegistration TRITONITE_FLUID = new FluidRegistration("tritonite_fluid", 0xFF8edeff, 550, 10, 10000);
    public static FluidRegistration IGNITZ_FLUID = new FluidRegistration("ignitz_fluid", 0xFFff284b, 950, 10, 6000);
    public static FluidRegistration IMPEROMITE_FLUID = new FluidRegistration("imperomite_fluid", 0xFF7fefa0, 900, 10, 10000);
    public static FluidRegistration SOLARIUM_FLUID = new FluidRegistration("solarium_fluid", 0xFFfef864, 1500, 10, 2000);
    public static FluidRegistration NIHILITE_FLUID = new FluidRegistration("nihilite_fluid", 0xFF6645ba, 580, 10, 10000);
    public static FluidRegistration ADAMANT_FLUID = new FluidRegistration("adamant_fluid", 0xFFff8efe, 1650, 10, 10000);
    public static FluidRegistration DYONITE_FLUID = new FluidRegistration("dyonite_fluid", 0xFFffbd3f, 660, 10, 7000);
    public static FluidRegistration NUCLEUM_FLUID = new FluidRegistration("nucleum_fluid", 0xFFe6ff40, 490, 10, 10000);
    public static FluidRegistration LUMIX_FLUID = new FluidRegistration("lumix_fluid", 0xFFf9f3cc, 450, 10, 8000);
    public static FluidRegistration SEISMUM_FLUID = new FluidRegistration("seismum_fluid", 0xFFecbca8, 720, 10, 10000);
    public static FluidRegistration ASTRIUM_FLUID = new FluidRegistration("astrium_fluid", 0xFF8f385f, 680, 10, 10000);
    public static FluidRegistration NIOB_FLUID = new FluidRegistration("niob_fluid", 0xFF7398b9, 550, 10, 10000);
    public static FluidRegistration YRDEEN_FLUID = new FluidRegistration("yrdeen_fluid", 0xFF8f385f, 710, 10, 10000);
    public static FluidRegistration IOX_FLUID = new FluidRegistration("iox_fluid", 0xFF99323c, 900, 10, 10000);
    public static FluidRegistration METEORITE_FLUID = new FluidRegistration("meteorite_fluid", 0xFF374f3d, 950, 10, 7000);
    public static FluidRegistration OBSIDIORITE_FLUID = new FluidRegistration("obsidiorite_fluid", 0xFF224853, 1050, 10, 7000);
    //public static FluidRegistration MAGMA_FLUID = new FluidRegistration("magma_fluid", 0xFFffc000, 2000, 10, 5000); Since TiC has Magma Cream Fluid this really isn't useful
    public static FluidRegistration NITRONITE_FLUID = new FluidRegistration("nitronite_fluid", 0xFFCCFF00, 3100, 10, 5000);

    // Community
    public static FluidRegistration DILITHIUM_FLUID = new FluidRegistration("dilithium_fluid", 0xFF79aea6, 1500, 10, 5000);

	public static class FluidRegistration {
		public RegistryObject<ForgeFlowingFluid.Source> FLUID;

		public RegistryObject<ForgeFlowingFluid.Flowing> FLUID_FLOWING;

		public RegistryObject<FlowingFluidBlock> FLUID_BLOCK;

		public RegistryObject<Item> FLUID_BUCKET;

		public FluidObject<ForgeFlowingFluid> OBJECT;

		public int COLOR;

		static final ResourceLocation FLUID_STILL = new ResourceLocation("tconstruct", "block/fluid/molten/still");
		static final ResourceLocation FLUID_FLOW = new ResourceLocation("tconstruct", "block/fluid/molten/flowing");

		public FluidRegistration(String name, int color, int temp, int lumen, int visk) {
			FLUID = FLUIDS.register(name, () ->
				new ForgeFlowingFluid.Source(makeProperties(color, temp, lumen, visk)));

			FLUID_FLOWING = FLUIDS.register(name + "_flowing", () ->
				new ForgeFlowingFluid.Flowing(makeProperties(color, temp, lumen, visk)));

			FLUID_BLOCK = BLOCKS.register(name + "_block", () ->
 				new FlowingFluidBlock(FLUID, Block.Properties.of(Material.WATER).noCollission().strength(100.0F).noDrops()));

			FLUID_BUCKET = ITEMS.register(name + "_bucket", () ->
 				new BucketItem(FLUID, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1).tab(ItemGroup.TAB_MISC)));

			OBJECT = new FluidObject<ForgeFlowingFluid>(new ResourceLocation(TAIGA.MODID, name), name, FLUID, FLUID_FLOWING, FLUID_BLOCK);

			map.put(name, OBJECT);
			map2.put(name, this);
		}

		private ForgeFlowingFluid.Properties makeProperties(int color, int temp, int lumen, int visk) {
	        return new ForgeFlowingFluid.Properties(FLUID, FLUID_FLOWING,
	        	FluidAttributes.builder(FLUID_STILL, FLUID_FLOW).color(color).temperature(temp).luminosity(lumen).viscosity(visk).density(2000))
	            .bucket(FLUID_BUCKET).block(FLUID_BLOCK);
	    } 
	}
}