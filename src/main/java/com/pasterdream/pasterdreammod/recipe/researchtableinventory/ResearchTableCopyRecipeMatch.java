package com.pasterdream.pasterdreammod.recipe.researchtableinventory;

import com.pasterdream.pasterdreammod.recipe.genericrecipe.MatchSingleFluid;
import com.pasterdream.pasterdreammod.recipe.genericrecipe.MatchSingleItem;
import com.pasterdream.pasterdreammod.world.block.researchtable.ResearchTableCopyRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.Collection;

public class ResearchTableCopyRecipeMatch
{
    public static ResearchTableCopyRecipeInventory matches(ResearchTableCopyRecipeInventory inventory, Collection<ResearchTableCopyRecipe> recipes)
    {
        FluidStack matchedFluidInput;
        ItemStack matchedPen;
        ItemStack matchedSourceBook;
        ItemStack matchedMaterial;

        for(ResearchTableCopyRecipe recipe : recipes)
        {
            matchedFluidInput = MatchSingleFluid.match(recipe.getFluidInput(), inventory.fluidStack());
            if(matchedFluidInput == null)
            {
                continue;
            }

            matchedPen = MatchSingleItem.match(recipe.getPen(), inventory.pen());
            if(matchedPen == null)
            {
                continue;
            }

            matchedSourceBook = MatchSingleItem.match(recipe.getSourceBook(), inventory.sourceBook());
            if(matchedSourceBook == null)
            {
                continue;
            }

            matchedMaterial = MatchSingleItem.match(recipe.getMaterial(), inventory.material());
            if(matchedMaterial == null)
            {
                continue;
            }

            return new ResearchTableCopyRecipeInventory(matchedFluidInput, matchedPen, matchedSourceBook, matchedMaterial);
        }
        return null;
    }
}
