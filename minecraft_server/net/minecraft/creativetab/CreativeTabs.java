package net.minecraft.creativetab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.Item;

public class CreativeTabs
{
    public static final CreativeTabs[] creativeTabArray = new CreativeTabs[12];
    public static final CreativeTabs tabBlock = new CreativeTabCombat(0, "buildingBlocks");
    public static final CreativeTabs tabDecorations = new CreativeTabBlock(1, "decorations");
    public static final CreativeTabs tabRedstone = new CreativeTabDeco(2, "redstone");
    public static final CreativeTabs tabTransport = new CreativeTabRedstone(3, "transportation");
    public static final CreativeTabs tabMisc = (new CreativeTabTransport(4, "misc")).func_111229_a(new EnumEnchantmentType[] {EnumEnchantmentType.all});
    public static final CreativeTabs tabAllSearch = (new CreativeTabMisc(5, "search")).setBackgroundImageName("item_search.png");
    public static final CreativeTabs tabFood = new CreativeTabSearch(6, "food");
    public static final CreativeTabs tabTools = (new CreativeTabFood(7, "tools")).func_111229_a(new EnumEnchantmentType[] {EnumEnchantmentType.digger});
    public static final CreativeTabs tabCombat = (new CreativeTabTools(8, "combat")).func_111229_a(new EnumEnchantmentType[] {EnumEnchantmentType.armor, EnumEnchantmentType.armor_feet, EnumEnchantmentType.armor_head, EnumEnchantmentType.armor_legs, EnumEnchantmentType.armor_torso, EnumEnchantmentType.bow, EnumEnchantmentType.weapon});
    public static final CreativeTabs tabBrewing = new CreativeTabBrewing(9, "brewing");
    public static final CreativeTabs tabMaterials = new CreativeTabMaterial(10, "materials");
    public static final CreativeTabs tabInventory = (new CreativeTabInventory(11, "inventory")).setBackgroundImageName("inventory.png").setNoScrollbar().setNoTitle();
    private final int tabIndex;
    private final String tabLabel;

    /** Texture to use. */
    private String backgroundImageName = "items.png";
    private boolean hasScrollbar = true;

    /** Whether to draw the title in the foreground of the creative GUI */
    private boolean drawTitle = true;
    private EnumEnchantmentType[] field_111230_s;

    public CreativeTabs(int par1, String par2Str)
    {
        this.tabIndex = par1;
        this.tabLabel = par2Str;
        creativeTabArray[par1] = this;
    }

    @SideOnly(Side.CLIENT)
    public int func_78021_a()
    {
        return this.tabIndex;
    }

    public CreativeTabs setBackgroundImageName(String par1Str)
    {
        this.backgroundImageName = par1Str;
        return this;
    }

    @SideOnly(Side.CLIENT)
    public String func_78013_b()
    {
        return this.tabLabel;
    }

    @SideOnly(Side.CLIENT)
    public String func_78024_c()
    {
        return "itemGroup." + this.func_78013_b();
    }

    @SideOnly(Side.CLIENT)
    public Item func_78016_d()
    {
        return Item.itemsList[this.func_78012_e()];
    }

    @SideOnly(Side.CLIENT)
    public int func_78012_e()
    {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    public String func_78015_f()
    {
        return this.backgroundImageName;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_78019_g()
    {
        return this.drawTitle;
    }

    public CreativeTabs setNoTitle()
    {
        this.drawTitle = false;
        return this;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_78017_i()
    {
        return this.hasScrollbar;
    }

    public CreativeTabs setNoScrollbar()
    {
        this.hasScrollbar = false;
        return this;
    }

    @SideOnly(Side.CLIENT)
    public int func_78020_k()
    {
        return this.tabIndex % 6;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_78023_l()
    {
        return this.tabIndex < 6;
    }

    @SideOnly(Side.CLIENT)
    public EnumEnchantmentType[] func_111225_m()
    {
        return this.field_111230_s;
    }

    public CreativeTabs func_111229_a(EnumEnchantmentType ... par1ArrayOfEnumEnchantmentType)
    {
        this.field_111230_s = par1ArrayOfEnumEnchantmentType;
        return this;
    }

    @SideOnly(Side.CLIENT)
    public boolean func_111226_a(EnumEnchantmentType par1EnumEnchantmentType)
    {
        if (this.field_111230_s == null)
        {
            return false;
        }
        else
        {
            EnumEnchantmentType[] var2 = this.field_111230_s;
            int var3 = var2.length;

            for (int var4 = 0; var4 < var3; ++var4)
            {
                EnumEnchantmentType var5 = var2[var4];

                if (var5 == par1EnumEnchantmentType)
                {
                    return true;
                }
            }

            return false;
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_78018_a(List par1List)
    {
        Item[] var2 = Item.itemsList;
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4)
        {
            Item var5 = var2[var4];

            if (var5 != null && var5.func_77640_w() == this)
            {
                var5.func_77633_a(var5.itemID, this, par1List);
            }
        }

        if (this.func_111225_m() != null)
        {
            this.func_92116_a(par1List, this.func_111225_m());
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_92116_a(List par1List, EnumEnchantmentType ... par2ArrayOfEnumEnchantmentType)
    {
        Enchantment[] var3 = Enchantment.enchantmentsList;
        int var4 = var3.length;

        for (int var5 = 0; var5 < var4; ++var5)
        {
            Enchantment var6 = var3[var5];

            if (var6 != null && var6.type != null)
            {
                boolean var7 = false;

                for (int var8 = 0; var8 < par2ArrayOfEnumEnchantmentType.length && !var7; ++var8)
                {
                    if (var6.type == par2ArrayOfEnumEnchantmentType[var8])
                    {
                        var7 = true;
                    }
                }

                if (var7)
                {
                    par1List.add(Item.enchantedBook.getEnchantedItemStack(new EnchantmentData(var6, var6.getMaxLevel())));
                }
            }
        }
    }
}
