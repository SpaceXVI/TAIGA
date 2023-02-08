package ms55.taiga.common.data;

import ms55.taiga.TAIGA;
import ms55.taiga.common.block.TAIGABlocks;
import ms55.taiga.common.fluid.TAIGAFluids;
import ms55.taiga.common.fluid.TAIGAFluids.FluidRegistration;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;
import slimeknights.tconstruct.common.TinkerTags;

public class FluidTags extends FluidTagsProvider {
	public FluidTags(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
		super(generatorIn, TAIGA.MODID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		for (RegistryObject<Block> block : TAIGABlocks.BLOCKS.getEntries()) {
			if (block.get() instanceof FlowingFluidBlock) {
				FluidRegistration fluid = TAIGAFluids.map2.get(block.getId().getPath().replace("_block", ""));

				tag(fluid.OBJECT.getLocalTag()).add(fluid.FLUID.get());
				tag(fluid.OBJECT.getForgeTag()).add(fluid.FLUID.get());
				tag(TinkerTags.Fluids.METAL_LIKE).addTag(fluid.OBJECT.getForgeTag());
			}
		}

		tag(TinkerTags.Fluids.EXPENSIVE_METAL_SPILLING)
		.addTag(TAIGAFluids.BASALT_FLUID.OBJECT.getForgeTag())
		.addTag(TAIGAFluids.DILITHIUM_FLUID.OBJECT.getForgeTag())
		.addTag(TAIGAFluids.JAUXUM_FLUID.OBJECT.getForgeTag())
		.addTag(TAIGAFluids.KARMESINE_FLUID.OBJECT.getForgeTag())
		.addTag(TAIGAFluids.METEORITE_FLUID.OBJECT.getForgeTag())
		.addTag(TAIGAFluids.OBSIDIORITE_FLUID.OBJECT.getForgeTag())
		.addTag(TAIGAFluids.OVIUM_FLUID.OBJECT.getForgeTag())
		.addTag(TAIGAFluids.PROMETHEUM_FLUID.OBJECT.getForgeTag())
		.addTag(TAIGAFluids.TERRAX_FLUID.OBJECT.getForgeTag())
		.addTag(TAIGAFluids.TIBERIUM_FLUID.OBJECT.getForgeTag())
		.addTag(TAIGAFluids.TRIBERIUM_FLUID.OBJECT.getForgeTag())
		.addTag(TAIGAFluids.TRITONITE_FLUID.OBJECT.getForgeTag());

		tag(TinkerTags.Fluids.CHEAP_METAL_SPILLING)
		.addTag(TAIGAFluids.ABYSSUM_FLUID.OBJECT.getForgeTag())
		.addTag(TAIGAFluids.ADAMANT_FLUID.OBJECT.getForgeTag())
		.addTag(TAIGAFluids.ASTRIUM_FLUID.OBJECT.getForgeTag())
		.addTag(TAIGAFluids.AURORIUM_FLUID.OBJECT.getForgeTag())
		.addTag(TAIGAFluids.DURANITE_FLUID.OBJECT.getForgeTag())
		.addTag(TAIGAFluids.DYONITE_FLUID.OBJECT.getForgeTag())
		.addTag(TAIGAFluids.EEZO_FLUID.OBJECT.getForgeTag())
		.addTag(TAIGAFluids.FRACTUM_FLUID.OBJECT.getForgeTag())
		.addTag(TAIGAFluids.IGNITZ_FLUID.OBJECT.getForgeTag())
		.addTag(TAIGAFluids.IMPEROMITE_FLUID.OBJECT.getForgeTag())
		.addTag(TAIGAFluids.IOX_FLUID.OBJECT.getForgeTag())
		.addTag(TAIGAFluids.LUMIX_FLUID.OBJECT.getForgeTag())
		.addTag(TAIGAFluids.NIHILITE_FLUID.OBJECT.getForgeTag())
		.addTag(TAIGAFluids.NITRONITE_FLUID.OBJECT.getForgeTag())
		.addTag(TAIGAFluids.NUCLEUM_FLUID.OBJECT.getForgeTag())
		.addTag(TAIGAFluids.OSRAM_FLUID.OBJECT.getForgeTag())
		.addTag(TAIGAFluids.PALLADIUM_FLUID.OBJECT.getForgeTag())
		.addTag(TAIGAFluids.PROXII_FLUID.OBJECT.getForgeTag())
		.addTag(TAIGAFluids.SEISMUM_FLUID.OBJECT.getForgeTag())
		.addTag(TAIGAFluids.SOLARIUM_FLUID.OBJECT.getForgeTag())
		.addTag(TAIGAFluids.URU_FLUID.OBJECT.getForgeTag())
		.addTag(TAIGAFluids.VALYRIUM_FLUID.OBJECT.getForgeTag())
		.addTag(TAIGAFluids.VIBRANIUM_FLUID.OBJECT.getForgeTag())
		.addTag(TAIGAFluids.VIOLIUM_FLUID.OBJECT.getForgeTag())
		.addTag(TAIGAFluids.YRDEEN_FLUID.OBJECT.getForgeTag());
	}
}