package ms55.taiga.common.data.smeltery;

import java.lang.reflect.Field;
import java.util.function.Consumer;

import ms55.taiga.TAIGA;
import ms55.taiga.common.item.TAIGAItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import slimeknights.tconstruct.common.data.BaseRecipeProvider;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.IToolRecipeHelper;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.recipe.material.MaterialRecipeBuilder;

public class ToolsRecipeProvider extends BaseRecipeProvider implements IMaterialRecipeHelper, IToolRecipeHelper {

	public ToolsRecipeProvider(DataGenerator generator) {
		super(generator);
	}

	@Override
	protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
		this.addMaterialsRecipes(consumer);
		//this.addPartRecipes(consumer);
		//this.addTinkerStationRecipes(consumer);
	}

	//What the hell am I doing
	private void addMaterialsRecipes(Consumer<IFinishedRecipe> consumer){
		for (RegistryObject<Item> item : TAIGAItems.ITEMS.getEntries()) {
			String name = item.get().getRegistryName().getPath();
			if (name.contains("ingot")) {
				//Just put it in a list, me
    	        for (Field field : MaterialIds.class.getDeclaredFields()) {
        	        MaterialId mat = null;
					try {
						mat = (MaterialId) field.get(this);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
					if (mat.getPath().equalsIgnoreCase(name.replace("_ingot", ""))) {
	        	        registerMetalMaterial(consumer, mat, name.replace("_ingot", ""), false);
					}
    	        }
			}
		}
	}

	private void registerMetalMaterial(Consumer<IFinishedRecipe> consumer, MaterialId material, String name, boolean optional) {
	    String matName = material.getPath();
	    Consumer<IFinishedRecipe> wrapped = optional ? withCondition(consumer, tagCondition("ingots/" + name)) : consumer;
	    registerMaterial(wrapped, material, Ingredient.of(getTag("forge", "ingots/" + name)), 1, 1, matName + "/ingot");
	    wrapped = optional ? withCondition(consumer, tagCondition("nuggets/" + name)) : consumer;
	    registerMaterial(wrapped, material, Ingredient.of(getTag("forge", "nuggets/" + name)), 1, 9, matName + "/nugget");
	    wrapped = optional ? withCondition(consumer, tagCondition("storage_blocks/" + name)) : consumer;
	    registerMaterial(wrapped, material, Ingredient.of(getTag("forge", "storage_blocks/" + name)), 9, 1, matName + "/block");
	}

	private void registerMaterial(Consumer<IFinishedRecipe> consumer, MaterialId material, Ingredient input, int value, int needed, String saveName) {
	    MaterialRecipeBuilder.materialRecipe(material)
	                         .setIngredient(input)
	                         .setValue(value)
	                         .setNeeded(needed)
	                         .build(consumer, location("tools/materials/" + saveName));
	}

	protected static ResourceLocation location(String id) {
	    return new ResourceLocation(TAIGA.MODID, id);
	}

	@Override
	public String getName() {
		return "TAIGA Tool Recipes";
	}
}