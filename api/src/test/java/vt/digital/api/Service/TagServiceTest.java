package vt.digital.api.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import vt.digital.api.Models.Tag;
import vt.digital.api.Repositories.TagRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TagServiceTest {

    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private TagService tagService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetTagByName() {
        Tag tag = new Tag();
        tag.setTagId(1);
        tag.setName("featured");

        when(tagRepository.findByName("featured")).thenReturn(tag);

        Tag result = tagService.getTagByName("featured");

        assertNotNull(result);
        assertEquals("featured", result.getName());
    }
}
