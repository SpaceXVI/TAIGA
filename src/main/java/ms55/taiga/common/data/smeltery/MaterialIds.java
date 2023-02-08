package ms55.taiga.common.data.smeltery;

import ms55.taiga.TAIGA;
import slimeknights.tconstruct.library.materials.definition.MaterialId;

public final class MaterialIds {

  	public static final MaterialId BASALT = id("basalt");
    public static final MaterialId METEORITE = id("meteorite");
    public static final MaterialId OBSIDIORITE = id("obsidiorite");
    public static final MaterialId DILITHIUM = id("dilithium");
  	public static final MaterialId TIBERIUM = id("tiberium");
    public static final MaterialId AURORIUM = id("aurorium");
    public static final MaterialId PROMETHEUM = id("prometheum");
    public static final MaterialId DURANITE = id("duranite");
    public static final MaterialId VALYRIUM = id("valyrium");
    public static final MaterialId VIBRANIUM = id("vibranium");
    public static final MaterialId KARMESINE = id("karmesine");
    public static final MaterialId OVIUM = id("ovium");
    public static final MaterialId JAUXUM = id("jauxum");
    public static final MaterialId PALLADIUM = id("palladium");
    public static final MaterialId URU = id("uru");
    public static final MaterialId OSRAM = id("osram");
    public static final MaterialId ABYSSUM = id("abyssum");
    public static final MaterialId EEZO = id("eezo");
    public static final MaterialId TERRAX = id("terrax");
    public static final MaterialId TRIBERIUM = id("triberium");
    public static final MaterialId FRACTUM = id("fractum");
    public static final MaterialId VIOLIUM = id("violium");
    public static final MaterialId PROXII = id("proxii");
    public static final MaterialId TRITONITE = id("tritonite");
    public static final MaterialId IGNITZ = id("ignitz");
    public static final MaterialId IMPEROMITE = id("imperomite");
    public static final MaterialId SOLARIUM = id("solarium");
    public static final MaterialId NIHILITE = id("nihilite");
    public static final MaterialId ADAMANT = id("adamant");
    public static final MaterialId DYONITE = id("dyonite");
    public static final MaterialId NUCLEUM = id("nucleum");
    public static final MaterialId LUMIX = id("lumix");
    public static final MaterialId SEISMUM = id("seismum");
    public static final MaterialId ASTRIUM = id("astrium");
    public static final MaterialId NIOB = id("niob");
    public static final MaterialId YRDEEN = id("yrdeen");
    public static final MaterialId IOX = id("iox");
  	//public static final MaterialId MAGMA = id("magma");
  	public static final MaterialId NITRONITE = id("nitronite");

  	/**
  	 * Creates a new material ID
  	 * @param name  ID name
  	 * @return  Material ID object
  	 */
    private static MaterialId id(String name) {
        return new MaterialId(TAIGA.MODID, name);
    }
}