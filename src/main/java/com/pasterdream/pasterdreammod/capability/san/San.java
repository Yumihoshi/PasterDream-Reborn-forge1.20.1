package com.pasterdream.pasterdreammod.capability.san;

public class San implements ISan
{
    private double sanValue = 100.0;
    private boolean isEnabled = true;
    private double maxSanValue = 100.0;

    @Override
    public double getSanValue()
    {
        return sanValue;
    }

    @Override
    public void setSanValue(double sanValue)
    {
        this.sanValue = sanValue;
    }

    @Override
    public void addSanValue(double delta)
    {
        setSanValue(sanValue + delta);
    }

    @Override
    public void setIsSanEnable(boolean isEnabled)
    {
        this.isEnabled = isEnabled;
    }

    @Override
    public boolean getIsSanEnabled()
    {
        return isEnabled;
    }

    @Override
    public double getMaxSanValue()
    {
        return maxSanValue;
    }

    @Override
    public void setMaxSanValue(double maxSanValue)
    {
        this.maxSanValue = maxSanValue;
    }

    @Override
    public void addMaxSanValue(double delta)
    {
        setSanValue(delta + maxSanValue);
    }

    @Override
    public void copyValueFromOtherSan(ISan other)
    {
        this.sanValue = other.getSanValue();
        this.isEnabled = other.getIsSanEnabled();
    }
}
