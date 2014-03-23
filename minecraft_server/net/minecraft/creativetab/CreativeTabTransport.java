package net.minecraft.creativetab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;

final class CreativeTabTransport extends CreativeTabs
{
    CreativeTabTransport(int par1, String par2Str)
    {
        super(par1, par2Str);
    }

    @SideOnly(Side.CLIENT)
    public int func_78012_e()
    {
        return Item.bucketLava.itemID;
    }
}
