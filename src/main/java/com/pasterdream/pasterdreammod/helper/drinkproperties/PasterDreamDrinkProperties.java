package com.pasterdream.pasterdreammod.helper.drinkproperties;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class PasterDreamDrinkProperties extends Item.Properties
{
    private int useDuration = 32;
    private double sanAdd = 0;
    private double meltDreamEnergyAdd = 0;

    @Override
    public PasterDreamDrinkProperties food(FoodProperties food)
    {
        super.food(food);
        return this;
    }

    @Override
    public PasterDreamDrinkProperties stacksTo(int size)
    {
        super.stacksTo(size);
        return this;
    }

    public PasterDreamDrinkProperties useDuration(int ticks)
    {
        this.useDuration = ticks;
        return this;
    }

    public PasterDreamDrinkProperties sanAdd(double amount)
    {
        this.sanAdd = amount;
        return this;
    }

    public PasterDreamDrinkProperties meltDreamEnergyAdd(double amount)
    {
        this.meltDreamEnergyAdd = amount;
        return this;
    }

    public int getUseDuration()
    {
        return useDuration;
    }

    public double getSanAdd()
    {
        return sanAdd;
    }

    public double getMeltDreamEnergyAdd()
    {
        return meltDreamEnergyAdd;
    }
}
