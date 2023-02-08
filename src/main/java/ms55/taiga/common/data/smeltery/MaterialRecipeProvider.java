package ms55.taiga.common.data.smeltery;

import java.lang.reflect.Field;
import java.util.function.Consumer;

import ms55.taiga.common.fluid.TAIGAFluids;
import ms55.taiga.common.item.TAIGAItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.crafting.Ingredient;
import slimeknights.tconstruct.common.data.BaseRecipeProvider;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.library.materials.definition.MaterialId;

public class MaterialRecipeProvider extends BaseRecipeProvider implements IMaterialRecipeHelper {
	public MaterialRecipeProvider(DataGenerator generator) {
		super(generator);
	}

	@Override
	public String getName() {
		return "TAIGA Material Recipe";
	}

	@Override
	protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {		
		addMaterialItems(consumer);
	    addMaterialSmeltery(consumer);
	}

	private void addMaterialItems(Consumer<IFinishedRecipe> consumer) {
		String folder = "tools/materials/";

		for (Field field : MaterialIds.class.getDeclaredFields()) {
	        MaterialId mat = null;
			try {
				mat = (MaterialId) field.get(this);
				if (!mat.getPath().equalsIgnoreCase("basalt") && !mat.getPath().equalsIgnoreCase("eezo") && !mat.getPath().equalsIgnoreCase("terrax") &&
						!mat.getPath().equalsIgnoreCase("tiberium") && !mat.getPath().equalsIgnoreCase("triberium")) {
				    metalMaterialRecipe(consumer, mat, folder, mat.getPath(), false);
				} else if (mat.getPath().equalsIgnoreCase("basalt")) {
				    materialRecipe(consumer, mat, Ingredient.of(TAIGAItems.BASALT_DUST.get(), TAIGAItems.BASALT_INGOT.get()), 1, 1, folder + "basalt");
				    materialRecipe(consumer, mat, Ingredient.of(TAIGAItems.BASALT_NUGGET.get()), 1, 9, folder + "basalt_nugget");
				} else if (mat.getPath().equalsIgnoreCase("eezo")) {
					materialRecipe(consumer, mat, Ingredient.of(TAIGAItems.EEZO_DUST.get(), TAIGAItems.EEZO_INGOT.get()), 1, 1, folder + "eezo");
				    materialRecipe(consumer, mat, Ingredient.of(TAIGAItems.EEZO_NUGGET.get()), 1, 9, folder + "eezo_nugget");
				} else if (mat.getPath().equalsIgnoreCase("terrax")) {
					materialRecipe(consumer, mat, Ingredient.of(TAIGAItems.TERRAX_DUST.get(), TAIGAItems.TERRAX_INGOT.get()), 1, 1, folder + "terrax");
				    materialRecipe(consumer, mat, Ingredient.of(TAIGAItems.TERRAX_NUGGET.get()), 1, 9, folder + "terrax_nugget");
				} else if (mat.getPath().equalsIgnoreCase("tiberium")) {
					materialRecipe(consumer, mat, Ingredient.of(TAIGAItems.TIBERIUM_DUST.get(), TAIGAItems.TIBERIUM_INGOT.get()), 1, 1, folder + "tiberium");
				    materialRecipe(consumer, mat, Ingredient.of(TAIGAItems.TIBERIUM_NUGGET.get()), 1, 9, folder + "tiberium_nugget");
				} else if (mat.getPath().equalsIgnoreCase("triberium")) {
					materialRecipe(consumer, mat, Ingredient.of(TAIGAItems.TRIBERIUM_DUST.get(), TAIGAItems.TRIBERIUM_INGOT.get()), 1, 1, folder + "triberium");
				    materialRecipe(consumer, mat, Ingredient.of(TAIGAItems.TRIBERIUM_NUGGET.get()), 1, 9, folder + "triberium_nugget");
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
        }
	}

	private void addMaterialSmeltery(Consumer<IFinishedRecipe> consumer) {
	    String folder = "tools/materials/";

	    //Do I really need to do that? Can't I just use lists?
	    for (Field field : MaterialIds.class.getDeclaredFields()) {
	        MaterialId mat = null;
			try {
				mat = (MaterialId) field.get(this);
				if (!mat.getPath().equalsIgnoreCase("basalt") && !mat.getPath().equalsIgnoreCase("eezo") && !mat.getPath().equalsIgnoreCase("terrax") &&
						!mat.getPath().equalsIgnoreCase("tiberium") && !mat.getPath().equalsIgnoreCase("triberium")) {
				    materialMeltingCasting(consumer, mat, TAIGAFluids.map2.get(mat.getPath() + "_fluid").OBJECT, false, folder + mat.getPath());
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
        }
	}
}