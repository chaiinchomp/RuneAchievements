package com.runeachievements.api;

import com.runeachievements.domain.AchievementsController;
import com.runeachievements.model.Achievement;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AchievementsApi {

    private final AchievementsController controller;

    @GetMapping("/achievement")
    public List<Achievement> achievement() {
        return controller.loadAchievements();
    }

}
