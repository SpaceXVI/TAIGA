package ms55.taiga.common.data.smeltery;

import static ms55.taiga.common.util.Utils.DURANITE;
import static ms55.taiga.common.util.Utils.VALYRIUM;
import static ms55.taiga.common.util.Utils.VIBRANIUM;
import static slimeknights.tconstruct.library.utils.HarvestLevels.DIAMOND;
import static slimeknights.tconstruct.library.utils.HarvestLevels.NETHERITE;
import static slimeknights.tconstruct.library.utils.HarvestLevels.STONE;

import net.minecraft.data.DataGenerator;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialStatsDataProvider;
import slimeknights.tconstruct.tools.stats.ExtraMaterialStats;
import slimeknights.tconstruct.tools.stats.HandleMaterialStats;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;

public class MaterialStatsDataProvider extends AbstractMaterialStatsDataProvider {
	public MaterialStatsDataProvider(DataGenerator gen, AbstractMaterialDataProvider materials) {
	    super(gen, materials);
	  }

	  @Override
	  public String getName() {
	    return "TAIGA's Material Stats";
	  }

	  @Override
	  protected void addMaterialStats() {
		  addMaterialStats(MaterialIds.TIBERIUM,
	                new HeadMaterialStats(80, 3.3f, DIAMOND, 4f),
	                HandleMaterialStats.DEFAULT.withDurability(0.7f),
	                ExtraMaterialStats.DEFAULT); //50
					//BOW : shitty

			addMaterialStats(MaterialIds.AURORIUM,
	             	new HeadMaterialStats(750, 3.6f, NETHERITE, 3.78f),
	             	HandleMaterialStats.DEFAULT.withDurability(1.2f),
	             	ExtraMaterialStats.DEFAULT); //130
					//BOW : 0.45f, 1f, 1

			addMaterialStats(MaterialIds.PROMETHEUM,
	             	new HeadMaterialStats(844, 4.75f, DURANITE, 6.6f),
	             	HandleMaterialStats.DEFAULT.withDurability(1.2f),
	             	ExtraMaterialStats.DEFAULT); //50
					//BOW : 0.2f, 0.6f, 3

			addMaterialStats(MaterialIds.DURANITE,
	             	new HeadMaterialStats(1550, 3.2f, DURANITE, 3.2f),
	             	HandleMaterialStats.DEFAULT.withDurability(1.16f),
	             	ExtraMaterialStats.DEFAULT); //100
					//BOW : 0.3f, 1.4f, 2

			addMaterialStats(MaterialIds.VALYRIUM,
	             	new HeadMaterialStats(1111, 5.37f, VALYRIUM, 4.8f),
	             	HandleMaterialStats.DEFAULT.withDurability(1.3f),
	             	ExtraMaterialStats.DEFAULT); //100
					//BOW : 1.1f, 1.2f, 4

			addMaterialStats(MaterialIds.VIBRANIUM,
					new HeadMaterialStats(1235, 7.62f, VIBRANIUM, 8.1f),
					HandleMaterialStats.DEFAULT.withDurability(1.3f),
					ExtraMaterialStats.DEFAULT); //100
					//BOW : 1.1f, 1.8f, 4
			
			addMaterialStats(MaterialIds.TERRAX,
					new HeadMaterialStats(444, 4.77f, NETHERITE, 2.9f),
					HandleMaterialStats.DEFAULT.withDurability(0.8f),
					ExtraMaterialStats.DEFAULT); //50
					//BOW : shitty

			addMaterialStats(MaterialIds.PALLADIUM,
					new HeadMaterialStats(797, 4.35f, DURANITE, 6.8f),
					HandleMaterialStats.DEFAULT.withDurability(1.3f),
					ExtraMaterialStats.DEFAULT); //-50
					//BOW : .5f, .2f, 3
			
			addMaterialStats(MaterialIds.URU,
	             	new HeadMaterialStats(877, 2f, VALYRIUM, 7.2f),
	             	HandleMaterialStats.DEFAULT.withDurability(0.5f),
	             	ExtraMaterialStats.DEFAULT); //175
					//BOW : 1.3f, 0.8f, 6

			addMaterialStats(MaterialIds.EEZO,
					new HeadMaterialStats(50, 23f, NETHERITE, 3.5f),
					HandleMaterialStats.DEFAULT.withDurability(0.1f),
					ExtraMaterialStats.DEFAULT); //10
					//BOW : shitty

			addMaterialStats(MaterialIds.BASALT,
					new HeadMaterialStats(200, 3, STONE, 2.5f),
					HandleMaterialStats.DEFAULT.withDurability(0.5f),
					ExtraMaterialStats.DEFAULT); //25
					//BOW : shitty

			addMaterialStats(MaterialIds.TRIBERIUM,
					new HeadMaterialStats(223, 6.2f, DIAMOND, 8.35f),
					HandleMaterialStats.DEFAULT.withDurability(0.63f),
					ExtraMaterialStats.DEFAULT); //50
					//BOW : shity

			addMaterialStats(MaterialIds.FRACTUM,
					new HeadMaterialStats(538, 5.71f, DIAMOND, 6.93f),
					HandleMaterialStats.DEFAULT.withDurability(0.88f),
					ExtraMaterialStats.DEFAULT); //117
					//BOW : shitty

			addMaterialStats(MaterialIds.VIOLIUM,
	             	new HeadMaterialStats(925, 3.8f, NETHERITE, 3.75f),
	             	HandleMaterialStats.DEFAULT.withDurability(0.9f),
	             	ExtraMaterialStats.DEFAULT); //50
					//BOW : .45f, .95f, 1

			addMaterialStats(MaterialIds.PROXII,
					new HeadMaterialStats(625, 6.8f, DURANITE, 4.21f),
					HandleMaterialStats.DEFAULT.withDurability(1.25f),
					ExtraMaterialStats.DEFAULT); //25
					//BOW : .35f, .5f, 3

			addMaterialStats(MaterialIds.TRITONITE,
					new HeadMaterialStats(200, 3, NETHERITE, 2.5f),
					HandleMaterialStats.DEFAULT.withDurability(0.55f),
					ExtraMaterialStats.DEFAULT); //150
					//BOW : shitty

			addMaterialStats(MaterialIds.IGNITZ,
					new HeadMaterialStats(350, 2f, NETHERITE, 6.66f),
					HandleMaterialStats.DEFAULT.withDurability(0.85f),
					ExtraMaterialStats.DEFAULT); //250
					//BOW : .8f, .8f, 3

			addMaterialStats(MaterialIds.IMPEROMITE,
					new HeadMaterialStats(1350, 4.65f, DURANITE, 5.9f),
					HandleMaterialStats.DEFAULT.withDurability(0.85f),
					ExtraMaterialStats.DEFAULT); //150
					//BOW : 1.2f, 1.8f, 2

			addMaterialStats(MaterialIds.SOLARIUM,
	             	new HeadMaterialStats(1100, 24f, VIBRANIUM, 7f),
	             	HandleMaterialStats.DEFAULT.withDurability(1.25f),
	             	ExtraMaterialStats.DEFAULT); //150
					//BOW : .8f, 1.5f, 5

			addMaterialStats(MaterialIds.NIHILITE,
					new HeadMaterialStats(400, 2.8f, VALYRIUM, 4.50f),
					HandleMaterialStats.DEFAULT.withDurability(0.77f),
					ExtraMaterialStats.DEFAULT); //155
					//BOW : 1.5f, .8f, 3

			addMaterialStats(MaterialIds.ADAMANT,
					new HeadMaterialStats(1750, 6f, VIBRANIUM, 6f),
					HandleMaterialStats.DEFAULT.withDurability(2f),
					ExtraMaterialStats.DEFAULT); //0
					//BOW : .35f, 1.85f, 8

			addMaterialStats(MaterialIds.DYONITE,
					new HeadMaterialStats(900, 6.45f, DURANITE, 5f),
					HandleMaterialStats.DEFAULT.withDurability(0.66f),
					ExtraMaterialStats.DEFAULT); //250
					//BOW : 2, .9f, -1

			addMaterialStats(MaterialIds.NUCLEUM,
					new HeadMaterialStats(505, 17.5f, VALYRIUM, 9.5f),
					HandleMaterialStats.DEFAULT.withDurability(1.05f),
					ExtraMaterialStats.DEFAULT); //125
					//BOW : shitty

			addMaterialStats(MaterialIds.LUMIX,
	             	new HeadMaterialStats(666, 3.84f, NETHERITE, 3.92f),
	             	HandleMaterialStats.DEFAULT.withDurability(0.85f),
	             	ExtraMaterialStats.DEFAULT); //200
					//BOW : .8f, 1.3f, 1

			addMaterialStats(MaterialIds.SEISMUM,
					new HeadMaterialStats(780, 3.66f, NETHERITE, 6.05f),
					HandleMaterialStats.DEFAULT.withDurability(0.95f),
					ExtraMaterialStats.DEFAULT); //50
					//BOW : shitty

			addMaterialStats(MaterialIds.ASTRIUM,
					new HeadMaterialStats(750, 8.35f, NETHERITE, 5.4f),
					HandleMaterialStats.DEFAULT.withDurability(0.95f),
					ExtraMaterialStats.DEFAULT); //200
					//BOW : 7f, .8f, 2

			addMaterialStats(MaterialIds.NIOB,
					new HeadMaterialStats(700, 4.5f, NETHERITE, 4.5f),
					HandleMaterialStats.DEFAULT.withDurability(2.0f),
					ExtraMaterialStats.DEFAULT); //50
					//BOW : shitty

			addMaterialStats(MaterialIds.YRDEEN,
					new HeadMaterialStats(999, 9.1f, NETHERITE, 3f),
					HandleMaterialStats.DEFAULT.withDurability(1.35f),
					ExtraMaterialStats.DEFAULT); //250
					//BOW : shitty

			addMaterialStats(MaterialIds.METEORITE,
					new HeadMaterialStats(1500, 1.5f, DIAMOND, 1.5f), //OBSIDIAN
					HandleMaterialStats.DEFAULT.withDurability(0.5f),
					ExtraMaterialStats.DEFAULT); //0
					//BOW : shitty

			addMaterialStats(MaterialIds.OBSIDIORITE,
					new HeadMaterialStats(1500, .5f, NETHERITE, .5f),
					HandleMaterialStats.DEFAULT.withDurability(1.0f),
					ExtraMaterialStats.DEFAULT); //100
					//BOW : shitty

			//all were -
			addMaterialStats(MaterialIds.OSRAM,
					new HeadMaterialStats(100, 1.5f, STONE, 1.5f),
					HandleMaterialStats.DEFAULT.withDurability(1.0f),
					ExtraMaterialStats.DEFAULT); //-1
					//BOW : 0.1f, 0.1f, -1f

			//all were -
			addMaterialStats(MaterialIds.ABYSSUM,
					new HeadMaterialStats(100, 1.5f, DIAMOND, 1.5f),
					HandleMaterialStats.DEFAULT.withDurability(1.0f),
					ExtraMaterialStats.DEFAULT); //-1
					//BOW : 0.1f, 0.1f, -1f

			//all were -
			addMaterialStats(MaterialIds.IOX,
					new HeadMaterialStats(200, 3f, DIAMOND, 3f),
					HandleMaterialStats.DEFAULT.withDurability(1.0f),
					ExtraMaterialStats.DEFAULT); //-1
					//BOW : 0.1f, 0.1f, -1f

			//all were -
			addMaterialStats(MaterialIds.KARMESINE,
					new HeadMaterialStats(50, 2f, DIAMOND, 1.5f),
					HandleMaterialStats.DEFAULT.withDurability(1.0f),
					ExtraMaterialStats.DEFAULT); //-1
					//BOW : 0.1f, 0.1f, -1f

			//all were -
			addMaterialStats(MaterialIds.OVIUM,
					new HeadMaterialStats(50, 2f, DIAMOND, 1.5f),
					HandleMaterialStats.DEFAULT.withDurability(1.0f),
					ExtraMaterialStats.DEFAULT); //-1
					//BOW : 0.1f, 0.1f, -1f

			//all were -
			addMaterialStats(MaterialIds.JAUXUM,
					new HeadMaterialStats(50, 2f, DIAMOND, 1.5f),
					HandleMaterialStats.DEFAULT.withDurability(1.0f),
					ExtraMaterialStats.DEFAULT); //-1
					//BOW : 0.1f, 0.1f, -1f

			//all were -
			addMaterialStats(MaterialIds.DILITHIUM,
					new HeadMaterialStats(50, 2f, DIAMOND, 1.5f),
					HandleMaterialStats.DEFAULT.withDurability(1.0f),
					ExtraMaterialStats.DEFAULT); //-1
					//BOW : 0.1f, 0.1f, -1f
	  }
}