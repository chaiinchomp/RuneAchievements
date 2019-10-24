package com.runeachievements.model;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MetaCriteria implements Criteria {

    private List<AchievementCriteria> subtasks;

}
