package com.pasterdream.pasterdreammod.capability.san;

public interface ISan
{
    double getSanValue();
    void setSanValue(double energy);
    void addSanValue(double delta);

    void setIsSanEnable(boolean isEnabled);
    boolean getIsSanEnabled();

    double getMaxSanValue();
    void setMaxSanValue(double maxSanValue);
    void addMaxSanValue(double delta);

    void copyValueFromOtherSan(ISan other);
}
