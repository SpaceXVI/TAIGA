package ms55.taiga.common.data;

import java.util.function.Consumer;

import ms55.taiga.TAIGA;
import ms55.taiga.common.block.TAIGABlocks;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class Recipes extends RecipeProvider implements IConditionBuilder {

    public Recipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
		for (RegistryObject<Block> block : TAIGABlocks.BLOCKS.getEntries()) {
			if (block.getId().getPath().contains("_block") && !block.getId().getPath().contains("_cobble") && !(block.get() instanceof FlowingFluidBlock)) {
				Item ingot = ForgeRegistries.ITEMS.getValue(new ResourceLocation(TAIGA.MODID, block.getId().getPath().replace("_block", "_ingot")));
				Item nugget = ForgeRegistries.ITEMS.getValue(new ResourceLocation(TAIGA.MODID, block.getId().getPath().replace("_block", "_nugget")));

				makeRecipe(consumer, block.get().asItem(), ingot, true);
				makeRecipe(consumer, ingot, nugget, false);
			}
		}
    }

    private void makeRecipe(Consumer<IFinishedRecipe> consumer, Item result, Item ingredient, boolean blockingot) {
    	String prefix = blockingot ? "block_ingot" : "ingot_nugget";

    	//Ingot -> Block
    	//Nugget -> Ingot
    	ConditionalRecipe.builder()
    	.addCondition(
    		and(
    	    	itemExists(TAIGA.MODID, result.getRegistryName().getPath()),
    	    	itemExists(TAIGA.MODID, ingredient.getRegistryName().getPath())
    		)		
    	)	
        .addRecipe(
        	ShapedRecipeBuilder.shaped(result)
            	.pattern("###")
            	.pattern("###")
            	.pattern("###")
            	.define('#', ingredient)
                .group("")
                .unlockedBy("has_item", has(ingredient))
                ::save
        )
        .build(consumer, new ResourceLocation(TAIGA.MODID, prefix + "/" + result.getRegistryName().getPath()));

    	//Ingot <- Block
    	//Nugget <- Ingot
    	ConditionalRecipe.builder()
    	.addCondition(
    		and(
    	    	itemExists(TAIGA.MODID, result.getRegistryName().getPath()),
    	    	itemExists(TAIGA.MODID, ingredient.getRegistryName().getPath())
    		)		
    	)	
        .addRecipe(
        	ShapelessRecipeBuilder.shapeless(ingredient, 9)
            	.requires(result)
                .group("")
                .unlockedBy("has_item", has(result))
                ::save
        )
        .build(consumer, new ResourceLocation(TAIGA.MODID, prefix + "/" + ingredient.getRegistryName().getPath()));
    }
}