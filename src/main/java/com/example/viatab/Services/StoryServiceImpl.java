package com.example.viatab.Services;

import com.example.viatab.Models.Story;
import com.example.viatab.Repository.StoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoryServiceImpl implements StoryService {
    private final StoryRepository storyRepository;

    public StoryServiceImpl(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    @Override
    public Story findStoryById(Long id) {
        return storyRepository.findById(id).get();
    }

    @Override
    public Story saveStory(Story story) {
        return storyRepository.save(story);
    }

    @Override
    public Story updateStory(Story story) {
        return storyRepository.save(story);
    }

    @Override
    public void deleteStoryById(Long id) {
        storyRepository.deleteById(id);
    }

    @Override
    public List<Story> getAll() {
        return storyRepository.findAll();
    }
}
