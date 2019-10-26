package com.runeachievements.api;

import com.runeachievements.domain.AchievementsController;
import com.runeachievements.model.Achievement;
import com.runeachievements.model.Category;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AchievementsApi {

    private final AchievementsController controller;

    @GetMapping("/achievement")
    public ResponseEntity<List<Achievement>> getAchievements() {
        return configureResponseEntity(controller.loadAchievements());
    }

    @GetMapping("/achievement/category")
    public ResponseEntity<List<Achievement>> getAchievementsByCategory(final Category category) {
        return configureResponseEntity(controller.loadAchievements(category));
    }

    private <T> ResponseEntity<T> configureResponseEntity(final T responseContent) {
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<>(responseContent, headers, HttpStatus.OK);
    }

}
