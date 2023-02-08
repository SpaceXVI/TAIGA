package ms55.taiga.common.world;

import ms55.taiga.TAIGA;
import ms55.taiga.common.world.feature.GenFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TAIGA.MODID)
public class BiomeGenEvent {

	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void gen(BiomeLoadingEvent event) {
		BiomeGenerationSettingsBuilder biomegen = event.getGeneration();

		if (event.getCategory() == Biome.Category.NETHER) {
			for (ConfiguredFeature<?, ?> list : GenFeatures.netherList) {
	    		biomegen.addFeature(Decoration.UNDERGROUND_ORES, list);
			}
	    } else if (event.getCategory() == Biome.Category.THEEND) {
	    	for (ConfiguredFeature<?, ?> list : GenFeatures.endList) {
	    		biomegen.addFeature(Decoration.UNDERGROUND_ORES, list);
			}
	    } else {
	    	for (ConfiguredFeature<?, ?> list : GenFeatures.worldList) {
	    		biomegen.addFeature(Decoration.UNDERGROUND_ORES, list);
			}
	    }
	}
}