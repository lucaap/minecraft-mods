package net.minecraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockLog extends BlockRotatedPillar
{
    /** The type of tree this log came from. */
    public static final String[] woodType = new String[] {"oak", "spruce", "birch", "jungle"};
    @SideOnly(Side.CLIENT)
    private Icon[] field_111052_c;
    @SideOnly(Side.CLIENT)
    private Icon[] field_94388_cO;

    protected BlockLog(int par1)
    {
        super(par1, Material.wood);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return 1;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return Block.wood.blockID;
    }

    /**
     * Called on server worlds only when the block has been replaced by a different block ID, or the same block with a
     * different metadata value, but before the new metadata value is set. Args: World, x, y, z, old block ID, old
     * metadata
     */
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        byte var7 = 4;
        int var8 = var7 + 1;

        if (par1World.checkChunksExist(par2 - var8, par3 - var8, par4 - var8, par2 + var8, par3 + var8, par4 + var8))
        {
            for (int var9 = -var7; var9 <= var7; ++var9)
            {
                for (int var10 = -var7; var10 <= var7; ++var10)
                {
                    for (int var11 = -var7; var11 <= var7; ++var11)
                    {
                        int var12 = par1World.getBlockId(par2 + var9, par3 + var10, par4 + var11);

                        if (var12 == Block.leaves.blockID)
                        {
                            int var13 = par1World.getBlockMetadata(par2 + var9, par3 + var10, par4 + var11);

                            if ((var13 & 8) == 0)
                            {
                                par1World.setBlockMetadataWithNotify(par2 + var9, par3 + var10, par4 + var11, var13 | 8, 4);
                            }
                        }
                    }
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    protected Icon func_111048_c(int par1)
    {
        return this.field_111052_c[par1];
    }

    @SideOnly(Side.CLIENT)
    protected Icon func_111049_d(int par1)
    {
        return this.field_94388_cO[par1];
    }

    /**
     * returns a number between 0 and 3
     */
    public static int limitToValidMetadata(int par0)
    {
        return par0 & 3;
    }

    @SideOnly(Side.CLIENT)
    public void func_71879_a(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
    }

    @SideOnly(Side.CLIENT)
    public void func_94332_a(IconRegister par1IconRegister)
    {
        this.field_111052_c = new Icon[woodType.length];
        this.field_94388_cO = new Icon[woodType.length];

        for (int var2 = 0; var2 < this.field_111052_c.length; ++var2)
        {
            this.field_111052_c[var2] = par1IconRegister.func_94245_a(this.func_111023_E() + "_" + woodType[var2]);
            this.field_94388_cO[var2] = par1IconRegister.func_94245_a(this.func_111023_E() + "_" + woodType[var2] + "_top");
        }
    }
}
