package com.pasterdream.pasterdreammod.worldgen.feature;

import com.pasterdream.pasterdreammod.init.ModBlocks;
import com.pasterdream.pasterdreammod.init.ModTreeDecoratorTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

public class PinkShroomlightTreeDecorator extends TreeDecorator {

    public static final PinkShroomlightTreeDecorator INSTANCE = new PinkShroomlightTreeDecorator();

    @Override
    public void place(Context context) {
        RandomSource random = context.random();

        for (BlockPos logPos : context.logs()) {
            if (logPos.getY() - context.logs().get(0).getY() < 2) continue;

            if (random.nextFloat() > 0.25f) continue;

            Direction dir = Direction.Plane.HORIZONTAL.getRandomDirection(random);
            BlockPos target = logPos.relative(dir);
            if (context.isAir(target)) {
                context.setBlock(target, ModBlocks.PINK_SHROOMLIGHT.get().defaultBlockState());
            }
        }
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return ModTreeDecoratorTypes.PINK_SHROOMLIGHT.get();
    }
}
