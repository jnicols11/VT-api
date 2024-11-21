package vt.digital.api.Controllers;

import vt.digital.api.Models.Product;
import vt.digital.api.Models.ProductImage;
import vt.digital.api.Service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/featured")
    public ResponseEntity<List<Product>> getFeaturedProducts() {
        List<Product> featuredProducts = productService.getFeaturedProducts();
        return ResponseEntity.ok(featuredProducts);
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String categoryName) {
        List<Product> products = productService.getProductsByCategory(categoryName);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String query) {
        List<Product> products = productService.searchProducts(query);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer productId) {
        Optional<Product> product = productService.getProductById(productId);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/filter")
    public ResponseEntity<List<Product>> filterProductsByTags(@RequestBody List<Integer> tagIds) {
        List<Product> products = productService.filterProductsByTags(tagIds);
        return ResponseEntity.ok(products);
    }

    @PostMapping("/")
    public ResponseEntity<Product> createProduct(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam BigDecimal price,
            @RequestParam Integer quantity,
            @RequestParam(value = "images", required = false) List<MultipartFile> images) throws IOException {

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantity(quantity);

        List<ProductImage> productImages = productService.convertImagesToEntities(images);
        Product createdProduct = productService.createProduct(product, productImages);
        return ResponseEntity.ok(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Integer id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam BigDecimal price,
            @RequestParam Integer quantity,
            @RequestParam(value = "images", required = false) List<MultipartFile> images) throws IOException {

        Product product = new Product();
        product.setProductId(id);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantity(quantity);

        List<ProductImage> productImages = productService.convertImagesToEntities(images);
        Product updatedProduct = productService.updateProduct(product, productImages);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
