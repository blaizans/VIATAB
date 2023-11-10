package com.example.viatab.Controllers;

import com.example.viatab.Models.Story;
import com.example.viatab.Services.StoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(description = "",name = "Animals Resource")
@RestController
@RequestMapping("api/story")
public class StoryController {
    private final StoryService storyService;

    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    @Operation(summary  = "Get a story by id", description = "Returns a story by the id")
    @GetMapping("/{id}")
    public Story getStoryById(@PathVariable("id") Long id){
        return storyService.findStoryById(id);
    }

    @Operation(summary  = "Add a story", description = "Add a story")
    @PostMapping("/")
    public Story saveStory(@RequestBody Story story){
        return storyService.saveStory(story);
    }

    @Operation(summary  = "Update a story", description = "Update a story")
    @PutMapping("{id}")
    public Story saveStory(@PathVariable("id") Long id, @RequestBody Story story){
        Story s = storyService.findStoryById(id);
        s.setName(story.getName());
        s.setDescription(story.getDescription());
        s.setDate(story.getDate());
        s.setPublisher(story.getPublisher());
        return storyService.saveStory(s);
    }

    @Operation(summary  = "Delete a story", description = "Delete a story")
    @DeleteMapping("/{id}")
    public void deleteStory(@PathVariable("id") Long id){
        storyService.deleteStoryById(id);
    }

    @Operation(summary  = "Get all stories", description = "Get all stories")
    @GetMapping("/")
    public List<Story> getAll(){
        return storyService.getAll();
    }
}