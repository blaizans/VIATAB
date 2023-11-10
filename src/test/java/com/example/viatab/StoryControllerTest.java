package com.example.viatab;

import com.example.viatab.Controllers.StoryController;
import com.example.viatab.Models.Story;
import com.example.viatab.Services.StoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class StoryControllerTest {

    @Mock
    private StoryService storyService;

    @InjectMocks
    private StoryController storyController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(storyController).build();
    }

    @Test
    void testGetStoryById() throws Exception {
        Story mockStory = new Story(1L, "Test Story", "Test Description", "Test Publisher", new Date());

        when(storyService.findStoryById(1L)).thenReturn(mockStory);

        mockMvc.perform(get("/api/story/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Story"))
                .andExpect(jsonPath("$.description").value("Test Description"))
                .andExpect(jsonPath("$.publisher").value("Test Publisher"));

        verify(storyService, times(1)).findStoryById(1L);
    }

    @Test
    void testSaveStory() throws Exception {
        Story inputStory = new Story(null, "Test Story", "Test Description", "Test Publisher", new Date());
        Story outputStory = new Story(1L, "Test Story", "Test Description", "Test Publisher", new Date());

        when(storyService.saveStory(any(Story.class))).thenReturn(outputStory);

        mockMvc.perform(post("/api/story/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Test Story\",\"description\":\"Test Description\",\"publisher\":\"Test Publisher\",\"date\":\"2023-11-10T12:00:00\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Story"))
                .andExpect(jsonPath("$.description").value("Test Description"))
                .andExpect(jsonPath("$.publisher").value("Test Publisher"));

        verify(storyService, times(1)).saveStory(any(Story.class));
    }

    @Test
    void testUpdateStory() throws Exception {
        Story existingStory = new Story(1L, "Existing Story", "Description", "Publisher", new Date());
        Story updatedStory = new Story(1L, "Updated Story", "Updated Description", "Updated Publisher", new Date());

        when(storyService.findStoryById(1L)).thenReturn(existingStory);
        when(storyService.saveStory(any(Story.class))).thenReturn(updatedStory);

        mockMvc.perform(put("/api/story/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated Story\",\"description\":\"Updated Description\",\"publisher\":\"Updated Publisher\",\"date\":\"2023-11-10T12:00:00\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Story"))
                .andExpect(jsonPath("$.description").value("Updated Description"))
                .andExpect(jsonPath("$.publisher").value("Updated Publisher"));

        verify(storyService, times(1)).findStoryById(1L);
        verify(storyService, times(1)).saveStory(any(Story.class));
    }

    @Test
    void testDeleteStory() throws Exception {
        mockMvc.perform(delete("/api/story/1"))
                .andExpect(status().isOk());

        verify(storyService, times(1)).deleteStoryById(1L);
    }

    @Test
    void testGetAllStories() throws Exception {
        List<Story> stories = Arrays.asList(
                new Story(1L, "Story 1", "Description 1", "Publisher 1", new Date()),
                new Story(2L, "Story 2", "Description 2", "Publisher 2", new Date())
        );

        when(storyService.getAll()).thenReturn(stories);

        mockMvc.perform(get("/api/story/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Story 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Story 2"));

        verify(storyService, times(1)).getAll();
    }
}
