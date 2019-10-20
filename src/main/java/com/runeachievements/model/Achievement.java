package com.runeachievements.model;

import lombok.Data;

@Data
public class Achievement {

    private final long uuid;
    private final String name;
    private final String description;
    private final String iconUrl;
    private final boolean isCompleted;

}
