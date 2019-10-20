package com.runeachievements.controller;

import com.runeachievements.model.Achievement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AchievementsController {

    @RequestMapping("/achievement")
    public Achievement achievement() {
        return new Achievement(1, "Name", "Description", "url", false);
    }

}
