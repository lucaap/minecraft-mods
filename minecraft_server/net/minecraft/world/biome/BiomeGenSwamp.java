package net.minecraft.world.biome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenSwamp extends BiomeGenBase
{
    protected BiomeGenSwamp(int par1)
    {
        super(par1);
        this.theBiomeDecorator.treesPerChunk = 2;
        this.theBiomeDecorator.flowersPerChunk = -999;
        this.theBiomeDecorator.deadBushPerChunk = 1;
        this.theBiomeDecorator.mushroomsPerChunk = 8;
        this.theBiomeDecorator.reedsPerChunk = 10;
        this.theBiomeDecorator.clayPerChunk = 1;
        this.theBiomeDecorator.waterlilyPerChunk = 4;
        this.waterColorMultiplier = 14745518;
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 1, 1, 1));
    }

    /**
     * Gets a WorldGen appropriate for this biome.
     */
    public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
    {
        return this.worldGeneratorSwamp;
    }

    @SideOnly(Side.CLIENT)
    public int func_76737_k()
    {
        double var1 = (double)this.getFloatTemperature();
        double var3 = (double)this.func_76727_i();
        return ((ColorizerGrass.func_77480_a(var1, var3) & 16711422) + 5115470) / 2;
    }

    @SideOnly(Side.CLIENT)
    public int func_76726_l()
    {
        double var1 = (double)this.getFloatTemperature();
        double var3 = (double)this.func_76727_i();
        return ((ColorizerFoliage.func_77470_a(var1, var3) & 16711422) + 5115470) / 2;
    }
}
