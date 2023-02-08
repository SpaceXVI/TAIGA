package ms55.taiga.common.block;

import static ms55.taiga.TAIGA.RAND;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;

public class MeteoriteRockBlock extends BasicBlock {
    private RegistryObject<Block> cobbblestate;

    public MeteoriteRockBlock(Material material, float hardness, float resistance, int harvestLevel, int light, String oreDictPrefix, RegistryObject<Block> OBSIDIORITE_COBBLE_BLOCK) {
        super(Block.Properties.of(material)
        		.requiresCorrectToolForDrops().strength(hardness, resistance).sound(SoundType.STONE).harvestLevel(harvestLevel).lightLevel((x) -> {return light;}), oreDictPrefix);
        MinecraftForge.EVENT_BUS.register(this);
        this.cobbblestate = OBSIDIORITE_COBBLE_BLOCK;
    }

    //I believe there's another way for this
    @SubscribeEvent
    public void breakMoonRock(BlockEvent.BreakEvent event) {
        if (event.getWorld().getBlockState(event.getPos()).getBlock().equals(this)) {
            if (!event.getWorld().isClientSide() && RAND.nextFloat() > 0.25) {
            	event.setCanceled(true);
            	event.getWorld().setBlock(event.getPos(), cobbblestate.get().defaultBlockState(), 1);
            }
        }
    }
}