package com.runeachievements.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Achievement {

    private long uuid;
    private String name;
    private String description;
    private String iconUrl;
    private boolean isCompleted;

}
