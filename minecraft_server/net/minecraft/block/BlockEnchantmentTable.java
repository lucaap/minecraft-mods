package net.minecraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnchantmentTable;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockEnchantmentTable extends BlockContainer
{
    @SideOnly(Side.CLIENT)
    private Icon field_94461_a;
    @SideOnly(Side.CLIENT)
    private Icon field_94460_b;

    protected BlockEnchantmentTable(int par1)
    {
        super(par1, Material.rock);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
        this.setLightOpacity(0);
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void func_71862_a(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        super.func_71862_a(par1World, par2, par3, par4, par5Random);

        for (int var6 = par2 - 2; var6 <= par2 + 2; ++var6)
        {
            for (int var7 = par4 - 2; var7 <= par4 + 2; ++var7)
            {
                if (var6 > par2 - 2 && var6 < par2 + 2 && var7 == par4 - 1)
                {
                    var7 = par4 + 2;
                }

                if (par5Random.nextInt(16) == 0)
                {
                    for (int var8 = par3; var8 <= par3 + 1; ++var8)
                    {
                        if (par1World.getBlockId(var6, var8, var7) == Block.bookShelf.blockID)
                        {
                            if (!par1World.isAirBlock((var6 - par2) / 2 + par2, var8, (var7 - par4) / 2 + par4))
                            {
                                break;
                            }

                            par1World.spawnParticle("enchantmenttable", (double)par2 + 0.5D, (double)par3 + 2.0D, (double)par4 + 0.5D, (double)((float)(var6 - par2) + par5Random.nextFloat()) - 0.5D, (double)((float)(var8 - par3) - par5Random.nextFloat() - 1.0F), (double)((float)(var7 - par4) + par5Random.nextFloat()) - 0.5D);
                        }
                    }
                }
            }
        }
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public Icon func_71858_a(int par1, int par2)
    {
        return par1 == 0 ? this.field_94460_b : (par1 == 1 ? this.field_94461_a : this.field_94336_cN);
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World par1World)
    {
        return new TileEntityEnchantmentTable();
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        if (par1World.isRemote)
        {
            return true;
        }
        else
        {
            TileEntityEnchantmentTable var10 = (TileEntityEnchantmentTable)par1World.getBlockTileEntity(par2, par3, par4);
            par5EntityPlayer.displayGUIEnchantment(par2, par3, par4, var10.func_94135_b() ? var10.func_94133_a() : null);
            return true;
        }
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
        super.onBlockPlacedBy(par1World, par2, par3, par4, par5EntityLivingBase, par6ItemStack);

        if (par6ItemStack.hasDisplayName())
        {
            ((TileEntityEnchantmentTable)par1World.getBlockTileEntity(par2, par3, par4)).func_94134_a(par6ItemStack.getDisplayName());
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_94332_a(IconRegister par1IconRegister)
    {
        this.field_94336_cN = par1IconRegister.func_94245_a(this.func_111023_E() + "_" + "side");
        this.field_94461_a = par1IconRegister.func_94245_a(this.func_111023_E() + "_" + "top");
        this.field_94460_b = par1IconRegister.func_94245_a(this.func_111023_E() + "_" + "bottom");
    }
}
