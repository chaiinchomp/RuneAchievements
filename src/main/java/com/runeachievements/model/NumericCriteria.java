package com.runeachievements.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NumericCriteria implements Criteria {

    private String taskId;
    private String name;
    private String iconUrl;
    private int count;

}
