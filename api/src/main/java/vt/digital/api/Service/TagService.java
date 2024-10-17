package vt.digital.api.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vt.digital.api.Models.Tag;
import vt.digital.api.Repositories.TagRepository;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }
}
