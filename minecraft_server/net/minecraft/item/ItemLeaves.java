package net.minecraft.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.util.Icon;
import net.minecraft.world.ColorizerFoliage;

public class ItemLeaves extends ItemBlock
{
    public ItemLeaves(int par1)
    {
        super(par1);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int par1)
    {
        return par1 | 4;
    }

    @SideOnly(Side.CLIENT)
    public Icon func_77617_a(int par1)
    {
        return Block.leaves.func_71858_a(0, par1);
    }

    @SideOnly(Side.CLIENT)
    public int func_82790_a(ItemStack par1ItemStack, int par2)
    {
        int var3 = par1ItemStack.getItemDamage();
        return (var3 & 1) == 1 ? ColorizerFoliage.func_77466_a() : ((var3 & 2) == 2 ? ColorizerFoliage.func_77469_b() : ColorizerFoliage.func_77468_c());
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack par1ItemStack)
    {
        int var2 = par1ItemStack.getItemDamage();

        if (var2 < 0 || var2 >= BlockLeaves.LEAF_TYPES.length)
        {
            var2 = 0;
        }

        return super.getUnlocalizedName() + "." + BlockLeaves.LEAF_TYPES[var2];
    }
}
