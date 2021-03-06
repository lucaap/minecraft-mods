package net.minecraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockNetherStalk extends BlockFlower
{
    @SideOnly(Side.CLIENT)
    private Icon[] field_94372_b;

    protected BlockNetherStalk(int par1)
    {
        super(par1);
        this.setTickRandomly(true);
        float var2 = 0.5F;
        this.setBlockBounds(0.5F - var2, 0.0F, 0.5F - var2, 0.5F + var2, 0.25F, 0.5F + var2);
        this.setCreativeTab((CreativeTabs)null);
    }

    /**
     * Gets passed in the blockID of the block below and supposed to return true if its allowed to grow on the type of
     * blockID passed in. Args: blockID
     */
    protected boolean canThisPlantGrowOnThisBlockID(int par1)
    {
        return par1 == Block.slowSand.blockID;
    }

    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        return this.canThisPlantGrowOnThisBlockID(par1World.getBlockId(par2, par3 - 1, par4));
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        int var6 = par1World.getBlockMetadata(par2, par3, par4);

        if (var6 < 3 && par5Random.nextInt(10) == 0)
        {
            ++var6;
            par1World.setBlockMetadataWithNotify(par2, par3, par4, var6, 2);
        }

        super.updateTick(par1World, par2, par3, par4, par5Random);
    }

    @SideOnly(Side.CLIENT)
    public Icon func_71858_a(int par1, int par2)
    {
        return par2 >= 3 ? this.field_94372_b[2] : (par2 > 0 ? this.field_94372_b[1] : this.field_94372_b[0]);
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 6;
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        if (!par1World.isRemote)
        {
            int var8 = 1;

            if (par5 >= 3)
            {
                var8 = 2 + par1World.rand.nextInt(3);

                if (par7 > 0)
                {
                    var8 += par1World.rand.nextInt(par7 + 1);
                }
            }

            for (int var9 = 0; var9 < var8; ++var9)
            {
                this.dropBlockAsItem_do(par1World, par2, par3, par4, new ItemStack(Item.netherStalkSeeds));
            }
        }
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return 0;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random par1Random)
    {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    public int func_71922_a(World par1World, int par2, int par3, int par4)
    {
        return Item.netherStalkSeeds.itemID;
    }

    @SideOnly(Side.CLIENT)
    public void func_94332_a(IconRegister par1IconRegister)
    {
        this.field_94372_b = new Icon[3];

        for (int var2 = 0; var2 < this.field_94372_b.length; ++var2)
        {
            this.field_94372_b[var2] = par1IconRegister.func_94245_a(this.func_111023_E() + "_stage_" + var2);
        }
    }
}
