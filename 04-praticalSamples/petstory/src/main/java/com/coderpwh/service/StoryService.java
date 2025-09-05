package com.coderpwh.service;


import org.springframework.stereotype.Service;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.chat.completions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;


/**
 * @author coderpwh
 */
@Service
public class StoryService {


    private static final Logger logger = LoggerFactory.getLogger(StoryService.class);
    private final OpenAIClient openAIClient;
    private final String modelName;


    /**
     * 构造函数
     * @param endpoint
     * @param modelName
     */
    public StoryService(@Value("${github.models.endpoint:https://models.github.ai/inference}") String endpoint,
                        @Value("${github.models.model:openai/gpt-4.1-nano}") String modelName) {

        this.modelName = modelName;

        String githubToken = "";

        if (githubToken == null || githubToken.isBlank()) {
            throw new IllegalStateException("GITHUB_TOKEN environment variable must be set with models:read scope");
        }

        this.openAIClient = OpenAIOkHttpClient.builder()
                .baseUrl(endpoint)
                .apiKey(githubToken)
                .build();

        logger.info("StoryService initialized with GitHub Models endpoint: {} and model: {}", endpoint, modelName);
    }


    /***
     * 生成故事
     * @param description
     * @return
     */
    public String generateStory(String description){

        if(description==null||description.trim().isEmpty()){
            logger.error("Empty or null description provided");
            throw new IllegalArgumentException("Description cannot be empty");
        }

        if(description.length()>1000){
            logger.error("Description too long, truncating: {} characters", description.length());
            description = description.substring(0, 1000);
        }


        try {
            logger.info("Generating story for description: {}", description);
             // 系统提示词
            String systemPrompt = """
                    您是一位富有创意的故事家，专为全家创作妙趣横生的宠物主题短篇故事。
                    故事字数请限制在500字以内，并确保内容老少皆宜。
                    """;

            String userPrompt = "请根据以下描述，写一个趣味横生的宠物主角短篇故事:"+description;


            ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
                    .model(modelName)
                    .addSystemMessage(systemPrompt)
                    .addUserMessage(userPrompt)
                    .maxTokens(800)
                    .temperature(0.8)
                    .build();

            logger.info("Sending request to GitHub Models for story generation");

            ChatCompletion response = openAIClient.chat().completions().create(params);

            if (response.choices().isEmpty()) {
                logger.error("Empty response received from GitHub Models");
                throw new RuntimeException("Empty response from GitHub Models service");
            }

            String result = response.choices().get(0).message().content().orElse("");


            if (result.trim().isEmpty()) {
                logger.error("Empty content received from GitHub Models");
                throw new RuntimeException("Empty content from GitHub Models service");
            }

            logger.info("Generated story of length: {}", result.length());
            return result.trim();
        }catch (Exception e){
            logger.error("Error generating story for description: {}", description, e);
            throw new RuntimeException("Failed to generate story: " + e.getMessage(), e);
        }
    }


}
