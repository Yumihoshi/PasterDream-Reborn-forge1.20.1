package com.pasterdream.pasterdreammod.init;

import com.pasterdream.pasterdreammod.advancement.critereon.FoundTombTrigger;
import com.pasterdream.pasterdreammod.advancement.critereon.NewStandardSwordDrawingTrigger;
import com.pasterdream.pasterdreammod.advancement.critereon.UseBoneNeedleTrigger;
import net.minecraft.advancements.CriteriaTriggers;

/**
 * 自定义进度触发器注册中心。
 * 在此声明所有自定义 CriterionTrigger 实例并在 {@link #init()} 中统一注册。
 */
public class ModCriteriaTriggers {

    /** 在梦维度中使用苍白骨针（支持普通使用 / 坠落使用两种条件） */
    public static final UseBoneNeedleTrigger USE_BONE_NEEDLE = new UseBoneNeedleTrigger();

    /** 进入失落剑冢结构 */
    public static final FoundTombTrigger FOUND_TOMB = new FoundTombTrigger();

    /** 新概念拔剑 */
    public static final NewStandardSwordDrawingTrigger NEW_STANDARD_SWORD_DRAWING = new NewStandardSwordDrawingTrigger();

    public static void init() {
        CriteriaTriggers.register(USE_BONE_NEEDLE);
        CriteriaTriggers.register(FOUND_TOMB);
        CriteriaTriggers.register(NEW_STANDARD_SWORD_DRAWING);
    }
}
