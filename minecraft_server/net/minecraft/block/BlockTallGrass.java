package net.minecraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.Icon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTallGrass extends BlockFlower
{
    private static final String[] grassTypes = new String[] {"deadbush", "tallgrass", "fern"};
    @SideOnly(Side.CLIENT)
    private Icon[] field_94366_b;

    protected BlockTallGrass(int par1)
    {
        super(par1, Material.vine);
        float var2 = 0.4F;
        this.setBlockBounds(0.5F - var2, 0.0F, 0.5F - var2, 0.5F + var2, 0.8F, 0.5F + var2);
    }

    @SideOnly(Side.CLIENT)
    public Icon func_71858_a(int par1, int par2)
    {
        if (par2 >= this.field_94366_b.length)
        {
            par2 = 0;
        }

        return this.field_94366_b[par2];
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return par2Random.nextInt(8) == 0 ? Item.seeds.itemID : -1;
    }

    /**
     * Returns the usual quantity dropped by the block plus a bonus of 1 to 'i' (inclusive).
     */
    public int quantityDroppedWithBonus(int par1, Random par2Random)
    {
        return 1 + par2Random.nextInt(par1 * 2 + 1);
    }

    /**
     * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the
     * block and l is the block's subtype/damage.
     */
    public void harvestBlock(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6)
    {
        if (!par1World.isRemote && par2EntityPlayer.getCurrentEquippedItem() != null && par2EntityPlayer.getCurrentEquippedItem().itemID == Item.shears.itemID)
        {
            par2EntityPlayer.addStat(StatList.mineBlockStatArray[this.blockID], 1);
            this.dropBlockAsItem_do(par1World, par3, par4, par5, new ItemStack(Block.tallGrass, 1, par6));
        }
        else
        {
            super.harvestBlock(par1World, par2EntityPlayer, par3, par4, par5, par6);
        }
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
        return par1 == 0 ? 16777215 : ColorizerFoliage.func_77468_c();
    }

    @SideOnly(Side.CLIENT)
    public int func_71920_b(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4);
        return var5 == 0 ? 16777215 : par1IBlockAccess.getBiomeGenForCoords(par2, par4).func_76737_k();
    }

    /**
     * Get the block's damage value (for use with pick block).
     */
    public int getDamageValue(World par1World, int par2, int par3, int par4)
    {
        return par1World.getBlockMetadata(par2, par3, par4);
    }

    @SideOnly(Side.CLIENT)
    public void func_71879_a(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int var4 = 1; var4 < 3; ++var4)
        {
            par3List.add(new ItemStack(par1, 1, var4));
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_94332_a(IconRegister par1IconRegister)
    {
        this.field_94366_b = new Icon[grassTypes.length];

        for (int var2 = 0; var2 < this.field_94366_b.length; ++var2)
        {
            this.field_94366_b[var2] = par1IconRegister.func_94245_a(grassTypes[var2]);
        }
    }
}
