package net.minecraft.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.util.Icon;

public class ItemColored extends ItemBlock
{
    private final Block blockRef;
    private String[] blockNames;

    public ItemColored(int par1, boolean par2)
    {
        super(par1);
        this.blockRef = Block.blocksList[this.getBlockID()];

        if (par2)
        {
            this.setMaxDamage(0);
            this.setHasSubtypes(true);
        }
    }

    @SideOnly(Side.CLIENT)
    public int func_82790_a(ItemStack par1ItemStack, int par2)
    {
        return this.blockRef.func_71889_f_(par1ItemStack.getItemDamage());
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int par1)
    {
        return par1;
    }

    /**
     * Sets the array of strings to be used for name lookups from item damage to metadata
     */
    public ItemColored setBlockNames(String[] par1ArrayOfStr)
    {
        this.blockNames = par1ArrayOfStr;
        return this;
    }

    @SideOnly(Side.CLIENT)
    public Icon func_77617_a(int par1)
    {
        return this.blockRef.func_71858_a(0, par1);
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        if (this.blockNames == null)
        {
            return super.getUnlocalizedName(par1ItemStack);
        }
        else
        {
            int var2 = par1ItemStack.getItemDamage();
            return var2 >= 0 && var2 < this.blockNames.length ? super.getUnlocalizedName(par1ItemStack) + "." + this.blockNames[var2] : super.getUnlocalizedName(par1ItemStack);
        }
    }
}
