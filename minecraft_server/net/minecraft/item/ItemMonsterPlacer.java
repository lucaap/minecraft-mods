package net.minecraft.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.Facing;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemMonsterPlacer extends Item
{
    @SideOnly(Side.CLIENT)
    private Icon field_94593_a;

    public ItemMonsterPlacer(int par1)
    {
        super(par1);
        this.setHasSubtypes(true);
        this.setCreativeTab(CreativeTabs.tabMisc);
    }

    public String getItemDisplayName(ItemStack par1ItemStack)
    {
        String var2 = ("" + StatCollector.translateToLocal(this.getUnlocalizedName() + ".name")).trim();
        String var3 = EntityList.getStringFromID(par1ItemStack.getItemDamage());

        if (var3 != null)
        {
            var2 = var2 + " " + StatCollector.translateToLocal("entity." + var3 + ".name");
        }

        return var2;
    }

    @SideOnly(Side.CLIENT)
    public int func_82790_a(ItemStack par1ItemStack, int par2)
    {
        EntityEggInfo var3 = (EntityEggInfo)EntityList.entityEggs.get(Integer.valueOf(par1ItemStack.getItemDamage()));
        return var3 != null ? (par2 == 0 ? var3.primaryColor : var3.secondaryColor) : 16777215;
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (par3World.isRemote)
        {
            return true;
        }
        else
        {
            int var11 = par3World.getBlockId(par4, par5, par6);
            par4 += Facing.offsetsXForSide[par7];
            par5 += Facing.offsetsYForSide[par7];
            par6 += Facing.offsetsZForSide[par7];
            double var12 = 0.0D;

            if (par7 == 1 && Block.blocksList[var11] != null && Block.blocksList[var11].getRenderType() == 11)
            {
                var12 = 0.5D;
            }

            Entity var14 = spawnCreature(par3World, par1ItemStack.getItemDamage(), (double)par4 + 0.5D, (double)par5 + var12, (double)par6 + 0.5D);

            if (var14 != null)
            {
                if (var14 instanceof EntityLivingBase && par1ItemStack.hasDisplayName())
                {
                    ((EntityLiving)var14).setCustomNameTag(par1ItemStack.getDisplayName());
                }

                if (!par2EntityPlayer.capabilities.isCreativeMode)
                {
                    --par1ItemStack.stackSize;
                }
            }

            return true;
        }
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (par2World.isRemote)
        {
            return par1ItemStack;
        }
        else
        {
            MovingObjectPosition var4 = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);

            if (var4 == null)
            {
                return par1ItemStack;
            }
            else
            {
                if (var4.typeOfHit == EnumMovingObjectType.TILE)
                {
                    int var5 = var4.blockX;
                    int var6 = var4.blockY;
                    int var7 = var4.blockZ;

                    if (!par2World.canMineBlock(par3EntityPlayer, var5, var6, var7))
                    {
                        return par1ItemStack;
                    }

                    if (!par3EntityPlayer.canPlayerEdit(var5, var6, var7, var4.sideHit, par1ItemStack))
                    {
                        return par1ItemStack;
                    }

                    if (par2World.getBlockMaterial(var5, var6, var7) == Material.water)
                    {
                        Entity var8 = spawnCreature(par2World, par1ItemStack.getItemDamage(), (double)var5, (double)var6, (double)var7);

                        if (var8 != null)
                        {
                            if (var8 instanceof EntityLivingBase && par1ItemStack.hasDisplayName())
                            {
                                ((EntityLiving)var8).setCustomNameTag(par1ItemStack.getDisplayName());
                            }

                            if (!par3EntityPlayer.capabilities.isCreativeMode)
                            {
                                --par1ItemStack.stackSize;
                            }
                        }
                    }
                }

                return par1ItemStack;
            }
        }
    }

    /**
     * Spawns the creature specified by the egg's type in the location specified by the last three parameters.
     * Parameters: world, entityID, x, y, z.
     */
    public static Entity spawnCreature(World par0World, int par1, double par2, double par4, double par6)
    {
        if (!EntityList.entityEggs.containsKey(Integer.valueOf(par1)))
        {
            return null;
        }
        else
        {
            Entity var8 = null;

            for (int var9 = 0; var9 < 1; ++var9)
            {
                var8 = EntityList.createEntityByID(par1, par0World);

                if (var8 != null && var8 instanceof EntityLivingBase)
                {
                    EntityLiving var10 = (EntityLiving)var8;
                    var8.setLocationAndAngles(par2, par4, par6, MathHelper.wrapAngleTo180_float(par0World.rand.nextFloat() * 360.0F), 0.0F);
                    var10.rotationYawHead = var10.rotationYaw;
                    var10.renderYawOffset = var10.rotationYaw;
                    var10.onSpawnWithEgg((EntityLivingData)null);
                    par0World.spawnEntityInWorld(var8);
                    var10.playLivingSound();
                }
            }

            return var8;
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean func_77623_v()
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public Icon func_77618_c(int par1, int par2)
    {
        return par2 > 0 ? this.field_94593_a : super.func_77618_c(par1, par2);
    }

    @SideOnly(Side.CLIENT)
    public void func_77633_a(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        Iterator var4 = EntityList.entityEggs.values().iterator();

        while (var4.hasNext())
        {
            EntityEggInfo var5 = (EntityEggInfo)var4.next();
            par3List.add(new ItemStack(par1, 1, var5.spawnedID));
        }
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IconRegister par1IconRegister)
    {
        super.func_94581_a(par1IconRegister);
        this.field_94593_a = par1IconRegister.func_94245_a(this.func_111208_A() + "_overlay");
    }
}
