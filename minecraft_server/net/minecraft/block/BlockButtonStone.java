package net.minecraft.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.Icon;

public class BlockButtonStone extends BlockButton
{
    protected BlockButtonStone(int par1)
    {
        super(par1, false);
    }

    @SideOnly(Side.CLIENT)
    public Icon func_71858_a(int par1, int par2)
    {
        return Block.stone.func_71851_a(1);
    }
}
