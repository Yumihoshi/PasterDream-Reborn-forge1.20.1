package com.pasterdream.pasterdreammod.capability.meltdreamenergy;

public class MeltDreamEnergy implements IMeltDreamEnergy
{
    private double meltDreamEnergy = 0.0;
    private boolean isNotNeedConsume = false;
    private double maxMeltDreamEnergy = 100.0;

    @Override
    public double getMeltDreamEnergy()
    {
        return meltDreamEnergy;
    }

    @Override
    public void setMeltDreamEnergy(double meltDreamEnergy)
    {
        this.meltDreamEnergy = meltDreamEnergy;
    }

    @Override
    public void addMeltDreamEnergy(double delta)
    {
        setMeltDreamEnergy(meltDreamEnergy + delta);
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
    public double getMaxMeltDreamEnergy()
    {
        return maxMeltDreamEnergy;
    }

    @Override
    public void setMaxMeltDreamEnergy(double maxMeltDreamEnergy)
    {
        this.maxMeltDreamEnergy = maxMeltDreamEnergy;
    }

    @Override
    public void addMaxMeltDreamEnergy(double delta)
    {
        setMaxMeltDreamEnergy(delta + maxMeltDreamEnergy);
    }

    @Override
    public void copyValueFromOtherMeltDreamEnergy(IMeltDreamEnergy other)
    {
        this.meltDreamEnergy = other.getMeltDreamEnergy();
        this.isNotNeedConsume = other.getIsOrNotNeedConsumeDreamEnergy();
        this.maxMeltDreamEnergy = other.getMaxMeltDreamEnergy();
    }
}
