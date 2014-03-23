package net.minecraft.creativetab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.Item;

final class CreativeTabDeco extends CreativeTabs
{
    CreativeTabDeco(int par1, String par2Str)
    {
        super(par1, par2Str);
    }

    @SideOnly(Side.CLIENT)
    public int func_78012_e()
    {
        return Item.redstone.itemID;
    }
}
