package vt.digital.api.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import vt.digital.api.Models.Product;
import vt.digital.api.Models.ProductImage;
import vt.digital.api.Models.Tag;
import vt.digital.api.Repositories.ProductImageRepository;
import vt.digital.api.Repositories.ProductRepository;
import vt.digital.api.Repositories.TagRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductImageRepository productImageRepository;

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

    public List<Product> getAllProducts() {
        return productRepository.findAll();
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

     public Product createProduct(Product product, List<ProductImage> productImages) {
        Product savedProduct = productRepository.save(product);
        if (productImages != null) {
            for (ProductImage image : productImages) {
                image.setProduct(savedProduct);
                productImageRepository.save(image);
            }
        }
        return savedProduct;
    }

    public Product updateProduct(Product product, List<ProductImage> productImages) {
        Product existingProduct = productRepository.findById(product.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());

        // Remove old images and save new ones if provided
        if (productImages != null && !productImages.isEmpty()) {
            productImageRepository.deleteByProductId(product.getProductId());
            for (ProductImage image : productImages) {
                image.setProduct(existingProduct);
                productImageRepository.save(image);
            }
        }

        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Integer id) {
        productImageRepository.deleteByProductId(id);
        productRepository.deleteById(id);
    }

    public List<ProductImage> convertImagesToEntities(List<MultipartFile> images) throws IOException {
        if (images == null || images.isEmpty()) {
            return new ArrayList<>();
        }
        List<ProductImage> productImages = new ArrayList<>();
        for (MultipartFile image : images) {
            ProductImage productImage = new ProductImage();
            productImage.setImage(image.getBytes());
            productImages.add(productImage);
        }
        return productImages;
    }
}
