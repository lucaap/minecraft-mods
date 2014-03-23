package net.minecraft.stats;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class Achievement extends StatBase
{
    /**
     * Is the column (related to center of achievement gui, in 24 pixels unit) that the achievement will be displayed.
     */
    public final int displayColumn;

    /**
     * Is the row (related to center of achievement gui, in 24 pixels unit) that the achievement will be displayed.
     */
    public final int displayRow;

    /**
     * Holds the parent achievement, that must be taken before this achievement is avaiable.
     */
    public final Achievement parentAchievement;

    /**
     * Holds the description of the achievement, ready to be formatted and/or displayed.
     */
    private final String achievementDescription;
    @SideOnly(Side.CLIENT)
    private IStatStringFormat field_75994_l;

    /**
     * Holds the ItemStack that will be used to draw the achievement into the GUI.
     */
    public final ItemStack theItemStack;

    /**
     * Special achievements have a 'spiked' (on normal texture pack) frame, special achievements are the hardest ones to
     * achieve.
     */
    private boolean isSpecial;

    public Achievement(int par1, String par2Str, int par3, int par4, Item par5Item, Achievement par6Achievement)
    {
        this(par1, par2Str, par3, par4, new ItemStack(par5Item), par6Achievement);
    }

    public Achievement(int par1, String par2Str, int par3, int par4, Block par5Block, Achievement par6Achievement)
    {
        this(par1, par2Str, par3, par4, new ItemStack(par5Block), par6Achievement);
    }

    public Achievement(int par1, String par2Str, int par3, int par4, ItemStack par5ItemStack, Achievement par6Achievement)
    {
        super(5242880 + par1, "achievement." + par2Str);
        this.theItemStack = par5ItemStack;
        this.achievementDescription = "achievement." + par2Str + ".desc";
        this.displayColumn = par3;
        this.displayRow = par4;

        if (par3 < AchievementList.minDisplayColumn)
        {
            AchievementList.minDisplayColumn = par3;
        }

        if (par4 < AchievementList.minDisplayRow)
        {
            AchievementList.minDisplayRow = par4;
        }

        if (par3 > AchievementList.maxDisplayColumn)
        {
            AchievementList.maxDisplayColumn = par3;
        }

        if (par4 > AchievementList.maxDisplayRow)
        {
            AchievementList.maxDisplayRow = par4;
        }

        this.parentAchievement = par6Achievement;
    }

    /**
     * Indicates whether or not the given achievement or statistic is independent (i.e., lacks prerequisites for being
     * update).
     */
    public Achievement setIndependent()
    {
        this.isIndependent = true;
        return this;
    }

    /**
     * Special achievements have a 'spiked' (on normal texture pack) frame, special achievements are the hardest ones to
     * achieve.
     */
    public Achievement setSpecial()
    {
        this.isSpecial = true;
        return this;
    }

    /**
     * Adds the achievement on the internal list of registered achievements, also, it's check for duplicated id's.
     */
    public Achievement registerAchievement()
    {
        super.registerStat();
        AchievementList.achievementList.add(this);
        return this;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_75967_d()
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public String func_75989_e()
    {
        return this.field_75994_l != null ? this.field_75994_l.func_74535_a(StatCollector.translateToLocal(this.achievementDescription)) : StatCollector.translateToLocal(this.achievementDescription);
    }

    @SideOnly(Side.CLIENT)
    public Achievement func_75988_a(IStatStringFormat par1IStatStringFormat)
    {
        this.field_75994_l = par1IStatStringFormat;
        return this;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_75984_f()
    {
        return this.isSpecial;
    }

    /**
     * Register the stat into StatList.
     */
    public StatBase registerStat()
    {
        return this.registerAchievement();
    }

    /**
     * Initializes the current stat as independent (i.e., lacking prerequisites for being updated) and returns the
     * current instance.
     */
    public StatBase initIndependentStat()
    {
        return this.setIndependent();
    }
}
