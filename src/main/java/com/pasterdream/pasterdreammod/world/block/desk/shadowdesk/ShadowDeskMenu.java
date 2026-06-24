package com.pasterdream.pasterdreammod.world.block.desk.shadowdesk;

import com.pasterdream.pasterdreammod.init.ModMenus;
import com.pasterdream.pasterdreammod.world.block.desk.DeskMenu;
import net.minecraft.world.entity.player.Inventory;

public class ShadowDeskMenu extends DeskMenu<ShadowDeskBlockEntity>
{
    public ShadowDeskMenu(int id, Inventory inv, ShadowDeskBlockEntity blockEntity)
    {
        super(ModMenus.SHADOW_DESK.get(), id, inv, blockEntity);
    }
}
