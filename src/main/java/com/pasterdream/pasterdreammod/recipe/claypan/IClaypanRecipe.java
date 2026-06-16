package com.pasterdream.pasterdreammod.recipe.claypan;

import net.minecraft.core.RegistryAccess;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;

public interface IClaypanRecipe extends Recipe<Container>
{
    FluidStack getFluidInput();
    int getProcessingTime();

    @Override
    default boolean matches(Container container, Level level)
    {
        return false;
    }

    @Override
    ItemStack assemble(Container container, RegistryAccess registryAccess);

    @Override
    ItemStack getResultItem(RegistryAccess registryAccess);

    @Override
    default boolean canCraftInDimensions(int width, int height)
    {
        return true;
    }
}
