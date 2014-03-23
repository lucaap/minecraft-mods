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

public class BlockStep extends BlockHalfSlab
{
    /** The list of the types of step blocks. */
    public static final String[] blockStepTypes = new String[] {"stone", "sand", "wood", "cobble", "brick", "smoothStoneBrick", "netherBrick", "quartz"};
    @SideOnly(Side.CLIENT)
    private Icon field_94433_b;

    public BlockStep(int par1, boolean par2)
    {
        super(par1, par2, Material.rock);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @SideOnly(Side.CLIENT)
    public Icon func_71858_a(int par1, int par2)
    {
        int var3 = par2 & 7;

        if (this.isDoubleSlab && (par2 & 8) != 0)
        {
            par1 = 1;
        }

        return var3 == 0 ? (par1 != 1 && par1 != 0 ? this.field_94433_b : this.field_94336_cN) : (var3 == 1 ? Block.sandStone.func_71851_a(par1) : (var3 == 2 ? Block.planks.func_71851_a(par1) : (var3 == 3 ? Block.cobblestone.func_71851_a(par1) : (var3 == 4 ? Block.brick.func_71851_a(par1) : (var3 == 5 ? Block.stoneBrick.func_71858_a(par1, 0) : (var3 == 6 ? Block.netherBrick.func_71851_a(1) : (var3 == 7 ? Block.blockNetherQuartz.func_71851_a(par1) : this.field_94336_cN)))))));
    }

    @SideOnly(Side.CLIENT)
    public void func_94332_a(IconRegister par1IconRegister)
    {
        this.field_94336_cN = par1IconRegister.func_94245_a("stone_slab_top");
        this.field_94433_b = par1IconRegister.func_94245_a("stone_slab_side");
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return Block.stoneSingleSlab.blockID;
    }

    /**
     * Returns an item stack containing a single instance of the current block type. 'i' is the block's subtype/damage
     * and is ignored for blocks which do not support subtypes. Blocks which cannot be harvested should return null.
     */
    protected ItemStack createStackedBlock(int par1)
    {
        return new ItemStack(Block.stoneSingleSlab.blockID, 2, par1 & 7);
    }

    /**
     * Returns the slab block name with step type.
     */
    public String getFullSlabName(int par1)
    {
        if (par1 < 0 || par1 >= blockStepTypes.length)
        {
            par1 = 0;
        }

        return super.getUnlocalizedName() + "." + blockStepTypes[par1];
    }

    @SideOnly(Side.CLIENT)
    public void func_71879_a(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        if (par1 != Block.stoneDoubleSlab.blockID)
        {
            for (int var4 = 0; var4 <= 7; ++var4)
            {
                if (var4 != 2)
                {
                    par3List.add(new ItemStack(par1, 1, var4));
                }
            }
        }
    }
}
