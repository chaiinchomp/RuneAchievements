package com.runeachievements.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Category {

    CHARACTER("C_0", "Character"),
    CLUES("C_1", "Clue Scrolls"),
    DIARIES("C_2", "Achievement Diaries"),
    EVENTS("C_3", "Events"),
    MINIGAMES("C_4", "Minigames"),
    COMBAT("C_5", "Combat"),
    OTHER("C_6", "Other"),
    PETS("C_7", "Pets"),
    SKILLS("C_8", "Skilling"),
    QUESTS("C_9", "Quests"),
    BOSSES("C_10", "Bosses & Raids");

    public final String uuid;
    public final String name;

}
