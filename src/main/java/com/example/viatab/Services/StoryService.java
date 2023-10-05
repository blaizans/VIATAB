package com.example.viatab.Services;

import com.example.viatab.Models.Story;

import java.util.List;

public interface StoryService {
    Story findStoryById(Long id);
    Story saveStory(Story story);
    Story updateStory(Story story);
    void deleteStoryById(Long id);
    List<Story> getAll();
}
