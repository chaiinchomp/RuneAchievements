package com.runeachievements.api;

import com.runeachievements.domain.AchievementsController;
import com.runeachievements.model.Achievement;
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
    public ResponseEntity<List<Achievement>> achievement() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<>(controller.loadAchievements(), headers, HttpStatus.OK);
    }

}
