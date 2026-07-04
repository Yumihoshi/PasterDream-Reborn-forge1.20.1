package com.pasterdream.pasterdreammod.recipe.genericrecipe.recipematchandprocess;

public record MatchedRecipeResult<T extends IProcessingRecipe>(T recipe, MachineInventory matchedRecipeInputsAndOutputs) {}