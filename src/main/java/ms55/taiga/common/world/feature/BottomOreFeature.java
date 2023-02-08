package ms55.taiga.common.world.feature;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class BottomOreFeature extends Feature<OreFeatureConfig> {
   public BottomOreFeature(Codec<OreFeatureConfig> codec) {
      super(codec);
   }

   @Override
   public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, OreFeatureConfig config) {	   
	   //big brain
	   if (Blocks.AIR.defaultBlockState().equals(reader.getBlockState(pos))) {
		   //29 30 31 contain air 32
		   while (reader.getBlockState(pos).equals(Blocks.AIR.defaultBlockState()) && pos.getY() < 64) {
        	   pos = pos.above();
           }
		   //32 is endstone, so replace it with the ore
		   if (config.target.test(reader.getBlockState(pos), rand)) {
        	   return reader.setBlock(pos, config.state, 2);
           }
	   } else if (Blocks.END_STONE.defaultBlockState().equals(reader.getBlockState(pos))) {
		   //32 31 30 contain end stone 29
		   while (reader.getBlockState(pos).equals(Blocks.END_STONE.defaultBlockState()) && pos.getY() > 0) {
        	   pos = pos.below();
           }
		   //29 is air, and 30 is end stone, so replace endstone with the ore
		   if (reader.getBlockState(pos).equals(Blocks.AIR.defaultBlockState()) && config.target.test(reader.getBlockState(pos.above()), rand)) {
        	   return reader.setBlock(pos.above(), config.state, 2);
           }
	   }

	   return false;
   }
}