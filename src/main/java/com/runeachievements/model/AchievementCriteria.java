package com.runeachievements.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AchievementCriteria implements Criteria {

    private String uuid;
    private String name;
    private String description;

}
