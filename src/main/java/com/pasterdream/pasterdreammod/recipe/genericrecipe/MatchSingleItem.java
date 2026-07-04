package com.pasterdream.pasterdreammod.recipe.genericrecipe;

import com.pasterdream.pasterdreammod.helper.pasterdreamingredient.ItemIngredient;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class MatchSingleItem
{
    public static ItemStack match(ItemIngredient itemIngredient, ItemStack matchItem)
    {
        if(itemIngredient.isTag())
        {
            List<ItemStack> ListItemStackFromTag = itemIngredient.getListItemStackFromTag();
            for(ItemStack itemStack : ListItemStackFromTag)
            {
                if(ItemStack.isSameItem(itemStack, matchItem))
                {
                    return itemStack;
                }
            }
            return null;
        }
            else
            {
                ItemStack itemStack = itemIngredient.getItemStack();
                if(ItemStack.isSameItem(itemStack, matchItem))
                {
                    return itemStack;
                }
                    else
                    {
                        return null;
                    }
            }
    }
}
