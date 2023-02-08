package ms55.taiga.common.data;

import ms55.taiga.TAIGA;
import ms55.taiga.common.data.smeltery.MaterialDataProvider;
import ms55.taiga.common.data.smeltery.MaterialRecipeProvider;
import ms55.taiga.common.data.smeltery.MaterialStatsDataProvider;
import ms55.taiga.common.data.smeltery.MaterialTraitsDataProvider;
import ms55.taiga.common.data.smeltery.SmelteryRecipes;
import ms55.taiga.common.data.smeltery.ToolsRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@EventBusSubscriber(modid = TAIGA.MODID, bus = Bus.MOD)
public class DataGenerators {

	@SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();

        if (event.includeServer()) {
            generator.addProvider(new Recipes(generator));
            generator.addProvider(new SmelteryRecipes(generator));
            generator.addProvider(new ToolsRecipeProvider(generator));
            generator.addProvider(new LootTablesStuff(generator));
            MaterialDataProvider materials = new MaterialDataProvider(generator);
            generator.addProvider(materials);
            generator.addProvider(new MaterialStatsDataProvider(generator, materials));
            generator.addProvider(new MaterialTraitsDataProvider(generator, materials));
            generator.addProvider(new MaterialRecipeProvider(generator));

    		generator.addProvider(new FluidTags(generator, helper));
            BlockTags blockTags = new BlockTags(generator, helper);
    		generator.addProvider(blockTags);
    		generator.addProvider(new ItemTags(generator, blockTags, helper));
        }
        if (event.includeClient()) {
            generator.addProvider(new ItemModels(generator, event.getExistingFileHelper()));
        	generator.addProvider(new BlockStates(generator, event.getExistingFileHelper()));
    		generator.addProvider(new Language(generator));
        }
    }
}