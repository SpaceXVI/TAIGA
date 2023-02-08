package ms55.taiga.common.data;

import java.lang.reflect.Field;

import ms55.taiga.TAIGA;
import ms55.taiga.common.block.TAIGABlocks;
import ms55.taiga.common.data.smeltery.MaterialIds;
import ms55.taiga.common.fluid.TAIGAFluids;
import ms55.taiga.common.item.TAIGAItems;
import ms55.taiga.common.traits.TAIGAModifiers;
import ms55.taiga.common.util.Utils;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BlockItem;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fml.RegistryObject;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.modifiers.Modifier;

public class Language extends LanguageProvider {
	public Language(DataGenerator gen) {
        super(gen, TAIGA.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
    	add("itemGroup.taigablocks", "TAIGA Blocks");
    	add("itemGroup.taigaitems", "TAIGA Items");
    	for (RegistryObject<Block> Block : TAIGABlocks.BLOCKS.getEntries()) {
    		String name = Block.getId().getPath();
    		add("block.taiga." + name, Utils.makeNameUpperCase(name));
		}

    	for (RegistryObject<?> Item : TAIGAItems.ITEMS.getEntries()) {
    		if (Item.get() instanceof BlockItem) {
    			continue;
    		}
    		String name = Item.getId().getPath();
    		add("item.taiga." + name, Utils.makeNameUpperCase(name));
		}

    	for (RegistryObject<Fluid> Fluid : TAIGAFluids.FLUIDS.getEntries()) {
    		String name = Fluid.getId().getPath();
    		add("fluid.taiga." + name, Utils.makeNameUpperCase(name));
		}

    	for (RegistryObject<Modifier> Modifier : TAIGAModifiers.MODIFIERS.getEntries()) {
    		String name = Modifier.getId().getPath();
    		add("modifier.taiga." + name, Utils.makeNameUpperCase(name));
		}

    	for (Field field : MaterialIds.class.getDeclaredFields()) {
	        MaterialId mat = null;
			try {
				mat = (MaterialId) field.get(this);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
			if (mat != null) {
	    		add("material.taiga." + mat.getPath(), Utils.makeNameUpperCase(mat.getPath()));
			}
        }
    }
}