package net.minecraft.stats;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import net.minecraft.util.StatCollector;

public class StatBase
{
    /** The Stat ID */
    public final int statId;

    /** The Stat name */
    private final String statName;
    public boolean isIndependent;

    /** Holds the GUID of the stat. */
    public String statGuid;
    private final IStatType type;
    private static NumberFormat numberFormat = NumberFormat.getIntegerInstance(Locale.US);
    public static IStatType simpleStatType = new StatTypeSimple();
    private static DecimalFormat decimalFormat = new DecimalFormat("########0.00");
    public static IStatType timeStatType = new StatTypeTime();
    public static IStatType distanceStatType = new StatTypeDistance();
    public static IStatType field_111202_k = new StatTypeFloat();

    public StatBase(int par1, String par2Str, IStatType par3IStatType)
    {
        this.statId = par1;
        this.statName = par2Str;
        this.type = par3IStatType;
    }

    public StatBase(int par1, String par2Str)
    {
        this(par1, par2Str, simpleStatType);
    }

    /**
     * Initializes the current stat as independent (i.e., lacking prerequisites for being updated) and returns the
     * current instance.
     */
    public StatBase initIndependentStat()
    {
        this.isIndependent = true;
        return this;
    }

    /**
     * Register the stat into StatList.
     */
    public StatBase registerStat()
    {
        if (StatList.oneShotStats.containsKey(Integer.valueOf(this.statId)))
        {
            throw new RuntimeException("Duplicate stat id: \"" + ((StatBase)StatList.oneShotStats.get(Integer.valueOf(this.statId))).statName + "\" and \"" + this.statName + "\" at id " + this.statId);
        }
        else
        {
            StatList.allStats.add(this);
            StatList.oneShotStats.put(Integer.valueOf(this.statId), this);
            this.statGuid = AchievementMap.getGuid(this.statId);
            return this;
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean func_75967_d()
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public String func_75968_a(int par1)
    {
        return this.type.func_75843_a(par1);
    }

    @SideOnly(Side.CLIENT)
    public String func_75970_i()
    {
        return this.statName;
    }

    public String toString()
    {
        return StatCollector.translateToLocal(this.statName);
    }

    @SideOnly(Side.CLIENT)

    static NumberFormat func_75965_j()
    {
        return numberFormat;
    }

    @SideOnly(Side.CLIENT)

    static DecimalFormat func_75969_k()
    {
        return decimalFormat;
    }
}
