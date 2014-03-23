package net.minecraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.world.IBlockAccess;

public class BlockBreakable extends Block
{
    private boolean localFlag;
    private String breakableBlockIcon;

    protected BlockBreakable(int par1, String par2Str, Material par3Material, boolean par4)
    {
        super(par1, par3Material);
        this.localFlag = par4;
        this.breakableBlockIcon = par2Str;
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
    public boolean func_71877_c(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        int var6 = par1IBlockAccess.getBlockId(par2, par3, par4);
        return !this.localFlag && var6 == this.blockID ? false : super.func_71877_c(par1IBlockAccess, par2, par3, par4, par5);
    }

    @SideOnly(Side.CLIENT)
    public void func_94332_a(IconRegister par1IconRegister)
    {
        this.field_94336_cN = par1IconRegister.func_94245_a(this.breakableBlockIcon);
    }
}
