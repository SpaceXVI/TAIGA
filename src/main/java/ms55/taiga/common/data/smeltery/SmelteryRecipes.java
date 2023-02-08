package ms55.taiga.common.data.smeltery;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import ms55.taiga.TAIGA;
import ms55.taiga.common.block.TAIGABlocks;
import ms55.taiga.common.fluid.TAIGAFluids;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.NotCondition;
import net.minecraftforge.common.crafting.conditions.TagEmptyCondition;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistryEntry;
import slimeknights.mantle.recipe.data.ConsumerWrapperBuilder;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.data.recipe.ISmelteryRecipeHelper;
import slimeknights.tconstruct.library.recipe.alloying.AlloyRecipeBuilder;
import slimeknights.tconstruct.library.recipe.casting.ItemCastingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.melting.MeltingRecipeBuilder;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

public class SmelteryRecipes extends RecipeProvider implements ISmelteryRecipeHelper {

	public SmelteryRecipes(DataGenerator generatorIn) {
		super(generatorIn);
	}

	@Override
	protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
		addCastingAndMeltingRecipes(consumer);
		addAlloyRecipes(consumer);
	}

	private void addCastingAndMeltingRecipes(Consumer<IFinishedRecipe> consumer) {
		for (RegistryObject<Block> block : TAIGABlocks.BLOCKS.getEntries()) {
			if (!block.getId().getPath().contains("_cobble") && !(block.get() instanceof FlowingFluidBlock)) {
				if (block.getId().getPath().contains("_block")) {
					Block ore = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(TAIGA.MODID, block.getId().getPath().replace("_block", "_ore")));
					Item ingot = ForgeRegistries.ITEMS.getValue(new ResourceLocation(TAIGA.MODID, block.getId().getPath().replace("_block", "_ingot")));
					Item nugget = ForgeRegistries.ITEMS.getValue(new ResourceLocation(TAIGA.MODID, block.getId().getPath().replace("_block", "_nugget")));
					Item dust = ForgeRegistries.ITEMS.getValue(new ResourceLocation(TAIGA.MODID, block.getId().getPath().replace("_block", "_dust")));
					Item crystal = ForgeRegistries.ITEMS.getValue(new ResourceLocation(TAIGA.MODID, block.getId().getPath().replace("_block", "_crystal")));
					//--Maybe add crystal casting?--
					loop:
					for (RegistryObject<Fluid> fluid : TAIGAFluids.FLUIDS.getEntries()) {
						if (!fluid.getId().getPath().contains("_flowing") && fluid.getId().getPath().contains(block.getId().getPath().replace("_block", ""))) {
							addCastingRecipesFor(consumer, fluid, TAIGAFluids.map.get(fluid.getId().getPath()), block.get().asItem(), ingot, nugget, crystal, block.getId().getPath().replace("_block", ""));
							addMeltingRecipesFor(consumer, fluid, block.get().asItem(), ore.asItem(), ingot, nugget, crystal, dust, block.getId().getPath().replace("_block", ""));
							break loop;
						}
					}
				}
			}
		}

		addMetalBase(consumer, TAIGAFluids.METEORITE_FLUID.FLUID.get(), 288, false, "storage_blocks/meteorite", 1.5f, "smeltery/melting/meteorite/block", false);
		addMetalBase(consumer, TAIGAFluids.METEORITE_FLUID.FLUID.get(), 288, false, "storage_blocks/meteorite_cobble", 1.5f, "smeltery/melting/meteorite/cobble", false);
		addMetalBase(consumer, TAIGAFluids.OBSIDIORITE_FLUID.FLUID.get(), 288, false, "storage_blocks/obsidiorite", 1.5f, "smeltery/melting/obsidiorite/block", false);
		addMetalBase(consumer, TAIGAFluids.OBSIDIORITE_FLUID.FLUID.get(), 288, false, "storage_blocks/obsidiorite_cobble", 1.5f, "smeltery/melting/obsidiorite/cobble", false);
		//addMetalBase(consumer, TAIGAFluids.MAGMA_FLUID.FLUID.get(), 288, false, "magma", 1.5f, "smeltery/melting/magma", false); //TODO figure out the tag
	}

	private void addCastingRecipesFor(Consumer<IFinishedRecipe> consumer, Supplier<? extends Fluid> fluid, FluidObject<ForgeFlowingFluid> object, @Nullable Item block, @Nullable Item ingot, @Nullable Item nugget, @Nullable Item crystal, String name) {
	    String castingFolder = "smeltery/casting/";

		/*if (block != null) {
			addBlockCastingRecipe(consumer, fluid, FluidValues.METAL_BLOCK, block, castingFolder + "block/" + name);
		}

		if (ingot != null) {
			addIngotCastingRecipe(consumer, fluid, ingot, castingFolder + "ingot/" + name);
		}

		if (nugget != null) {
			addNuggetCastingRecipe(consumer, fluid, nugget, castingFolder + "nugget/" + name);
		}*/

		if (crystal != null) {
			addCrystalCastingRecipe(consumer, fluid, crystal, castingFolder + "crystal/" + name);
		}

		metalCasting(consumer, object, block, ingot, nugget, castingFolder, name);
	}

