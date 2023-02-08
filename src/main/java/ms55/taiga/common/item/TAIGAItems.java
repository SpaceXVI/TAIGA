package ms55.taiga.common.item;

import static ms55.taiga.common.util.Utils.PREFIX_CRYSTAL;
import static ms55.taiga.common.util.Utils.PREFIX_DUST;
import static ms55.taiga.common.util.Utils.PREFIX_INGOT;
import static ms55.taiga.common.util.Utils.PREFIX_NUGGET;

import java.util.function.Supplier;

import ms55.taiga.TAIGA;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TAIGAItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TAIGA.MODID);

	public static RegistryObject<BasicItem> TIBERIUM_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "tiberium_ingot");
    public static RegistryObject<BasicItem> TIBERIUM_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "tiberium_dust");
    public static RegistryObject<BasicItem> TIBERIUM_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "tiberium_nugget");

    public static RegistryObject<BasicItem> AURORIUM_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "aurorium_ingot");
    public static RegistryObject<BasicItem> AURORIUM_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "aurorium_dust");
    public static RegistryObject<BasicItem> AURORIUM_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "aurorium_nugget");

    public static RegistryObject<BasicItem> PROMETHEUM_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "prometheum_ingot");
    public static RegistryObject<BasicItem> PROMETHEUM_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "prometheum_dust");
    public static RegistryObject<BasicItem> PROMETHEUM_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "prometheum_nugget");

    public static RegistryObject<BasicItem> DURANITE_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "duranite_ingot");
    public static RegistryObject<BasicItem> DURANITE_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "duranite_dust");
    public static RegistryObject<BasicItem> DURANITE_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "duranite_nugget");

    public static RegistryObject<BasicItem> VALYRIUM_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "valyrium_ingot");
    public static RegistryObject<BasicItem> VALYRIUM_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "valyrium_dust");
    public static RegistryObject<BasicItem> VALYRIUM_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "valyrium_nugget");

    public static RegistryObject<BasicItem> VIBRANIUM_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "vibranium_ingot");
    public static RegistryObject<BasicItem> VIBRANIUM_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "vibranium_dust");
    public static RegistryObject<BasicItem> VIBRANIUM_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "vibranium_nugget");

    public static RegistryObject<BasicItem> KARMESINE_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "karmesine_ingot");
    public static RegistryObject<BasicItem> KARMESINE_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "karmesine_dust");
    public static RegistryObject<BasicItem> KARMESINE_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "karmesine_nugget");

    public static RegistryObject<BasicItem> OVIUM_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "ovium_ingot");
    public static RegistryObject<BasicItem> OVIUM_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "ovium_dust");
    public static RegistryObject<BasicItem> OVIUM_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "ovium_nugget");

    public static RegistryObject<BasicItem> JAUXUM_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "jauxum_ingot");
    public static RegistryObject<BasicItem> JAUXUM_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "jauxum_dust");
    public static RegistryObject<BasicItem> JAUXUM_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "jauxum_nugget");

    public static RegistryObject<BasicItem> TERRAX_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "terrax_ingot");
    public static RegistryObject<BasicItem> TERRAX_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "terrax_dust");
    public static RegistryObject<BasicItem> TERRAX_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "terrax_nugget");

    public static RegistryObject<BasicItem> PALLADIUM_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "palladium_ingot");
    public static RegistryObject<BasicItem> PALLADIUM_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "palladium_dust");
    public static RegistryObject<BasicItem> PALLADIUM_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "palladium_nugget");

    public static RegistryObject<BasicItem> URU_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "uru_ingot");
    public static RegistryObject<BasicItem> URU_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "uru_dust");
    public static RegistryObject<BasicItem> URU_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "uru_nugget");

    public static RegistryObject<BasicItem> OSRAM_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "osram_ingot");
    public static RegistryObject<BasicItem> OSRAM_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "osram_dust");
    public static RegistryObject<BasicItem> OSRAM_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "osram_nugget");

    public static RegistryObject<BasicItem> ABYSSUM_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "abyssum_ingot");
    public static RegistryObject<BasicItem> ABYSSUM_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "abyssum_dust");
    public static RegistryObject<BasicItem> ABYSSUM_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "abyssum_nugget");

    public static RegistryObject<BasicItem> EEZO_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "eezo_ingot");
    public static RegistryObject<BasicItem> EEZO_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "eezo_dust");
    public static RegistryObject<BasicItem> EEZO_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "eezo_nugget");

    public static RegistryObject<BasicItem> TRIBERIUM_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "triberium_ingot");
    public static RegistryObject<BasicItem> TRIBERIUM_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "triberium_dust");
    public static RegistryObject<BasicItem> TRIBERIUM_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "triberium_nugget");

    public static RegistryObject<BasicItem> FRACTUM_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "fractum_ingot");
    public static RegistryObject<BasicItem> FRACTUM_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "fractum_dust");
    public static RegistryObject<BasicItem> FRACTUM_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "fractum_nugget");

    public static RegistryObject<BasicItem> VIOLIUM_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "violium_ingot");
    public static RegistryObject<BasicItem> VIOLIUM_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "violium_dust");
    public static RegistryObject<BasicItem> VIOLIUM_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "violium_nugget");

    public static RegistryObject<BasicItem> PROXII_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "proxii_ingot");
    public static RegistryObject<BasicItem> PROXII_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "proxii_dust");
    public static RegistryObject<BasicItem> PROXII_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "proxii_nugget");

    public static RegistryObject<BasicItem> TRITONITE_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "tritonite_ingot");
    public static RegistryObject<BasicItem> TRITONITE_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "tritonite_dust");
    public static RegistryObject<BasicItem> TRITONITE_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "tritonite_nugget");

    public static RegistryObject<BasicItem> IGNITZ_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "ignitz_ingot");
    public static RegistryObject<BasicItem> IGNITZ_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "ignitz_dust");
    public static RegistryObject<BasicItem> IGNITZ_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "ignitz_nugget");

    public static RegistryObject<BasicItem> IMPEROMITE_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "imperomite_ingot");
    public static RegistryObject<BasicItem> IMPEROMITE_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "imperomite_dust");
    public static RegistryObject<BasicItem> IMPEROMITE_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "imperomite_nugget");

    public static RegistryObject<BasicItem> SOLARIUM_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "solarium_ingot");
    public static RegistryObject<BasicItem> SOLARIUM_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "solarium_dust");
    public static RegistryObject<BasicItem> SOLARIUM_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "solarium_nugget");

    public static RegistryObject<BasicItem> NIHILITE_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "nihilite_ingot");
    public static RegistryObject<BasicItem> NIHILITE_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "nihilite_dust");
    public static RegistryObject<BasicItem> NIHILITE_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "nihilite_nugget");

    public static RegistryObject<BasicItem> ADAMANT_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "adamant_ingot");
    public static RegistryObject<BasicItem> ADAMANT_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "adamant_dust");
    public static RegistryObject<BasicItem> ADAMANT_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "adamant_nugget");

    public static RegistryObject<BasicItem> DYONITE_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "dyonite_ingot");
    public static RegistryObject<BasicItem> DYONITE_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "dyonite_dust");
    public static RegistryObject<BasicItem> DYONITE_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "dyonite_nugget");

    public static RegistryObject<BasicItem> NUCLEUM_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "nucleum_ingot");
    public static RegistryObject<BasicItem> NUCLEUM_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "nucleum_dust");
    public static RegistryObject<BasicItem> NUCLEUM_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "nucleum_nugget");

    public static RegistryObject<BasicItem> LUMIX_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "lumix_ingot");
    public static RegistryObject<BasicItem> LUMIX_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "lumix_dust");
    public static RegistryObject<BasicItem> LUMIX_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "lumix_nugget");

    public static RegistryObject<BasicItem> SEISMUM_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "seismum_ingot");
    public static RegistryObject<BasicItem> SEISMUM_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "seismum_dust");
    public static RegistryObject<BasicItem> SEISMUM_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "seismum_nugget");

    public static RegistryObject<BasicItem> ASTRIUM_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "astrium_ingot");
    public static RegistryObject<BasicItem> ASTRIUM_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "astrium_dust");
    public static RegistryObject<BasicItem> ASTRIUM_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "astrium_nugget");

    public static RegistryObject<BasicItem> NIOB_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "niob_ingot");
    public static RegistryObject<BasicItem> NIOB_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "niob_dust");
    public static RegistryObject<BasicItem> NIOB_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "niob_nugget");

    public static RegistryObject<BasicItem> YRDEEN_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "yrdeen_ingot");
    public static RegistryObject<BasicItem> YRDEEN_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "yrdeen_dust");
    public static RegistryObject<BasicItem> YRDEEN_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "yrdeen_nugget");

    public static RegistryObject<BasicItem> IOX_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "iox_ingot");
    public static RegistryObject<BasicItem> IOX_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "iox_dust");
    public static RegistryObject<BasicItem> IOX_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "iox_nugget");

    public static RegistryObject<BasicItem> METEORITE_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "meteorite_ingot");
    public static RegistryObject<BasicItem> METEORITE_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "meteorite_dust");
    public static RegistryObject<BasicItem> METEORITE_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "meteorite_nugget");

    public static RegistryObject<BasicItem> BASALT_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "basalt_ingot");
    public static RegistryObject<BasicItem> BASALT_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "basalt_dust");
    public static RegistryObject<BasicItem> BASALT_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "basalt_nugget");

    public static RegistryObject<BasicItem> OBSIDIORITE_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "obsidiorite_ingot");
    public static RegistryObject<BasicItem> OBSIDIORITE_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "obsidiorite_dust");
    public static RegistryObject<BasicItem> OBSIDIORITE_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "obsidiorite_nugget");

    public static RegistryObject<BasicItem> DILITHIUM_INGOT  = registerItem(() -> new BasicItem(PREFIX_INGOT), "dilithium_ingot");
    public static RegistryObject<BasicItem> DILITHIUM_DUST   = registerItem(() -> new BasicItem(PREFIX_DUST), "dilithium_dust");
    public static RegistryObject<BasicItem> DILITHIUM_NUGGET = registerItem(() -> new BasicItem(PREFIX_NUGGET), "dilithium_nugget");

    public static RegistryObject<BasicItem> DILITHIUM_CRYSTAL = registerItem(() -> new BasicItem(PREFIX_CRYSTAL), "dilithium_crystal");
    public static RegistryObject<BasicItem> TIBERIUM_CRYSTAL  = registerItem(() -> new BasicItem(PREFIX_CRYSTAL), "tiberium_crystal");

    private static RegistryObject<BasicItem> registerItem(Supplier<? extends BasicItem> sup, String name) {
		return ITEMS.register(name, sup);
	}
}