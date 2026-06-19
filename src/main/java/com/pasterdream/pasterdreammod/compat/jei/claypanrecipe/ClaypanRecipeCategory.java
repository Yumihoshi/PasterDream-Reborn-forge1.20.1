package com.pasterdream.pasterdreammod.compat.jei.claypanrecipe;

import com.pasterdream.pasterdreammod.PasterDreamMod;
import com.pasterdream.pasterdreammod.helper.fluidingredient.FluidIngredient;
import com.pasterdream.pasterdreammod.init.ModBlocks;
import com.pasterdream.pasterdreammod.world.block.claypan.ClaypanRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class ClaypanRecipeCategory implements IRecipeCategory<ClaypanRecipe>
{
    public static final RecipeType<ClaypanRecipe> CLAYPAN_RECIPE_TYPE = new RecipeType<>(ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "claypan"), ClaypanRecipe.class);
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(PasterDreamMod.MOD_ID, "textures/gui/claypan/claypan.png");
    private final IDrawable background;
    private final IDrawable icon;

    public ClaypanRecipeCategory(IGuiHelper helper)
    {
        background = helper.drawableBuilder(TEXTURE, 0, 0, 82, 28).setTextureSize(82, 28).build();
        icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.CLAYPAN.get()));
    }

    @Override
    public RecipeType<ClaypanRecipe> getRecipeType()
    {
        return CLAYPAN_RECIPE_TYPE;
    }

    @Override
    public Component getTitle()
    {
        return Component.translatable("block." + PasterDreamMod.MOD_ID + ".claypan");
    }

    @Override
    public IDrawable getIcon()
    {
        return icon;
    }

    @Override
    public int getWidth()
    {
        return 82;
    }

    @Override
    public int getHeight()
    {
        return 28;
    }

    @Override
    public void draw(ClaypanRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY)
    {
        int time = recipe.getProcessingTime();
        background.draw(guiGraphics);
        guiGraphics.drawCenteredString(Minecraft.getInstance().font, String.format("%02d", (time / 72000)) + "h" + String.format("%02d", ((time % 72000) / 1200)) + "m" + String.format("%02d", ((time % 1200) / 20)) + "s" + String.format("%02d", (time % 20)) + "tick", 41, 34, 0xFFFFFFFF);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ClaypanRecipe recipe, IFocusGroup focuses)
    {
        List<FluidIngredient> fluidInputs = recipe.getInputFluidIngredients();
        if (!fluidInputs.isEmpty())
        {
            FluidIngredient fluidIngredient = fluidInputs.get(0);
            Fluid fluid = fluidIngredient.getFluid();
            int amount = fluidIngredient.getAmount();
            CompoundTag nbt = fluidIngredient.getNbt();
            IRecipeSlotBuilder slot = builder.addSlot(RecipeIngredientRole.INPUT, 6, 6).addFluidStack(fluid, amount, nbt).setFluidRenderer(amount, false, 16, 16);
        }

        // 物品输出
        List<Ingredient> itemOutputs = recipe.getOutputItemIngredients();
        if (!itemOutputs.isEmpty())
        {
            Ingredient outputIng = itemOutputs.get(0);
            ItemStack outputStack = outputIng.getItems().length > 0 ? outputIng.getItems()[0] : ItemStack.EMPTY;
            if (!outputStack.isEmpty())
            {
                builder.addSlot(RecipeIngredientRole.OUTPUT, 60, 6).addItemStack(outputStack);
            }
        }
    }
}
