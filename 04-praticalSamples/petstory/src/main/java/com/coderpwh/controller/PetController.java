package com.coderpwh.controller;

import com.coderpwh.service.StoryService;
import org.springframework.stereotype.Controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.constraints.NotNull;

/**
 * @author coderpwh
 */
@Controller
public class PetController {


    private static final Logger logger = LoggerFactory.getLogger(PetController.class);

    private final StoryService storyService;

    public PetController(StoryService storyService) {
        this.storyService = storyService;
    }


    @GetMapping("/")
    public String index() {
        logger.info("Accessing index page");
        return "index";
    }


    public String generateStory(@RequestParam("description") @NotNull String description,
                                Model model) {
        logger.info("Generating story for description: {}", description);
        String story = storyService.generateStory(description);
        model.addAttribute("story", story);
        return "story";
    }



}
