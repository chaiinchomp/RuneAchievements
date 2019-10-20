package com.runeachievements.domain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.runeachievements.model.Achievement;
import java.io.File;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AchievementsController {

    private static final String ACHIEVEMENT_DATA = "achievementData.json";

    private final ObjectMapper mapper;

    public List<Achievement> loadAchievements() {
        try {
            File achievementData = new File(getClass().getClassLoader().getResource(ACHIEVEMENT_DATA).getFile());
            return mapper.readValue(achievementData, new TypeReference<List<Achievement>>(){});
        } catch (Exception e) {
            // very bad no good lack of error handling
            System.out.println(e.toString());
            return Collections.emptyList();
        }
    }

}