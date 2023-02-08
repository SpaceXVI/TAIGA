package ms55.taiga.common.data;

import ms55.taiga.TAIGA;
import ms55.taiga.common.item.BasicItem;
import ms55.taiga.common.item.TAIGAItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile.UncheckedModelFile;
import net.minecraftforge.client.model.generators.loaders.DynamicBucketModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

public class ItemModels extends ItemModelProvider {

	public ItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, TAIGA.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		for (RegistryObject<Item> item : TAIGAItems.ITEMS.getEntries()) {
			String name = item.get().getRegistryName().getPath();
			if (name.contains("ingot") || name.contains("nugget") || name.contains("dust") || name.contains("crystal")) {
				BasicItem item1 = (BasicItem) item.get();
				getBuilder(name)
			    .parent(new UncheckedModelFile(new ResourceLocation("item/generated")))
			    .texture("layer0", modLoc("item/" + item1.getOreDictPrefix() + "/" + name.replace("_" + item1.getOreDictPrefix(), "")));
			} else if (item.get() instanceof BucketItem) {
				getBuilder(name)
			    .parent(new UncheckedModelFile(new ResourceLocation("forge:item/bucket_drip")))
			    .customLoader((a, b) -> DynamicBucketModelBuilder.begin(this.getBuilder(name), this.existingFileHelper)) //Holy shit
			    .fluid(((BucketItem) item.get()).getFluid());
			}
		}
	}

	@Override
	public String getName() {
		return "TAIGA Item Models";
	}
}