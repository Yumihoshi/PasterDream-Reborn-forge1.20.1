package com.pasterdream.pasterdreammod.world.block.desk.dyedreamdesk;

import com.pasterdream.pasterdreammod.init.ModMenus;
import com.pasterdream.pasterdreammod.world.block.desk.DeskMenu;
import net.minecraft.world.entity.player.Inventory;

public class DyedreamDeskMenu extends DeskMenu<DyedreamDeskBlockEntity>
{
    public DyedreamDeskMenu(int id, Inventory inv, DyedreamDeskBlockEntity blockEntity)
    {
        super(ModMenus.DYEDREAM_DESK.get(), id, inv, blockEntity);
    }
}
