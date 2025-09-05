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


    /***
     * 输入内容进行过滤
     * @param input
     * @return
     */
    private  String sanitizeInput(String input){
        if(input == null){
            return  "";
        }

        String sanitized = input.replaceAll("[<>\"'&]", "").trim();
        return sanitized.substring(0, Math.min(sanitized.length(), 1000));
    }


    private  String generateFallbackStory(String description){

        String[] storyTemplates = {
                "Meet the most wonderful pet in the world – a furry ball of energy and love who brings happiness wherever they go! This amazing companion starts each day with an enthusiastic tail wag and is always ready for the next adventure. Whether it's playing in the backyard, going for walks in the neighborhood, or simply enjoying belly rubs, this pet approaches life with boundless enthusiasm and loyalty. They have a special gift for making everyone smile with their playful antics and gentle spirit. At the end of each day, this faithful friend curls up nearby, content knowing they've spread joy and been the best companion anyone could ask for.",

                "Once upon a time, there lived a remarkable pet whose heart was as big as their personality! Every morning brought new possibilities for fun and friendship. This special companion had a talent for turning ordinary moments into extraordinary memories. From chasing butterflies in the garden to greeting visitors with unbridled excitement, they filled every day with laughter and love. Their favorite activities included exploring new scents, playing with favorite toys, and sharing quiet moments with their beloved family. This wonderful pet reminded everyone around them that the simple joys in life – a warm sunny spot, a gentle pat, and unconditional love – are truly the most precious gifts of all.",

                "In a cozy home filled with love, there lived an extraordinary pet who was everyone's best friend. This delightful companion had a way of brightening even the cloudiest days with their infectious enthusiasm and loving nature. Adventures were their specialty – whether discovering new corners of the house, meeting neighborhood friends, or simply enjoying peaceful naps in favorite spots. Their playful spirit and gentle heart made them the perfect family member, always ready to offer comfort when needed and celebrate life's happy moments. This special pet showed everyone that true friendship knows no bounds and that every day is better when shared with someone who loves you unconditionally."
        };

        // Use description hash for consistent responses
        int index = Math.abs(description.hashCode() % storyTemplates.length);
        return storyTemplates[index];
    }





}
