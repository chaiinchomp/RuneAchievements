package com.runeachievements.domain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.runeachievements.model.Achievement;
import java.io.File;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AchievementsController {

    private static final String ACHIEVEMENT_DATA = "achievementData.json";

    private final ObjectMapper mapper;

    public List<Achievement> loadAchievements() {
        try {
            InputStream achievementData = getClass().getClassLoader().getResourceAsStream(ACHIEVEMENT_DATA);
            return mapper.readValue(achievementData, new TypeReference<List<Achievement>>(){});
        } catch (Exception e) {
            throw new InternalException("Failed to load achievements: " + e);
        }
    }

}
