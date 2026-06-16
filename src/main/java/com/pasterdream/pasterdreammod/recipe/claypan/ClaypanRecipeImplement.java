package com.pasterdream.pasterdreammod.recipe.claypan;

import com.pasterdream.pasterdreammod.init.ModRecipes;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;

public class ClaypanRecipeImplement implements IClaypanRecipe
{
    private final ResourceLocation id;
    private final FluidStack fluidInput;
    private final ItemStack output;
    private final int processingTime;

    public ClaypanRecipeImplement(ResourceLocation id, FluidStack fluidInput, ItemStack output, int processingTime)
    {
        this.id = id;
        this.fluidInput = fluidInput;
        this.output = output;
        this.processingTime = processingTime;
    }

    @Override
    public FluidStack getFluidInput()
    {
        return fluidInput;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess)
    {
        return output.copy();
    }

    @Override
    public int getProcessingTime()
    {
        return processingTime;
    }

    @Override
    public ResourceLocation getId()
    {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return ModRecipes.CLAYPAN_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType()
    {
        return ModRecipes.CLAYPAN.get();
    }

    @Override
    public ItemStack assemble(Container container, RegistryAccess registryAccess)
    {
        return output.copy();
    }

    @Override
    public boolean matches(Container container, Level level)
    {
        return false;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height)
    {
        return true;
    }
}
