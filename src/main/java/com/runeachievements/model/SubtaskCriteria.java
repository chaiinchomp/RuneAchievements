package com.runeachievements.model;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubtaskCriteria implements Criteria {

    private int requiredCount;
    private List<SimpleCriteria> subtasks;

}
