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

public class ResistancePotion extends ItemFood
{
	private int[] potionEffect = {22,};
	private int[] potionDuration = {120,};
	private int[] potionTier = {0,};
	
    public ResistancePotion(int itemID)
	{
    	super(itemID, 0, 0F, false);
	    this.maxStackSize = 1;
	    this.setAlwaysEdible();
	    this.setCreativeTab(CreativeTabs.tabBrewing);     
	    setTextureName("firstmod:resistance_potion");
	}
    
	public void onFoodEaten(ItemStack itemstack, World world, EntityPlayer player)
    {	
        --itemstack.stackSize;
        player.getFoodStats().addStats(this);
        world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
        this.addPotionEffects(itemstack, world, player);   		        
    }
    
    protected void addPotionEffects(ItemStack item, World world, EntityPlayer player)
    {
        player.addPotionEffect(new PotionEffect(potionEffect[0], potionDuration[0] * 20, potionTier[0]));       
     }
    
    public EnumAction getItemUseAction(ItemStack itemstack)
    {
        return EnumAction.drink;       
    }
    
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
    {     	
    	player.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));	       	
        return itemstack; 
    }
    
    public boolean hasEffect(ItemStack itemstack)
    {	
        return true;        
    }
    
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4)
    {
    	list.add("Resistance I (2:00)");    	
    }	
}
