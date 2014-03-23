package net.minecraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGrass extends Block
{
    @SideOnly(Side.CLIENT)
    private Icon field_94437_a;
    @SideOnly(Side.CLIENT)
    private Icon field_94435_b;
    @SideOnly(Side.CLIENT)
    private Icon field_94436_c;

    protected BlockGrass(int par1)
    {
        super(par1, Material.grass);
        this.setTickRandomly(true);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @SideOnly(Side.CLIENT)
    public Icon func_71858_a(int par1, int par2)
    {
        return par1 == 1 ? this.field_94437_a : (par1 == 0 ? Block.dirt.func_71851_a(par1) : this.field_94336_cN);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote)
        {
            if (par1World.getBlockLightValue(par2, par3 + 1, par4) < 4 && Block.lightOpacity[par1World.getBlockId(par2, par3 + 1, par4)] > 2)
            {
                par1World.setBlock(par2, par3, par4, Block.dirt.blockID);
            }
            else if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9)
            {
                for (int var6 = 0; var6 < 4; ++var6)
                {
                    int var7 = par2 + par5Random.nextInt(3) - 1;
                    int var8 = par3 + par5Random.nextInt(5) - 3;
                    int var9 = par4 + par5Random.nextInt(3) - 1;
                    int var10 = par1World.getBlockId(var7, var8 + 1, var9);

                    if (par1World.getBlockId(var7, var8, var9) == Block.dirt.blockID && par1World.getBlockLightValue(var7, var8 + 1, var9) >= 4 && Block.lightOpacity[var10] <= 2)
                    {
                        par1World.setBlock(var7, var8, var9, Block.grass.blockID);
                    }
                }
            }
        }
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return Block.dirt.idDropped(0, par2Random, par3);
    }

    @SideOnly(Side.CLIENT)
    public Icon func_71895_b(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        if (par5 == 1)
        {
            return this.field_94437_a;
        }
        else if (par5 == 0)
        {
            return Block.dirt.func_71851_a(par5);
        }
        else
        {
            Material var6 = par1IBlockAccess.getBlockMaterial(par2, par3 + 1, par4);
            return var6 != Material.snow && var6 != Material.craftedSnow ? this.field_94336_cN : this.field_94435_b;
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_94332_a(IconRegister par1IconRegister)
    {
        this.field_94336_cN = par1IconRegister.func_94245_a(this.func_111023_E() + "_side");
        this.field_94437_a = par1IconRegister.func_94245_a(this.func_111023_E() + "_top");
        this.field_94435_b = par1IconRegister.func_94245_a(this.func_111023_E() + "_side_snowed");
        this.field_94436_c = par1IconRegister.func_94245_a(this.func_111023_E() + "_side_overlay");
    }

    @SideOnly(Side.CLIENT)
    public int func_71933_m()
    {
        double var1 = 0.5D;
        double var3 = 1.0D;
        return ColorizerGrass.func_77480_a(var1, var3);
    }

    @SideOnly(Side.CLIENT)
    public int func_71889_f_(int par1)
    {
        return this.func_71933_m();
    }

    @SideOnly(Side.CLIENT)
    public int func_71920_b(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        int var5 = 0;
        int var6 = 0;
        int var7 = 0;

        for (int var8 = -1; var8 <= 1; ++var8)
        {
            for (int var9 = -1; var9 <= 1; ++var9)
            {
                int var10 = par1IBlockAccess.getBiomeGenForCoords(par2 + var9, par4 + var8).func_76737_k();
                var5 += (var10 & 16711680) >> 16;
                var6 += (var10 & 65280) >> 8;
                var7 += var10 & 255;
            }
        }

        return (var5 / 9 & 255) << 16 | (var6 / 9 & 255) << 8 | var7 / 9 & 255;
    }

    @SideOnly(Side.CLIENT)
    public static Icon func_94434_o()
    {
        return Block.grass.field_94436_c;
    }
}
