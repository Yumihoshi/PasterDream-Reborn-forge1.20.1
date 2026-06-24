package com.pasterdream.pasterdreammod.world.block.ItemContainer.crate.windmoorcrate;

import com.pasterdream.pasterdreammod.init.ModMenus;
import com.pasterdream.pasterdreammod.world.block.ItemContainer.crate.CrateMenu;
import net.minecraft.world.entity.player.Inventory;

public class WindMoorCrateMenu extends CrateMenu<WindMoorCrateBlockEntity>
{
    public WindMoorCrateMenu(int id, Inventory inventory, WindMoorCrateBlockEntity blockEntity)
    {
        super(ModMenus.WIND_MOOR_CRATE.get(), id, inventory, blockEntity);
    }
}
