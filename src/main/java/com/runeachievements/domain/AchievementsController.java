package com.runeachievements.domain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.runeachievements.model.Achievement;
import com.runeachievements.model.Category;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AchievementsController {

    private static final Map<Category,String> ACHIEVEMENT_DATA = ImmutableMap.<Category, String>builder()
            .put(Category.BOSSES, "achievementData_bosses.json")
            .put(Category.CHARACTER, "achievementData_character.json")
            .put(Category.CLUES, "achievementData_clues.json")
            .put(Category.DIARIES, "achievementData_diaries.json")
            .put(Category.EVENTS, "achievementData_events.json")
            .put(Category.MINIGAMES, "achievementData_minigames.json")
            .put(Category.COMBAT, "achievementData_combat.json")
            .put(Category.OTHER, "achievementData_other.json")
            .put(Category.PETS, "achievementData_pets.json")
            .put(Category.SKILLS, "achievementData_skills.json")
            .put(Category.QUESTS, "achievementData_quests.json")
            .build();

    private final ObjectMapper mapper;

    public List<Achievement> loadAchievements() {
        List<Achievement> achievements = new ArrayList<>();
        for (Category category : Category.values()) {
            achievements.addAll(loadAchievements(category));
        }
        return achievements;
    }

    public List<Achievement> loadAchievements(final Category category) {
        try {
            InputStream achievementData =
                    getClass().getClassLoader().getResourceAsStream(ACHIEVEMENT_DATA.get(category));
            return mapper.readValue(achievementData, new TypeReference<List<Achievement>>(){});
        } catch (Exception e) {
            throw new InternalException("Failed to load achievements: " + e);
        }
    }

}
