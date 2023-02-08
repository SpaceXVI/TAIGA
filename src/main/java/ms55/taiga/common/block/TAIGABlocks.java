package ms55.taiga.common.block;

import static ms55.taiga.common.item.TAIGAItems.ITEMS;
import static ms55.taiga.common.util.Utils.DURANITE;
import static ms55.taiga.common.util.Utils.PREFIX_BLOCK;
import static ms55.taiga.common.util.Utils.PREFIX_ORE;
import static ms55.taiga.common.util.Utils.VALYRIUM;
import static ms55.taiga.common.util.Utils.VIBRANIUM;
import static slimeknights.tconstruct.library.utils.HarvestLevels.DIAMOND;
import static slimeknights.tconstruct.library.utils.HarvestLevels.IRON;
import static slimeknights.tconstruct.library.utils.HarvestLevels.NETHERITE;
import static slimeknights.tconstruct.library.utils.HarvestLevels.STONE;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import ms55.taiga.TAIGA;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TAIGABlocks {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TAIGA.MODID);

    // Ores
	public static RegistryObject<Block> BASALT_BLOCK = registerOre("basalt_block", Material.STONE, 20.0f, 35.0f, IRON, PREFIX_BLOCK);
    public static RegistryObject<Block> TIBERIUM_ORE = registerCustomBlock(() -> new BlockTiberium(), "tiberium_ore");
    public static RegistryObject<Block> AUROROIM_ORE = registerOre("aurorium_ore", Material.STONE, 15.0f, 12f, DIAMOND, 2 /*0.2f*/, PREFIX_ORE);
    public static RegistryObject<Block> PROMETHEUM_ORE = registerOre("prometheum_ore", Material.STONE, 20.0f, 12f, DURANITE, 4 /*0.4f*/, PREFIX_ORE);
    public static RegistryObject<Block> DURANITE_ORE = registerOre("duranite_ore", Material.STONE, 25.0f, 1000f, DURANITE, PREFIX_ORE);
    public static RegistryObject<Block> VALYRIUM_ORE = registerOre("valyrium_ore", Material.STONE, 35.0f, 2000f, VALYRIUM, PREFIX_ORE);
    public static RegistryObject<Block> VIBRANIUM_ORE = registerOre("vibranium_ore", Material.STONE, 40.0f, 3000f, VIBRANIUM, PREFIX_ORE);
    public static RegistryObject<Block> KARMESINE_ORE = registerOre("karmesine_ore", Material.STONE, 10.0f, 10f, DIAMOND, PREFIX_ORE);
    public static RegistryObject<Block> OVIUM_ORE = registerOre("ovium_ore", Material.STONE, 10.0f, 10f, DIAMOND, PREFIX_ORE);
    public static RegistryObject<Block> JAUXUM_ORE = registerOre("jauxum_ore", Material.STONE, 10.0f, 10f, DIAMOND, PREFIX_ORE);
    public static RegistryObject<Block> PALLADIUM_ORE = registerOre("palladium_ore", Material.STONE, 25.0f, 150f, DURANITE, 4 /*0.4f*/, PREFIX_ORE);
    public static RegistryObject<Block> URU_ORE = registerOre("uru_ore", Material.STONE, 35.0f, 500f, VALYRIUM, PREFIX_ORE);
    public static RegistryObject<Block> OSRAM_ORE = registerOre("osram_ore", Material.STONE, 15.0f, 35.0f, DIAMOND, PREFIX_ORE);
    public static RegistryObject<Block> EEZO_ORE = registerOre("eezo_ore", Material.STONE, 50.0f, 50000.0f, DIAMOND, PREFIX_ORE);
    public static RegistryObject<Block> ABYSSUM_ORE = registerOre("abyssum_ore", Material.STONE, 15.0f, 35.0f, DIAMOND, PREFIX_ORE);

    // Blocks
    public static RegistryObject<Block> TIBERIUM_BLOCK = registerBlock("tiberium_block", Material.GLASS, 10.0f, 15f, STONE, 1, PREFIX_BLOCK);
    public static RegistryObject<Block> AURORIUM_BLOCK = registerBlock("aurorium_block", Material.METAL, 15.0f, 15f, DIAMOND, PREFIX_BLOCK);
    public static RegistryObject<Block> PROMETHEUM_BLOCK = registerBlock("prometheum_block", Material.METAL, 20.0f, 15f, DURANITE, 5 /*0.5f*/, PREFIX_BLOCK);
    public static RegistryObject<Block> DURANITE_BLOCK = registerBlock("duranite_block", Material.METAL, 20.0f, 800f, DURANITE, PREFIX_BLOCK);
    public static RegistryObject<Block> VALYRIUM_BLOCK = registerBlock("valyrium_block", Material.METAL, 20.0f, 1500f, VALYRIUM, PREFIX_BLOCK);
    public static RegistryObject<Block> VIBRANIUM_BLOCK = registerBlock("vibranium_block", Material.METAL, 20.0f, 3000f, VIBRANIUM, PREFIX_BLOCK);
    public static RegistryObject<Block> KARMESINE_BLOCK = registerBlock("karmesine_block", Material.METAL, 10.0f, 12f, DIAMOND, PREFIX_BLOCK);
    public static RegistryObject<Block> OVIUM_BLOCK = registerBlock("ovium_block", Material.METAL, 10.0f, 12f, DIAMOND, PREFIX_BLOCK);
    public static RegistryObject<Block> JAUXUM_BLOCK = registerBlock("jauxum_block", Material.METAL, 10.0f, 12f, DIAMOND, PREFIX_BLOCK);
    public static RegistryObject<Block> PALLADIUM_BLOCK = registerBlock("palladium_block", Material.METAL, 25.0f, 150f, DURANITE, 5 /*0.5f*/, PREFIX_BLOCK);
    public static RegistryObject<Block> URU_BLOCK = registerBlock("uru_block", Material.METAL, 30.0f, 500f, VALYRIUM, PREFIX_BLOCK);
    public static RegistryObject<Block> OSRAM_BLOCK = registerBlock("osram_block", Material.METAL, 15.0f, 12f, DIAMOND, PREFIX_BLOCK);
    public static RegistryObject<Block> ABYSSUM_BLOCK = registerBlock("abyssum_block", Material.METAL, 15.0f, 35f, DIAMOND, PREFIX_BLOCK);
    public static RegistryObject<Block> EEZO_BLOCK = registerBlock("eezo_block", Material.METAL, 20.0f, 1000f, DIAMOND, PREFIX_BLOCK);
    public static RegistryObject<Block> TERRAX_BLOCK = registerBlock("terrax_block", Material.METAL, 10.0f, 15f, DIAMOND, PREFIX_BLOCK);
    public static RegistryObject<Block> TRIBERIUM_BLOCK = registerBlock("triberium_block", Material.METAL, 15.0f, 15f, NETHERITE, 1, PREFIX_BLOCK);
    public static RegistryObject<Block> FRACTUM_BLOCK = registerBlock("fractum_block", Material.METAL, 15.0f, 25f, DIAMOND, PREFIX_BLOCK);
    public static RegistryObject<Block> VIOLIUM_BLOCK = registerBlock("violium_block", Material.METAL, 15.0f, 25f, DIAMOND, PREFIX_BLOCK);
    public static RegistryObject<Block> PROXII_BLOCK = registerBlock("proxii_block", Material.METAL, 15.0f, 25f, DURANITE, PREFIX_BLOCK);
    public static RegistryObject<Block> TRITONITE_BLOCK = registerBlock("tritonite_block", Material.METAL, 15.0f, 25f, DIAMOND, PREFIX_BLOCK);
    public static RegistryObject<Block> IGNITZ_BLOCK = registerBlock("ignitz_block", Material.METAL, 20.0f, 20f, DIAMOND, PREFIX_BLOCK);
    public static RegistryObject<Block> IMPEROMITE_BLOCK = registerBlock("imperomite_block", Material.METAL, 20.0f, 25f, DURANITE, PREFIX_BLOCK);
    public static RegistryObject<Block> SOLARIUM_BLOCK = registerBlock("solarium_block", Material.METAL, 25.0f, 25f, VIBRANIUM, PREFIX_BLOCK);
    public static RegistryObject<Block> NIHILITE_BLOCK = registerBlock("nihilite_block", Material.METAL, 10.0f, 25f, VALYRIUM, PREFIX_BLOCK);
    public static RegistryObject<Block> ADAMANT_BLOCK = registerBlock("adamant_block", Material.METAL, 25.0f, 25f, VIBRANIUM, PREFIX_BLOCK);
    public static RegistryObject<Block> DYONITE_BLOCK = registerBlock("dyonite_block", Material.METAL, 10.0f, 25f, DURANITE, PREFIX_BLOCK);
    public static RegistryObject<Block> NUCLEUM_BLOCK = registerBlock("nucleum_block", Material.METAL, 10.0f, 25f, VALYRIUM, PREFIX_BLOCK);
    public static RegistryObject<Block> LUMIX_BLOCK = registerBlock("lumix_block", Material.METAL, 15.0f, 25f, DIAMOND, PREFIX_BLOCK);
    public static RegistryObject<Block> SEISMUM_BLOCK = registerBlock("seismum_block", Material.METAL, 15.0f, 25f, DIAMOND, PREFIX_BLOCK);
    public static RegistryObject<Block> ASTRIUM_BLOCK = registerBlock("astrium_block", Material.METAL, 15.0f, 25f, DIAMOND, PREFIX_BLOCK);
    public static RegistryObject<Block> NIOB_BLOCK = registerBlock("niob_block", Material.METAL, 15.0f, 25f, DURANITE, PREFIX_BLOCK);
    public static RegistryObject<Block> YRDEEN_BLOCK = registerBlock("yrdeen_block", Material.METAL, 15.0f, 25f, VALYRIUM, PREFIX_BLOCK);
    public static RegistryObject<Block> IOX_BLOCK = registerBlock("iox_block", Material.METAL, 20.0f, 25f, DURANITE, PREFIX_BLOCK);

    // More Stuff
    public static RegistryObject<Block> METEORITE_COBBLE_BLOCK = registerCustomBlock(() -> new CobbleBlock(Material.STONE, 20f, 10f, DIAMOND, 1 /*0.075f*/, PREFIX_BLOCK), "meteorite_cobble_block");
    public static RegistryObject<Block> OBSIDIORITE_COBBLE_BLOCK = registerCustomBlock(() -> new CobbleBlock(Material.STONE, 25f, 20f, DURANITE, 1 /*0.035f*/, PREFIX_BLOCK), "obsidiorite_cobble_block");
    public static RegistryObject<Block> METEORITE_BLOCK = registerCustomBlock(() -> new MeteoriteRockBlock(Material.STONE, 40f, 2000f, DIAMOND, 1 /*0.15f*/, PREFIX_BLOCK, METEORITE_COBBLE_BLOCK), "meteorite_block");
    public static RegistryObject<Block> OBSIDIORITE_BLOCK = registerCustomBlock(() -> new MeteoriteRockBlock(Material.STONE, 50f, 4000f, DURANITE, 2 /*0.2f*/, PREFIX_BLOCK, OBSIDIORITE_COBBLE_BLOCK), "obsidiorite_block");

    // Community
    //public static RegistryObject<Block> DILITHIUM_ORE = registerOre("dilithium_ore", Material.STONE, 18f, 18f, DIAMOND, 1 /*0.73f*/, PREFIX_ORE);
    public static RegistryObject<Block> DILITHIUM_ORE = registerCustomBlock(() -> new BlockDilithium(), "dilithium_ore");
    public static RegistryObject<Block> DILITHIUM_BLOCK = registerBlock("dilithium_block", Material.STONE, 18f, 18f, DIAMOND, 1 /*0.73f*/, PREFIX_BLOCK);

	public static RegistryObject<Block> registerOre(String name, Material material, float hardness, float resistance, int miningLevel, @Nullable String prefix) {
		return registerOre(name, material, hardness, resistance, miningLevel, 0, prefix);
	}

	// Just to make readability easier for me
	public static RegistryObject<Block> registerOre(String name, Material material, float hardness, float resistance, int miningLevel, int lightLevel, @Nullable String prefix) {
		return registerBlock(name, material, hardness, resistance, miningLevel, lightLevel, prefix);
	}

	public static RegistryObject<Block> registerBlock(String name, Material material, float hardness, float resistance, int miningLevel, @Nullable String prefix) {
		return registerBlock(name, material, hardness, resistance, miningLevel, 0, prefix);
	}

	public static RegistryObject<Block> registerBlock(String name, Material material, float hardness, float resistance, int miningLevel, int lightLevel, @Nullable String prefix) {
		final RegistryObject<Block> BLOCK = BLOCKS.register(name, () -> new BasicBlock(Block.Properties.of(material)
				.requiresCorrectToolForDrops().strength(hardness, resistance).sound(SoundType.STONE).harvestLevel(miningLevel).lightLevel((x) -> {
				      return lightLevel;
				   }), prefix));

		ITEMS.register(name, () -> new BlockItem(BLOCK.get(), new Item.Properties()
			.tab(TAIGA.BLOCK).stacksTo(64)));

		return BLOCK;
	}

	public static RegistryObject<Block> registerCustomBlock(Supplier<? extends BasicBlock> sup, String name) {
		final RegistryObject<Block> BLOCK = BLOCKS.register(name, sup);

		ITEMS.register(name, () -> new BlockItem(BLOCK.get(), new Item.Properties()
			.tab(TAIGA.BLOCK).stacksTo(64)));

		return BLOCK;
	}
}
