package net.minecraft.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;

public class ItemCoal extends Item
{
    @SideOnly(Side.CLIENT)
    private Icon field_111220_a;

    public ItemCoal(int par1)
    {
        super(par1);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        return par1ItemStack.getItemDamage() == 1 ? "item.charcoal" : "item.coal";
    }

    @SideOnly(Side.CLIENT)
    public void func_77633_a(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
    }

    @SideOnly(Side.CLIENT)
    public Icon func_77617_a(int par1)
    {
        return par1 == 1 ? this.field_111220_a : super.func_77617_a(par1);
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IconRegister par1IconRegister)
    {
        super.func_94581_a(par1IconRegister);
        this.field_111220_a = par1IconRegister.func_94245_a("charcoal");
    }
}
