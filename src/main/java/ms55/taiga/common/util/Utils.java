package ms55.taiga.common.util;

import java.util.Random;

import ms55.taiga.TAIGA;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;

public class Utils {
	public static final Random random = new Random();

	public static String PREFIX_INGOT = "ingot";
    public static String PREFIX_NUGGET = "nugget";
    public static String PREFIX_ORE = "ore";
    public static String PREFIX_BLOCK = "block";
    public static String PREFIX_DUST = "dust";
    public static String PREFIX_CRYSTAL = "crystal";

    public static final int DURANITE  = 5; //6
    public static final int VALYRIUM  = 6; //7
    public static final int VIBRANIUM = 7; //8

    public static String makeNameUpperCase(String name) {
    	if (name.contains("_")) {
    		String firstbit  = (name.substring(0, 1).toUpperCase() + name.substring(1, name.indexOf("_"))).replaceFirst("_", " ");
    		String secondbit = name.substring(name.indexOf("_") + 1, name.indexOf("_") + 2).toUpperCase() + name.substring(name.indexOf("_") + 2).toLowerCase();

    		if (secondbit.contains("_")) {
    			secondbit = makeNameUpperCase(secondbit);
    		}

    		return firstbit + " " +secondbit;
    	} else {
        	return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    	}
    }

//    public static boolean hasTrait(ToolStack stack, Modifier modifier) {
//    	System.out.println(modifier + " : level " + stack.getModifierLevel(modifier) + " : " + (stack.getModifierLevel(modifier) > 0));
//		return stack.getModifierLevel(modifier) > 0;
//    }

//    public static List<ItemStack> generateLoot(LivingEntity entity, DamageSource damageSourceIn, boolean attackedRecently) {
//        ResourceLocation resourcelocation = entity.getLootTableResourceLocation();
//        LootTable loottable = entity.level.getServer().getLootTables().get(resourcelocation);
//        LootContext.Builder lootcontext$builder = getLootContextBuilder(entity, attackedRecently, damageSourceIn);
//        LootContext ctx = lootcontext$builder.build(LootParameterSets.ENTITY);
//        return loottable.generate(ctx);
//    }
//
//    public static LootContext.Builder getLootContextBuilder(LivingEntity entity, boolean attackedRecently, DamageSource damageSourceIn) {
//        LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerWorld) entity.level)).withRandom(random).withParameter(LootParameters.THIS_ENTITY, entity).withParameter(LootParameters.ORIGIN, entity.position()).withParameter(LootParameters.DAMAGE_SOURCE, damageSourceIn).withOptionalParameter(LootParameters.KILLER_ENTITY, damageSourceIn.getEntity()).withOptionalParameter(LootParameters.DIRECT_KILLER_ENTITY, damageSourceIn.getDirectEntity());
//        if (attackedRecently && entity.getCombatTracker().getKiller() != null) {
//           lootcontext$builder = lootcontext$builder.withParameter(LootParameters.LAST_DAMAGE_PLAYER, (PlayerEntity) entity.getAttackingEntity()).withLuck(((PlayerEntity)entity.getLastHurtByMob()).getLuck());
//        }
//
//        return lootcontext$builder;
//     }

    public static boolean isNight(int time) {
        return time > 12500;
    }

    public static double round2(double d) {
        return (Math.round(d * 100.0) / 100.0);
    }

    public static int nextInt(Random random, int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    public static class GeneralNBTData {
        public int killcount;
        public float health;
        public float amount;
        public int brokenblocks;
        public float bonus;
        public int curse;
        public String name;
        public int id;
        public float radius;
        public float dfloat;
        public int dint;
        public boolean active;

        public static GeneralNBTData read(CompoundNBT tag) {
            GeneralNBTData data = new GeneralNBTData();
            data.killcount = tag.getInt("killcount");
            data.brokenblocks = tag.getInt("brokenblocks");
            data.health = tag.getFloat("health");
            data.amount = tag.getFloat("amount");
            data.bonus = tag.getFloat("bonus");
            data.curse = tag.getInt("curse");
            data.name = tag.getString("name");
            data.id = tag.getInt("id");
            data.radius = tag.getFloat("radius");
            data.dfloat = tag.getFloat("dfloat");
            data.dint = tag.getInt("dint");
            data.active = tag.getBoolean("active");
            return data;
        }

		public static GeneralNBTData read(ModDataNBT tag) {
			GeneralNBTData data = new GeneralNBTData();
            data.killcount = tag.getInt(new ResourceLocation(TAIGA.MODID, "killcount"));
            data.brokenblocks = tag.getInt(new ResourceLocation(TAIGA.MODID, "brokenblocks"));
            data.health = tag.getFloat(new ResourceLocation(TAIGA.MODID, "health"));
            data.amount = tag.getFloat(new ResourceLocation(TAIGA.MODID, "amount"));
            data.bonus = tag.getFloat(new ResourceLocation(TAIGA.MODID, "bonus"));
            data.curse = tag.getInt(new ResourceLocation(TAIGA.MODID, "curse"));
            data.name = tag.getString(new ResourceLocation(TAIGA.MODID, "name"));
            data.id = tag.getInt(new ResourceLocation(TAIGA.MODID, "id"));
            data.radius = tag.getFloat(new ResourceLocation(TAIGA.MODID, "radius"));
            data.dfloat = tag.getFloat(new ResourceLocation(TAIGA.MODID, "dfloat"));
            data.dint = tag.getInt(new ResourceLocation(TAIGA.MODID, "dint"));
            data.active = tag.getBoolean(new ResourceLocation(TAIGA.MODID, "active"));
            return data;
		}

        public void write(CompoundNBT tag) {
            tag.putInt("killcount", killcount);
            tag.putInt("brokenblocks", brokenblocks);
            tag.putFloat("health", health);
            tag.putFloat("amount", amount);
            tag.putFloat("bonus", bonus);
            tag.putInt("curse", curse);
            tag.putString("name", name);
            tag.putInt("id", id);
            tag.putFloat("radius", radius);
            tag.putInt("dint", dint);
            tag.putFloat("dfloat", dfloat);
            tag.putBoolean("active", active);
        }

        public void write(ModDataNBT tag) {
            tag.putInt(new ResourceLocation(TAIGA.MODID, "killcount"), killcount);
            tag.putInt(new ResourceLocation(TAIGA.MODID, "brokenblocks"), brokenblocks);
            tag.putFloat(new ResourceLocation(TAIGA.MODID, "health"), health);
            tag.putFloat(new ResourceLocation(TAIGA.MODID, "amount"), amount);
            tag.putFloat(new ResourceLocation(TAIGA.MODID, "bonus"), bonus);
            tag.putInt(new ResourceLocation(TAIGA.MODID, "curse"), curse);
            tag.putString(new ResourceLocation(TAIGA.MODID, "name"), name);
            tag.putInt(new ResourceLocation(TAIGA.MODID, "id"), id);
            tag.putFloat(new ResourceLocation(TAIGA.MODID, "radius"), radius);
            tag.putInt(new ResourceLocation(TAIGA.MODID, "dint"), dint);
            tag.putFloat(new ResourceLocation(TAIGA.MODID, "dfloat"), dfloat);
            tag.putBoolean(new ResourceLocation(TAIGA.MODID, "active"), active);
        }
    }
}