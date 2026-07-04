package com.pasterdream.pasterdreammod.recipe.researchtableinventory;

import com.pasterdream.pasterdreammod.helper.pasterdreamingredient.FluidIngredient;
import com.pasterdream.pasterdreammod.helper.pasterdreamingredient.ItemIngredient;

public interface IResearchTableCopyRecipe
{
    FluidIngredient getFluidInput();
    ItemIngredient getPen();
    int getPenCost();
    ItemIngredient getSourceBook();
    ItemIngredient getMaterial();
}
