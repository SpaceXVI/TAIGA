package ms55.taiga.common.data.smeltery;

import ms55.taiga.common.traits.TAIGAModifiers;
import net.minecraft.data.DataGenerator;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialTraitDataProvider;

public class MaterialTraitsDataProvider extends AbstractMaterialTraitDataProvider {

	public MaterialTraitsDataProvider(DataGenerator gen, AbstractMaterialDataProvider materials) {
		super(gen, materials);
	}

	@Override
	public String getName() {
		return "TAIGA's Material Traits";
	}

	@Override
	protected void addMaterialTraits() {

		noTraits(MaterialIds.ABYSSUM);
		addDefaultTraits(MaterialIds.ADAMANT, TAIGAModifiers.BERSERK.get()); //
		addDefaultTraits(MaterialIds.ASTRIUM, TAIGAModifiers.PORTED.get()); //
		addDefaultTraits(MaterialIds.AURORIUM, TAIGAModifiers.ARCANE.get()); //
		addDefaultTraits(MaterialIds.BASALT, TAIGAModifiers.SOFTY.get()); //
		addDefaultTraits(MaterialIds.DILITHIUM, TAIGAModifiers.ARCANE.get());
		addDefaultTraits(MaterialIds.DURANITE, TAIGAModifiers.ANALYSING.get()); //
		addDefaultTraits(MaterialIds.DYONITE, TAIGAModifiers.TANTRUM.get()); //
		addDefaultTraits(MaterialIds.EEZO, TAIGAModifiers.DISSOLVING.get(), TAIGAModifiers.SUPERHEAVY.get()); //
		addDefaultTraits(MaterialIds.FRACTUM, TAIGAModifiers.FRACTURE.get()); //
		addDefaultTraits(MaterialIds.IGNITZ, TAIGAModifiers.MELTING.get(), TAIGAModifiers.GARISHLY.get()); //
		addDefaultTraits(MaterialIds.IMPEROMITE, TAIGAModifiers.HOLLOW.get()); //
		noTraits(MaterialIds.IOX);
		noTraits(MaterialIds.JAUXUM);
		noTraits(MaterialIds.KARMESINE);
		addDefaultTraits(MaterialIds.LUMIX, TAIGAModifiers.BRIGHT.get(), TAIGAModifiers.GLIMMER.get()); //
		addDefaultTraits(MaterialIds.METEORITE, /*TAIGAModifiers.CRUMBLING.get(),*/ TAIGAModifiers.PULVERIZING.get()); //
		addDefaultTraits(MaterialIds.NIHILITE, TAIGAModifiers.SOULEATER.get()); //
		addDefaultTraits(MaterialIds.NIOB, TAIGAModifiers.REVIVING.get()); //
		addDefaultTraits(MaterialIds.NITRONITE, TAIGAModifiers.ARCANE.get());
		addDefaultTraits(MaterialIds.NUCLEUM, TAIGAModifiers.DECAY.get(), TAIGAModifiers.MUTATE.get()); //
		noTraits(MaterialIds.OBSIDIORITE/*, TAIGAModifiers.ALIEN.get()*/); //
		noTraits(MaterialIds.OSRAM);
		noTraits(MaterialIds.OVIUM);
		addDefaultTraits(MaterialIds.PALLADIUM, TAIGAModifiers.DARK.get(), TAIGAModifiers.CURSED.get()); //
		addDefaultTraits(MaterialIds.PROMETHEUM, TAIGAModifiers.BLIND.get(), TAIGAModifiers.CATCHER.get()); //
		addDefaultTraits(MaterialIds.PROXII, TAIGAModifiers.CURVATURE.get()); //
		addDefaultTraits(MaterialIds.SEISMUM, TAIGAModifiers.CASCADE.get()); //
		addDefaultTraits(MaterialIds.SOLARIUM, TAIGAModifiers.SUPERHEAVY.get(), TAIGAModifiers.CRUSHING.get()); //
		addDefaultTraits(MaterialIds.TERRAX, TAIGAModifiers.SLAUGHTERING.get()); //
		addDefaultTraits(MaterialIds.TIBERIUM, TAIGAModifiers.UNSTABLE.get()); //
		addDefaultTraits(MaterialIds.TRIBERIUM, TAIGAModifiers.FRAGILE.get()); //
		addDefaultTraits(MaterialIds.TRITONITE, TAIGAModifiers.WHIRL.get()); //
		addDefaultTraits(MaterialIds.URU, TAIGAModifiers.DIFFUSE.get()); //
		addDefaultTraits(MaterialIds.VALYRIUM, TAIGAModifiers.CONGENIAL.get()); //
		addDefaultTraits(MaterialIds.VIBRANIUM, TAIGAModifiers.RESONANCE.get(), TAIGAModifiers.HEROIC.get()); //
		addDefaultTraits(MaterialIds.VIOLIUM, TAIGAModifiers.ARCANE.get()); //
		addDefaultTraits(MaterialIds.YRDEEN, TAIGAModifiers.NATUREBOUND.get()); //
	}
}
