package net.minecraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.Icon;

public class BlockButtonWood extends BlockButton
{
    protected BlockButtonWood(int par1)
    {
        super(par1, true);
    }

    @SideOnly(Side.CLIENT)
    public Icon func_71858_a(int par1, int par2)
    {
        return Block.planks.func_71851_a(1);
    }
}
