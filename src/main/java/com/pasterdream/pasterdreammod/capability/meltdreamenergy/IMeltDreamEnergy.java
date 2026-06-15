package com.pasterdream.pasterdreammod.capability.meltdreamenergy;

public interface IMeltDreamEnergy
{
    double getMeltDreamEnergy();
    void setMeltDreamEnergy(double energy);
    void addMeltDreamEnergy(double delta);

    void setIsOrNotNeedConsumeDreamEnergy(boolean isNotNeed);
    boolean getIsOrNotNeedConsumeDreamEnergy();

    void copyValueFromOtherMeltDreamEnergy(IMeltDreamEnergy other);
}
