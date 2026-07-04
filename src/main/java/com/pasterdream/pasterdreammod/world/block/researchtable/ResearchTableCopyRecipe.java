package com.pasterdream.pasterdreammod.world.block.researchtable;

import com.pasterdream.pasterdreammod.helper.pasterdreamingredient.FluidIngredient;
import com.pasterdream.pasterdreammod.helper.pasterdreamingredient.ItemIngredient;
import com.pasterdream.pasterdreammod.init.ModRecipes;
import com.pasterdream.pasterdreammod.recipe.researchtableinventory.IResearchTableCopyRecipe;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public class ResearchTableCopyRecipe implements IResearchTableCopyRecipe, Recipe<Container>
{
    private final ResourceLocation id;
    private final FluidIngredient fluidInput;
    private final ItemIngredient pen;
    private final int penCost;
    private final ItemIngredient sourceBook;
    private final ItemIngredient material;

    public ResearchTableCopyRecipe(ResourceLocation id, FluidIngredient fluidInput, ItemIngredient pen, ItemIngredient sourceBook, ItemIngredient material)
    {
        this.id = id;
        this.fluidInput = fluidInput;
        this.pen = pen;
        this.penCost = (pen.hasNbt() && !pen.getNbt().isEmpty() && pen.getNbt().contains("Damage") ? pen.getNbt().getInt("Damage") : 1);
        this.sourceBook = sourceBook;
        this.material = material;
    }

    @Override
    public boolean matches(Container container, Level level)
    {
        return false;
    }

    @Override
    public ItemStack assemble(Container container, RegistryAccess registryAccess)
    {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height)
    {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess access)
    {
        return ItemStack.EMPTY;
    }

    @Override
    public ResourceLocation getId()
    {
        return id;
    }

    @Override
    public FluidIngredient getFluidInput()
    {
        return fluidInput;
    }

    @Override
    public ItemIngredient getPen()
    {
        return pen;
    }

    @Override
    public int getPenCost()
    {
        return penCost;
    }

    @Override
    public ItemIngredient getSourceBook()
    {
        return sourceBook;
    }

    @Override
    public ItemIngredient getMaterial()
    {
        return material;
    }

    @Override
    public RecipeSerializer<?> getSerializer()
    {
        return ModRecipes.RESEARCH_TABLE_COPY_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType()
    {
        return ModRecipes.RESEARCH_TABLE_COPY.get();
    }
}
