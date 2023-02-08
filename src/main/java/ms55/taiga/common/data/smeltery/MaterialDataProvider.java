package ms55.taiga.common.data.smeltery;

import ms55.taiga.common.fluid.TAIGAFluids;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;

public class MaterialDataProvider extends AbstractMaterialDataProvider {

	public MaterialDataProvider(DataGenerator gen) {
		super(gen);
	}

	@Override
	protected void addMaterials() {
		addMaterial(MaterialIds.BASALT, 2, ORDER_GENERAL, true, TextFormatting.YELLOW.getColor());
		addMaterial(MaterialIds.TIBERIUM, 4, ORDER_NETHER, true, TextFormatting.GREEN.getColor());
		addMaterial(MaterialIds.AURORIUM, 5, ORDER_END, false, TextFormatting.RED.getColor());
		addMaterial(MaterialIds.PROMETHEUM, 4, ORDER_NETHER, false, TextFormatting.DARK_PURPLE.getColor()); //Add a check in the modifier to check if its the handle , should also add a catcher trait                                                 
		addMaterial(MaterialIds.DURANITE, 2, ORDER_SPECIAL, false, TextFormatting.YELLOW.getColor());
		addMaterial(MaterialIds.VALYRIUM, 4, ORDER_NETHER, false, TextFormatting.DARK_GRAY.getColor());
		addMaterial(MaterialIds.VIBRANIUM, 2, ORDER_SPECIAL, false, TextFormatting.GRAY.getColor()); //Resonance : Hanlde, Heroic : Head
		addMaterial(MaterialIds.TERRAX, 3, ORDER_SPECIAL, true, TextFormatting.DARK_GRAY.getColor());
		addMaterial(MaterialIds.PALLADIUM, 5, ORDER_END, false, TextFormatting.GOLD.getColor());
		addMaterial(MaterialIds.URU, 5, ORDER_END, false, TextFormatting.DARK_RED.getColor());
		addMaterial(MaterialIds.EEZO, 2, ORDER_SPECIAL, true, TextFormatting.GOLD.getColor());
		addMaterial(MaterialIds.TRIBERIUM, 4, ORDER_SPECIAL, true, TextFormatting.GREEN.getColor());
		addMaterial(MaterialIds.FRACTUM, 4, ORDER_SPECIAL, false, TextFormatting.DARK_RED.getColor());
		addMaterial(MaterialIds.VIOLIUM, 2, ORDER_GENERAL, false, TextFormatting.DARK_PURPLE.getColor());
		addMaterial(MaterialIds.PROXII, 5, ORDER_END, false, TextFormatting.LIGHT_PURPLE.getColor());
		addMaterial(MaterialIds.TRITONITE, 4, ORDER_NETHER, false, TextFormatting.GOLD.getColor());
		addMaterial(MaterialIds.IGNITZ, 4, ORDER_NETHER, false, TextFormatting.RED.getColor()); //Melting, Garishly : Handle
		addMaterial(MaterialIds.IMPEROMITE, 5, ORDER_END, false, TextFormatting.DARK_RED.getColor());
		addMaterial(MaterialIds.SOLARIUM, 4, ORDER_END, false, TextFormatting.YELLOW.getColor());
		addMaterial(MaterialIds.NIHILITE, 5, ORDER_END, false, TextFormatting.DARK_GRAY.getColor());
		addMaterial(MaterialIds.ADAMANT, 5, ORDER_SPECIAL, false, TextFormatting.GOLD.getColor());
		addMaterial(MaterialIds.DYONITE, 5, ORDER_END, false, TextFormatting.GREEN.getColor());
		addMaterial(MaterialIds.NUCLEUM, 5, ORDER_END, false, TextFormatting.YELLOW.getColor());
		addMaterial(MaterialIds.LUMIX, 3, ORDER_SPECIAL, false, TextFormatting.YELLOW.getColor()); //Bright : Handle, Glimmer : Head
		addMaterial(MaterialIds.SEISMUM, 4, ORDER_NETHER, false, TextFormatting.GREEN.getColor());
		addMaterial(MaterialIds.ASTRIUM, 5, ORDER_SPECIAL, false, TextFormatting.DARK_PURPLE.getColor());
		addMaterial(MaterialIds.NIOB, 4, ORDER_NETHER, false, TextFormatting.RED.getColor());
		addMaterial(MaterialIds.YRDEEN, 4, ORDER_NETHER, false, TextFormatting.RED.getColor());
		addMaterial(MaterialIds.METEORITE, 5, ORDER_END, false, TextFormatting.DARK_GREEN.getColor()); //crumbling : head, pulverizing
		addMaterial(MaterialIds.OBSIDIORITE, 4, ORDER_END, false, TAIGAFluids.OBSIDIORITE_FLUID.COLOR); //Alien

		addMaterial(MaterialIds.KARMESINE, 2, ORDER_GENERAL, false, TextFormatting.RED.getColor());
		addMaterial(MaterialIds.JAUXUM, 2, ORDER_GENERAL, false, TextFormatting.YELLOW.getColor());
		addMaterial(MaterialIds.OVIUM, 2, ORDER_GENERAL, false, TextFormatting.BLUE.getColor());
		addMaterial(MaterialIds.OSRAM, 4, ORDER_GENERAL, false, TextFormatting.GOLD.getColor());
		addMaterial(MaterialIds.DILITHIUM, 2, ORDER_GENERAL, false, TextFormatting.BLUE.getColor());
		addMaterial(MaterialIds.ABYSSUM, 5, ORDER_GENERAL, false, TextFormatting.GOLD.getColor());
	}

	@Override
	public String getName() {
		return "TAIGA Materials";
	}
}