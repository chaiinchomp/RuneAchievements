package com.runeachievements.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AchievementCriteria implements Criteria {

    private String achievementId;
    private String name;
    private String description;

}
