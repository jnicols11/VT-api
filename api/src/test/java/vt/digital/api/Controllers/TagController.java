package vt.digital.api.Controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import vt.digital.api.Models.Tag;
import vt.digital.api.Service.TagService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TagControllerTest {

    @Mock
    private TagService tagService;

    @InjectMocks
    private TagController tagController;

    private Tag tag1;
    private Tag tag2;

    @BeforeEach
    void setUp() {
        tag1 = new Tag(1, "Men", null);
        tag2 = new Tag(2, "Women", null);
    }

    @Test
    void getAllTags_ShouldReturnTagList() {
        // Arrange
        List<Tag> tags = Arrays.asList(tag1, tag2);
        when(tagService.getAllTags()).thenReturn(tags);

        // Act
        ResponseEntity<List<Tag>> response = tagController.getAllTags();

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        assertEquals("Men", response.getBody().get(0).getName());

        verify(tagService, times(1)).getAllTags();
    }

    @Test
    void getTagByName_ShouldReturnTag() {
        // Arrange
        when(tagService.getTagByName("Men")).thenReturn(tag1);

        // Act
        ResponseEntity<Tag> response = tagController.getTagByName("Men");

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Men", response.getBody().getName());

        verify(tagService, times(1)).getTagByName("Men");
    }

    @Test
    void getTagByName_ShouldReturnNull_WhenTagNotFound() {
        // Arrange
        when(tagService.getTagByName("NonExistentTag")).thenReturn(null);

        // Act
        ResponseEntity<Tag> response = tagController.getTagByName("NonExistentTag");

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNull(response.getBody());

        verify(tagService, times(1)).getTagByName("NonExistentTag");
    }
}