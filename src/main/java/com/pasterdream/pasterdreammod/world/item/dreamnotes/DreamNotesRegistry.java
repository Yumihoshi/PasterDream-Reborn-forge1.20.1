package com.pasterdream.pasterdreammod.world.item.dreamnotes;

import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class DreamNotesRegistry
{
    private static final Map<String, DreamNotesInfo> NOTES = new HashMap<>();

    public static void register(String content, int width, int height, ResourceLocation texture)
    {
        if (NOTES.containsKey(content))
        {
            throw new IllegalArgumentException("重复的寻梦者笔记内容：" + content);
        }
        NOTES.put(content, new DreamNotesInfo(width, height, texture));
    }

    public static DreamNotesInfo getInfo(String content)
    {
        return NOTES.get(content);
    }
}
