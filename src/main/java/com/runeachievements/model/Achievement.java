package com.runeachievements.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Achievement {

    private String uuid;
    private String name;
    private String description;
    private String iconUrl;
    private Series series;
    private int seriesOrdinal;
    private SubtaskCriteria subtaskCriteria;
    private NumericCriteria numericCriteria;
    private MetaCriteria metaCriteria;

}
