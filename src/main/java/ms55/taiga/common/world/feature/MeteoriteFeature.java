package ms55.taiga.common.world.feature;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;

import ms55.taiga.common.world.feature.config.MeteoriteFeatureConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.Tags.IOptionalNamedTag;

public class MeteoriteFeature extends Feature<MeteoriteFeatureConfig> {
   public MeteoriteFeature(Codec<MeteoriteFeatureConfig> codec) {
      super(codec);
   }

   @Override
   public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, MeteoriteFeatureConfig config) {
       List<IOptionalNamedTag<?>> tags = Lists.newArrayList(
    		   Tags.Blocks.DIRT, 
    		   Tags.Blocks.STONE, 
    		   Tags.Blocks.SAND, 
    		   Tags.Blocks.GRAVEL, 
    		   Tags.Blocks.COBBLESTONE, 
    		   Tags.Blocks.SANDSTONE);
       //Somehow Grass Blocks don't have a tag

       int mGenerated = 0;
       BlockState centerBlock = config.center;
       BlockState hullBlock = config.hull;

       //other:
       //for (int i = 0; i < config.count; i++) {
           if (rand.nextFloat() < 0.06) {
               int r = rand.nextInt(5) + 1;

               if (reader.getBlockState(pos).equals(Blocks.AIR.defaultBlockState()) && reader.getBlockState(pos.below()).equals(Blocks.AIR.defaultBlockState())) {
                   // we are in mid air, go down
                   while (reader.getBlockState(pos.below()).equals(Blocks.AIR.defaultBlockState())) {
                	   pos = pos.below();

                       if (pos.getY() < 12)
                           break;
                   }
               }

               boolean pass = false;
               for (IOptionalNamedTag<?> tag : tags) {
            	   if (!tag.getValues().contains(reader.getBlockState(pos.below()).getBlock())) {
                       continue;
            	   }
            	   pass = true;
               }

               if (!pass) {
            	   return false;
               }

               pos = pos.below(rand.nextInt(r * 2) + r + 1);

               //MeteorWorldSaveData saveData = MeteorWorldSaveData.getForWorld(reader); //Do we really need this?
               //saveData.addPos(pos);
               //saveData.markDirty();

               mGenerated++;

               int t = 1;
               if (r > 3) t = rand.nextInt(r - 1);
               for (int x = -t; x <= t; x++) {
                   for (int y = -t; y <= t; y++) {
                       for (int z = -t; z <= t; z++) {
                           if (MathHelper.sqrt(x * x + y * y + z * z) > t) {
                               continue;
                           }
                           reader.setBlock(new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z), centerBlock, 2);
                       }
                   }
               }
               for (int x = -r; x <= r; x++) {
                   for (int y = -r; y <= r; y++) {
                       for (int z = -r; z <= r; z++) {
                           if (MathHelper.sqrt(x * x + y * y + z * z) > r) {
                               continue;
                           }
                           BlockPos nPos = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
                           if (reader.getBlockState(nPos).equals(centerBlock))
                               continue;
                           reader.setBlock(nPos, hullBlock, 2);
                       }
                   }
               }
           }
       //}

       return mGenerated > 0;
   }
}