package net.minecraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockWorkbench extends Block
{
    @SideOnly(Side.CLIENT)
    private Icon field_94385_a;
    @SideOnly(Side.CLIENT)
    private Icon field_94384_b;

    protected BlockWorkbench(int par1)
    {
        super(par1, Material.wood);
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    @SideOnly(Side.CLIENT)
    public Icon func_71858_a(int par1, int par2)
    {
        return par1 == 1 ? this.field_94385_a : (par1 == 0 ? Block.planks.func_71851_a(par1) : (par1 != 2 && par1 != 4 ? this.field_94336_cN : this.field_94384_b));
    }

    @SideOnly(Side.CLIENT)
    public void func_94332_a(IconRegister par1IconRegister)
    {
        this.field_94336_cN = par1IconRegister.func_94245_a(this.func_111023_E() + "_side");
        this.field_94385_a = par1IconRegister.func_94245_a(this.func_111023_E() + "_top");
        this.field_94384_b = par1IconRegister.func_94245_a(this.func_111023_E() + "_front");
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
            par5EntityPlayer.displayGUIWorkbench(par2, par3, par4);
            return true;
        }
    }
}