//	private void addBlockCastingRecipe(Consumer<IFinishedRecipe> consumer, Supplier<? extends Fluid> fluid, int amount, IItemProvider block, String name) {
//	    ItemCastingRecipeBuilder.basinRecipe(block)	
//	                            .setFluidAndTime(new FluidStack(fluid.get(), amount))
//	                            .build(consumer, new ResourceLocation(TAIGA.MODID, name));
//	}
//
//	private void addIngotCastingRecipe(Consumer<IFinishedRecipe> consumer, Supplier<? extends Fluid> fluid, IItemProvider ingot, String name) {
//	    addCastingWithCastRecipe(consumer, fluid, FluidValues.INGOT, TinkerSmeltery.ingotCast, ingot, name);
//	}
//
//	private void addNuggetCastingRecipe(Consumer<IFinishedRecipe> consumer, Supplier<? extends Fluid> fluid, IItemProvider nugget, String name) {
//	    addCastingWithCastRecipe(consumer, fluid, FluidValues.NUGGET, TinkerSmeltery.nuggetCast, nugget, name);
//	}

	private void addCrystalCastingRecipe(Consumer<IFinishedRecipe> consumer, Supplier<? extends Fluid> fluid, IItemProvider crystal, String name) {
		addCastingWithCastRecipe(consumer, fluid, 72, TinkerSmeltery.gemCast, crystal, name);
	}

	private void addCastingWithCastRecipe(Consumer<IFinishedRecipe> consumer, Supplier<? extends Fluid> fluid, int amount, CastItemObject cast, IItemProvider output, String name) {
	    FluidStack fluidStack = new FluidStack(fluid.get(), amount);
	    ItemCastingRecipeBuilder.tableRecipe(output)
	                            .setFluidAndTime(fluidStack)
	                            .setCast(cast, false)
	                            .build(consumer, new ResourceLocation(TAIGA.MODID, name + "_gold_cast"));
	    ItemCastingRecipeBuilder.tableRecipe(output)
	                            .setFluidAndTime(fluidStack)
	                            .setCast(cast.getSingleUseTag(), true)
	                            .build(consumer, new ResourceLocation(TAIGA.MODID, name + "_sand_cast"));
	}

	//Melting
	private void addMeltingRecipesFor(Consumer<IFinishedRecipe> consumer, Supplier<? extends Fluid> fluid, @Nullable Item block, @Nullable Item ore, @Nullable Item ingot, @Nullable Item nugget, @Nullable Item crystal, @Nullable Item dust, String name) {
	    String folder = "smeltery/melting/";
	    String metalFolder = folder + "metal/";
	    String prefix = folder + "/" + name + "/";

	    //addMetalMelting(consumer, fluid.get(), name, block, ore, ingot, nugget, crystal, dust, metalFolder, false);
	    if (crystal != Items.AIR) {
		    addMetalBase(consumer, fluid.get(), 72, false, "crystals/" + name, 0.75f, prefix + "crystal", false);
	    }
		metalMelting(consumer, fluid.get(), name, (ore != Items.AIR), metalFolder, false);
	}

