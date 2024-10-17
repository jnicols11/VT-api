package vt.digital.api.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vt.digital.api.Models.Product;
import vt.digital.api.Models.Tag;
import vt.digital.api.Repositories.ProductRepository;
import vt.digital.api.Repositories.TagRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TagRepository tagRepository;

    public List<Product> getProductsByCategory(String category) {
        Tag categoryTag = tagRepository.findByName(category);
    
        if (categoryTag == null) {
            throw new RuntimeException("Category '" + category + "' not found");
        }
    
        Integer tagId = categoryTag.getTagId();
        return productRepository.findByProductTags_Tag_TagId(tagId);
    }

    public List<Product> getFeaturedProducts() {
        Tag featuredTag = tagRepository.findByName("featured");
        return productRepository.findByProductTags_Tag_TagId(featuredTag.getTagId());
    }

    public List<Product> filterProductsByTags(List<Integer> tagIds) {
        return productRepository.findByProductTags_Tag_TagIdIn(tagIds);
    }

    public List<Product> searchProducts(String searchTerm) {
        return productRepository.findByNameContaining(searchTerm);
    }

    public Optional<Product> getProductById(Integer productId) {
        return productRepository.findById(productId);
    }
}
