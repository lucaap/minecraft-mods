package firstmod;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class LucaPotion extends ItemFood
{
    public LucaPotion(int itemID)
	{
    	super(itemID, 0, 0F, false);
	    this.maxStackSize = 1;
	    this.setAlwaysEdible();
	    this.setCreativeTab(CreativeTabs.tabBrewing);     
	    setTextureName("firstmod:lucaPotion");
	}
    
    @Override
	public void onFoodEaten(ItemStack itemstack, World world, EntityPlayer player)
    {	
        --itemstack.stackSize;
        player.getFoodStats().addStats(this);
        world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
        this.addPotionEffects(itemstack, world, player);   		        
    }
    
    protected void addPotionEffects(ItemStack item, World world, EntityPlayer player)
    {
        player.addPotionEffect(new PotionEffect(22, 120 * 20, 3));       
    }
    
    @Override
    public EnumAction getItemUseAction(ItemStack itemstack)
    {
        return EnumAction.drink;       
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
    {     	
    	player.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));	       	
        return itemstack; 
    }
    
    @Override
    public boolean hasEffect(ItemStack itemstack)
    {	
        return true;        
    }
    
    @Override
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4)
    {
    	list.add("Absorption IV (2:00)");    	
    }	
}