//	private void addMetalMelting(Consumer<IFinishedRecipe> consumer, Fluid fluid, String name, @Nullable Item block, @Nullable Item ore, @Nullable Item ingot, @Nullable Item nugget, @Nullable Item crystal, @Nullable Item dust, String folder, boolean isOptional) {
//	    String prefix = folder + "/" + name + "/";
//
//	    if (block != Items.AIR) {
//		    addMetalBase(consumer, fluid, FluidValues.METAL_BLOCK, false, "storage_blocks/" + name, 3.0f, prefix + "block", isOptional);
//	    }
//
//	    if (ingot != Items.AIR) {
//		    addMetalBase(consumer, fluid, FluidValues.INGOT, false, "ingots/" + name, 1.0f, prefix + "ingot", isOptional);
//	    }
//
//	    if (nugget != Items.AIR) {
//		    addMetalBase(consumer, fluid, FluidValues.NUGGET, false, "nuggets/" + name, 1 / 3f, prefix + "nugget", isOptional);
//	    }
//
//	    if (dust != Items.AIR) {
//		    addMetalBase(consumer, fluid, FluidValues.INGOT, false, "dusts/" + name, 0.75f, prefix + "dust", isOptional);
//	    }
//
//	    if (crystal != Items.AIR) {
//		    addMetalBase(consumer, fluid, 72, false, "crystals/" + name, 0.75f, prefix + "crystal", isOptional);
//	    }
//
//	    if (ore != Items.AIR) {
//	    	addMetalBase(consumer, fluid, FluidValues.INGOT * 2, false, "ores/" + name, 1.5f, prefix + "ore", isOptional);
//	    }
//	}

	private void addMetalBase(Consumer<IFinishedRecipe> consumer, Fluid fluid, int amount, boolean isOre, String tagName, float factor, String recipePath, boolean isOptional) {
	    Consumer<IFinishedRecipe> wrapped = isOptional ? withCondition(consumer, tagCondition(tagName)) : consumer;
	    MeltingRecipeBuilder builder = MeltingRecipeBuilder.melting(Ingredient.of(getTag("forge", tagName)), fluid, amount, factor);

	    if (isOre) {
	    	builder.setOre();
	    }

	    builder.build(wrapped, new ResourceLocation(TAIGA.MODID, recipePath));
	}

	//Alloying
	private void addAlloyRecipes(Consumer<IFinishedRecipe> consumer) {
	    String folder = "smeltery/alloys/";

	    AlloyRecipeBuilder.alloy(TAIGAFluids.TERRAX_FLUID.FLUID.get(), 2)
        .addInput(TAIGAFluids.KARMESINE_FLUID.FLUID.get(), 1)
        .addInput(TAIGAFluids.OVIUM_FLUID.FLUID.get(), 1)
        .addInput(TAIGAFluids.JAUXUM_FLUID.FLUID.get(), 1)
        .build(consumer, location(folder + "terrax"));

	    AlloyRecipeBuilder.alloy(TAIGAFluids.TRIBERIUM_FLUID.FLUID.get(), 1)
        .addInput(TAIGAFluids.TIBERIUM_FLUID.FLUID.get(), 5)
        .addInput(TAIGAFluids.BASALT_FLUID.FLUID.get(), 1)
        .build(consumer, location(folder + "triberium"));

	    AlloyRecipeBuilder.alloy(TAIGAFluids.TRIBERIUM_FLUID.FLUID.get(), 1)
        .addInput(TAIGAFluids.TIBERIUM_FLUID.FLUID.get(), 5)
        .addInput(TAIGAFluids.DILITHIUM_FLUID.FLUID.get(), 2)
        .build(consumer, location(folder + "triberium1"));

	    AlloyRecipeBuilder.alloy(TAIGAFluids.FRACTUM_FLUID.FLUID.get(), 2)
        .addInput(TAIGAFluids.TIBERIUM_FLUID.FLUID.get(), 3)
        .addInput(TinkerFluids.moltenObsidian.get(), 3)
        .addInput(TAIGAFluids.ABYSSUM_FLUID.FLUID.get(), 1)
        .build(consumer, location(folder + "fractum"));

	    AlloyRecipeBuilder.alloy(TAIGAFluids.VIOLIUM_FLUID.FLUID.get(), 2)
        .addInput(TAIGAFluids.AURORIUM_FLUID.FLUID.get(), 3)
        .addInput(TinkerFluids.moltenDebris.get(), 2) //TODO replace debris with ardite
        .build(consumer, location(folder + "violium"));

	    AlloyRecipeBuilder.alloy(TAIGAFluids.PROXII_FLUID.FLUID.get(), 3)
        .addInput(TAIGAFluids.PROMETHEUM_FLUID.FLUID.get(), 3)
        .addInput(TAIGAFluids.PALLADIUM_FLUID.FLUID.get(), 3)
        .addInput(TAIGAFluids.EEZO_FLUID.FLUID.get(), 1)
        .build(consumer, location(folder + "proxii"));

	    AlloyRecipeBuilder.alloy(TAIGAFluids.TRITONITE_FLUID.FLUID.get(), 2)
        .addInput(TinkerFluids.moltenCobalt.get(), 3)
        .addInput(TAIGAFluids.TERRAX_FLUID.FLUID.get(), 2)
        .build(consumer, location(folder + "tritonite"));

	    AlloyRecipeBuilder.alloy(TAIGAFluids.IGNITZ_FLUID.FLUID.get(), 2)
        .addInput(TinkerFluids.moltenDebris.get(), 2) //TODO replace debris with ardite
        .addInput(TAIGAFluids.TERRAX_FLUID.FLUID.get(), 2)
        .addInput(TAIGAFluids.OSRAM_FLUID.FLUID.get(), 1)
        .build(consumer, location(folder + "ignitz"));

	    AlloyRecipeBuilder.alloy(TAIGAFluids.IMPEROMITE_FLUID.FLUID.get(), 2)
        .addInput(TAIGAFluids.DURANITE_FLUID.FLUID.get(), 3)
        .addInput(TAIGAFluids.PROMETHEUM_FLUID.FLUID.get(), 1)
        .addInput(TAIGAFluids.ABYSSUM_FLUID.FLUID.get(), 1)
        .build(consumer, location(folder + "imperomite"));

	    AlloyRecipeBuilder.alloy(TAIGAFluids.SOLARIUM_FLUID.FLUID.get(), 2)
        .addInput(TAIGAFluids.VALYRIUM_FLUID.FLUID.get(), 2)
        .addInput(TAIGAFluids.URU_FLUID.FLUID.get(), 2)
        .addInput(TAIGAFluids.NUCLEUM_FLUID.FLUID.get(), 1)
        .build(consumer, location(folder + "solarium"));

	    AlloyRecipeBuilder.alloy(TAIGAFluids.ADAMANT_FLUID.FLUID.get(), 3)
        .addInput(TAIGAFluids.VIBRANIUM_FLUID.FLUID.get(), 1)
        .addInput(TAIGAFluids.SOLARIUM_FLUID.FLUID.get(), 1)
        .addInput(TAIGAFluids.IOX_FLUID.FLUID.get(), 3)
        .build(consumer, location(folder + "adamant"));

	    AlloyRecipeBuilder.alloy(TAIGAFluids.NIHILITE_FLUID.FLUID.get(), 1)
        .addInput(TAIGAFluids.VIBRANIUM_FLUID.FLUID.get(), 1)
        .addInput(TAIGAFluids.SOLARIUM_FLUID.FLUID.get(), 1)
        .addInput(TAIGAFluids.EEZO_FLUID.FLUID.get(), 1) //Is eezo a good idea?
        .build(consumer, location(folder + "nihilite"));

	    AlloyRecipeBuilder.alloy(TAIGAFluids.SEISMUM_FLUID.FLUID.get(), 4)
        .addInput(TinkerFluids.moltenObsidian.get(), 4)
        .addInput(TAIGAFluids.TRIBERIUM_FLUID.FLUID.get(), 2)
        .addInput(TAIGAFluids.EEZO_FLUID.FLUID.get(), 1)
        .build(consumer, location(folder + "seismum"));

	    AlloyRecipeBuilder.alloy(TAIGAFluids.ASTRIUM_FLUID.FLUID.get(), 2)
        .addInput(TAIGAFluids.TERRAX_FLUID.FLUID.get(), 3)
        .addInput(TAIGAFluids.AURORIUM_FLUID.FLUID.get(), 2)
        .build(consumer, location(folder + "astrium"));

	    AlloyRecipeBuilder.alloy(TAIGAFluids.NIOB_FLUID.FLUID.get(), 3)
        .addInput(TAIGAFluids.PALLADIUM_FLUID.FLUID.get(), 3)
        .addInput(TAIGAFluids.DURANITE_FLUID.FLUID.get(), 1)
        .addInput(TAIGAFluids.OSRAM_FLUID.FLUID.get(), 1)
        .build(consumer, location(folder + "niob"));

	    AlloyRecipeBuilder.alloy(TAIGAFluids.YRDEEN_FLUID.FLUID.get(), 3)
        .addInput(TAIGAFluids.URU_FLUID.FLUID.get(), 3)
        .addInput(TAIGAFluids.VALYRIUM_FLUID.FLUID.get(), 3)
        .addInput(TAIGAFluids.OSRAM_FLUID.FLUID.get(), 1)
        .build(consumer, location(folder + "yrdeen"));

	    AlloyRecipeBuilder.alloy(TAIGAFluids.YRDEEN_FLUID.FLUID.get(), 3)
        .addInput(TAIGAFluids.URU_FLUID.FLUID.get(), 3)
        .addInput(TAIGAFluids.VALYRIUM_FLUID.FLUID.get(), 3)
        .addInput(TAIGAFluids.EEZO_FLUID.FLUID.get(), 1)
        .build(consumer, location(folder + "yrdeen1"));

	    AlloyRecipeBuilder.alloy(TAIGAFluids.YRDEEN_FLUID.FLUID.get(), 3)
        .addInput(TAIGAFluids.URU_FLUID.FLUID.get(), 3)
        .addInput(TAIGAFluids.VALYRIUM_FLUID.FLUID.get(), 3)
        .addInput(TAIGAFluids.ABYSSUM_FLUID.FLUID.get(), 1)
        .build(consumer, location(folder + "yrdeen2"));

	    AlloyRecipeBuilder.alloy(TAIGAFluids.IOX_FLUID.FLUID.get(), 1)
        .addInput(TAIGAFluids.EEZO_FLUID.FLUID.get(), 2)
        .addInput(TAIGAFluids.ABYSSUM_FLUID.FLUID.get(), 2)
        .addInput(TAIGAFluids.OSRAM_FLUID.FLUID.get(), 2)
        .addInput(TAIGAFluids.OBSIDIORITE_FLUID.FLUID.get(), 9) //make this require less obsidiorite since obsidiorite IS harder to obtain
        .build(consumer, location(folder + "iox"));

	    AlloyRecipeBuilder.alloy(TAIGAFluids.IOX_FLUID.FLUID.get(), 1)
        .addInput(TAIGAFluids.EEZO_FLUID.FLUID.get(), 2)
        .addInput(TAIGAFluids.ABYSSUM_FLUID.FLUID.get(), 2)
        .addInput(TAIGAFluids.OSRAM_FLUID.FLUID.get(), 2)
        .addInput(TinkerFluids.moltenObsidian.get(), 9)
        .build(consumer, location(folder + "iox2"));

	    AlloyRecipeBuilder.alloy(TAIGAFluids.LUMIX_FLUID.FLUID.get(), 1)
        .addInput(TAIGAFluids.PALLADIUM_FLUID.FLUID.get(), 1)
        .addInput(TAIGAFluids.TERRAX_FLUID.FLUID.get(), 1)
        .build(consumer, location(folder + "lumix"));

	    AlloyRecipeBuilder.alloy(TAIGAFluids.OBSIDIORITE_FLUID.FLUID.get(), 1)
        .addInput(TAIGAFluids.METEORITE_FLUID.FLUID.get(), 1)
        .addInput(TinkerFluids.moltenObsidian.get(), 1)
        .build(consumer, location(folder + "obsidiorite"));

	    AlloyRecipeBuilder.alloy(TAIGAFluids.NUCLEUM_FLUID.FLUID.get(), 3)
        .addInput(TAIGAFluids.PROXII_FLUID.FLUID.get(), 3)
        .addInput(TAIGAFluids.ABYSSUM_FLUID.FLUID.get(), 1)
        .addInput(TAIGAFluids.OSRAM_FLUID.FLUID.get(), 1)
        .build(consumer, location(folder + "nucleum"));

	    AlloyRecipeBuilder.alloy(TAIGAFluids.NUCLEUM_FLUID.FLUID.get(), 3)
        .addInput(TAIGAFluids.IMPEROMITE_FLUID.FLUID.get(), 3)
        .addInput(TAIGAFluids.OSRAM_FLUID.FLUID.get(), 1)
        .addInput(TAIGAFluids.EEZO_FLUID.FLUID.get(), 1)
        .build(consumer, location(folder + "nucleum1"));

	    AlloyRecipeBuilder.alloy(TAIGAFluids.NUCLEUM_FLUID.FLUID.get(), 3)
        .addInput(TAIGAFluids.NIOB_FLUID.FLUID.get(), 3)
        .addInput(TAIGAFluids.EEZO_FLUID.FLUID.get(), 1)
        .addInput(TAIGAFluids.ABYSSUM_FLUID.FLUID.get(), 1)
        .build(consumer, location(folder + "nucleum2"));

	    AlloyRecipeBuilder.alloy(TAIGAFluids.DYONITE_FLUID.FLUID.get(), 3)
        .addInput(TAIGAFluids.TRIBERIUM_FLUID.FLUID.get(), 3)
        .addInput(TAIGAFluids.FRACTUM_FLUID.FLUID.get(), 1)
        .addInput(TAIGAFluids.SEISMUM_FLUID.FLUID.get(), 1)
        .addInput(TAIGAFluids.OSRAM_FLUID.FLUID.get(), 1)
        .build(consumer, location(folder + "dyonite"));

	    AlloyRecipeBuilder.alloy(TAIGAFluids.DYONITE_FLUID.FLUID.get(), 3)
        .addInput(TAIGAFluids.TIBERIUM_FLUID.FLUID.get(), 12)
        .addInput(TAIGAFluids.FRACTUM_FLUID.FLUID.get(), 1)
        .addInput(TAIGAFluids.SEISMUM_FLUID.FLUID.get(), 1)
        .addInput(TAIGAFluids.OSRAM_FLUID.FLUID.get(), 1)
        .build(consumer, location(folder + "dyonite1"));

	    AlloyRecipeBuilder.alloy(new FluidStack(TAIGAFluids.NITRONITE_FLUID.FLUID.get(), 6), 1500)
        .addInput(TinkerFluids.magma.get(), 6)
        .addInput(TAIGAFluids.OSRAM_FLUID.FLUID.get(), 1)
        .build(consumer, location(folder + "nitronite"));
	}

	public Consumer<IFinishedRecipe> withCondition(Consumer<IFinishedRecipe> consumer, ICondition... conditions) {
	    ConsumerWrapperBuilder builder = ConsumerWrapperBuilder.wrap();

	    for (ICondition condition : conditions) {
	    	builder.addCondition(condition);
	    }

	    return builder.build(consumer);
	}

	public ICondition tagCondition(String name) {
	    return new NotCondition(new TagEmptyCondition("forge", name));
	}

	public INamedTag<Item> getTag(String modId, String name) {
	    return ItemTags.bind(modId + ":" + name);
	}

	public ResourceLocation prefixR(IForgeRegistryEntry<?> entry, String prefix) {
	    ResourceLocation loc = Objects.requireNonNull(entry.getRegistryName());
	    return location(prefix + loc.getPath());
	}

	public ResourceLocation location(String id) {
	    return new ResourceLocation(TAIGA.MODID, id);
	}

	@Override
	public String getName() {
		return "TAIGA Smeltery Recipes";
	}

	@Override
	public String getModId() {
		return TAIGA.MODID;
	}
}