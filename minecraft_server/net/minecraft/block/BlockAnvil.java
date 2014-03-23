package net.minecraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingSand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAnvil extends BlockSand
{
    /** List of types/statues the Anvil can be in. */
    public static final String[] statuses = new String[] {"intact", "slightlyDamaged", "veryDamaged"};
    private static final String[] anvilIconNames = new String[] {"anvil_top_damaged_0", "anvil_top_damaged_1", "anvil_top_damaged_2"};
    @SideOnly(Side.CLIENT)
    public int field_82521_b;
    @SideOnly(Side.CLIENT)
    private Icon[] field_94432_cP;

    protected BlockAnvil(int par1)
    {
        super(par1, Material.anvil);
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
        if (this.field_82521_b == 3 && par1 == 1)
        {
            int var3 = (par2 >> 2) % this.field_94432_cP.length;
            return this.field_94432_cP[var3];
        }
        else
        {
            return this.field_94336_cN;
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_94332_a(IconRegister par1IconRegister)
    {
        this.field_94336_cN = par1IconRegister.func_94245_a("anvil_base");
        this.field_94432_cP = new Icon[anvilIconNames.length];

        for (int var2 = 0; var2 < this.field_94432_cP.length; ++var2)
        {
            this.field_94432_cP[var2] = par1IconRegister.func_94245_a(anvilIconNames[var2]);
        }
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
        int var7 = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        int var8 = par1World.getBlockMetadata(par2, par3, par4) >> 2;
        ++var7;
        var7 %= 4;

        if (var7 == 0)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 2 | var8 << 2, 2);
        }

        if (var7 == 1)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 3 | var8 << 2, 2);
        }

        if (var7 == 2)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 0 | var8 << 2, 2);
        }

        if (var7 == 3)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 1 | var8 << 2, 2);
        }
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
            par5EntityPlayer.displayGUIAnvil(par2, par3, par4);
            return true;
        }
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 35;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return par1 >> 2;
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        int var5 = par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 3;

        if (var5 != 3 && var5 != 1)
        {
            this.setBlockBounds(0.125F, 0.0F, 0.0F, 0.875F, 1.0F, 1.0F);
        }
        else
        {
            this.setBlockBounds(0.0F, 0.0F, 0.125F, 1.0F, 1.0F, 0.875F);
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_71879_a(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
    }

    /**
     * Called when the falling block entity for this block is created
     */
    protected void onStartFalling(EntityFallingSand par1EntityFallingSand)
    {
        par1EntityFallingSand.setIsAnvil(true);
    }

    /**
     * Called when the falling block entity for this block hits the ground and turns back into a block
     */
    public void onFinishFalling(World par1World, int par2, int par3, int par4, int par5)
    {
        par1World.playAuxSFX(1022, par2, par3, par4, 0);
    }

    @SideOnly(Side.CLIENT)
    public boolean func_71877_c(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return true;
    }
}
