package ms55.taiga.common.data;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import com.mojang.datafixers.util.Pair;

import ms55.taiga.TAIGA;
import ms55.taiga.common.block.TAIGABlocks;
import ms55.taiga.common.item.TAIGAItems;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.loot.LootParameterSet;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTableManager;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.ValidationTracker;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ForgeLootTableProvider;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

public class LootTablesStuff extends ForgeLootTableProvider {
    private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> tables = ImmutableList.of(Pair.of(BlockProvider::new, LootParameterSets.BLOCK));

	public LootTablesStuff(DataGenerator dataGeneratorIn) {
		super(dataGeneratorIn);
	}

	@Override
	protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
		return tables;
	}

	@Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker) {
        final Set<ResourceLocation> modLootTableIds =
            LootTables
                .all()
                .stream()
                .filter(lootTable -> lootTable.getNamespace().equals(TAIGA.MODID))
                .collect(Collectors.toSet());

        for (ResourceLocation id : Sets.difference(modLootTableIds, map.keySet()))
            validationtracker.reportProblem("Missing mod loot table: " + id);

        map.forEach((id, lootTable) ->
            LootTableManager.validate(validationtracker, id, lootTable));
    }

	@Override
	public String getName() {
		return "TAIGA's Loot Tables";
	}

	private static class BlockProvider extends BlockLootTables {
		@Override
        protected void addTables() {
	    	for (RegistryObject<Block> Block : TAIGABlocks.BLOCKS.getEntries()) {
	    		if (Block.get() instanceof FlowingFluidBlock) {
	    			continue;
	    		}

	    		String str = Block.getId().getPath();
	    		if (str.equalsIgnoreCase("meteorite_block")) {
	    			this.dropOther(Block.get(), TAIGABlocks.METEORITE_COBBLE_BLOCK.get());
	    		} else if (str.equalsIgnoreCase("obsidiorite_block")) {
	    			this.dropOther(Block.get(), TAIGABlocks.OBSIDIORITE_COBBLE_BLOCK.get());
	    		} else if (str.equalsIgnoreCase("dilithium_ore")) {
	    			this.add(Block.get(), (block) -> {
	    				return createOreDrop(block, TAIGAItems.DILITHIUM_CRYSTAL.get());
	    			});
	    		} else if (str.equalsIgnoreCase("tiberium_ore")) {
	    			this.add(Block.get(), (block) -> {
	    				return createOreDrop(block, TAIGAItems.TIBERIUM_CRYSTAL.get());
	    			});
	    		} else {
		    		this.dropSelf(Block.get());
	    		}
	    	}
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ForgeRegistries.BLOCKS.getValues().stream().filter(entityType -> entityType.getRegistryName() != null && TAIGA.MODID.equals(entityType.getRegistryName().getNamespace())).collect(Collectors.toSet());
        }
	}     
}