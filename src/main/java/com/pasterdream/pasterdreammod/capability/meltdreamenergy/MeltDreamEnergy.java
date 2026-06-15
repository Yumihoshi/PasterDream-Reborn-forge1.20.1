package com.pasterdream.pasterdreammod.capability.meltdreamenergy;

public class MeltDreamEnergy implements IMeltDreamEnergy
{
    private double energy = 0.0;
    private boolean isNotNeedConsume = false;

    @Override
    public double getMeltDreamEnergy()
    {
        return energy;
    }

    @Override
    public void setMeltDreamEnergy(double energy)
    {
        this.energy = energy;
    }

    @Override
    public void addMeltDreamEnergy(double delta)
    {
        setMeltDreamEnergy(energy + delta);
    }

    @Override
    public void setIsOrNotNeedConsumeDreamEnergy(boolean isNotNeedConsume)
    {
        this.isNotNeedConsume = isNotNeedConsume;
    }

    @Override
    public boolean getIsOrNotNeedConsumeDreamEnergy()
    {
        return isNotNeedConsume;
    }

    @Override
    public void copyValueFromOtherMeltDreamEnergy(IMeltDreamEnergy other)
    {
        this.energy = other.getMeltDreamEnergy();
        this.isNotNeedConsume = other.getIsOrNotNeedConsumeDreamEnergy();
    }
}
