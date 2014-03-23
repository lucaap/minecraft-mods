package net.minecraft.stats;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

final class StatTypeFloat implements IStatType
{
    @SideOnly(Side.CLIENT)
    public String func_75843_a(int par1)
    {
        return StatBase.func_75969_k().format((double)par1 * 0.1D);
    }
}
