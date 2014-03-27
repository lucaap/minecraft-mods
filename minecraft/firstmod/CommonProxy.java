package firstmod;

import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class CommonProxy 
{
	public void addNames() 
	{
		LanguageRegistry.addName(FirstMod.absorptionPotion, "Potion of Absorption");
	}

	public void addRecipes() 
	{
		final CraftingManager cm = CraftingManager.getInstance();
        cm.addRecipe(new ItemStack(Item.energyBar), new Object[] {"SWS", 'W', Item.sugar, 'S', Item.Chocolate});
        cm.addRecipe(new ItemStack(Item.orangeFanta), new Object[] {"SWS", 'S', Item.sugar, 'W', Item.glassBottle});
        cm.addRecipe(new ItemStack(Item.strawberryFanta), new Object[] {"SWS", 'S', Item.appleRed, 'W', Item.glassBottle});
        cm.addRecipe(new ItemStack(Item.blueberryFanta),new Object[] {"SWS", 'S', ItemFood.melon, 'W', Item.glassBottle});
        cm.addRecipe(new ItemStack(Item.lemonFanta), new Object[] {"SWS", 'S', ItemFood.bucketMilk, 'W', Item.glassBottle});
        cm.addRecipe(new ItemStack(Item.mixFanta), new Object[] {"SWS", 'S', Item.appleGold, 'W', Item.glassBottle});
        cm.addRecipe(new ItemStack(Item.rawChocolate), new Object[] {"SW", 'S', new ItemStack(Item.dyePowder, 1, 3), 'W', Item.sugar});
 	}
}
