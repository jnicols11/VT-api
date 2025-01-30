package vt.digital.api.Controllers;

import vt.digital.api.Models.Product;
import vt.digital.api.Models.ProductImage;
import vt.digital.api.Service.ProductService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private Product sampleProduct;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        sampleProduct = new Product();
        sampleProduct.setProductId(1);
        sampleProduct.setName("Sample Product");
        sampleProduct.setDescription("This is a sample product");
        sampleProduct.setPrice(BigDecimal.valueOf(19.99));
        sampleProduct.setQuantity(10);
    }

    @Test
    void getAllProducts_ShouldReturnListOfProducts() {
        when(productService.getAllProducts()).thenReturn(List.of(sampleProduct));

        ResponseEntity<List<Product>> response = productController.getAllProducts();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void getFeaturedProducts_ShouldReturnFeaturedProducts() {
        when(productService.getFeaturedProducts()).thenReturn(List.of(sampleProduct));

        ResponseEntity<List<Product>> response = productController.getFeaturedProducts();

        assertEquals(200, response.getStatusCodeValue());
        assertFalse(response.getBody().isEmpty());
        verify(productService, times(1)).getFeaturedProducts();
    }

    @Test
    void getProductsByCategory_ShouldReturnProducts() {
        String category = "Electronics";
        when(productService.getProductsByCategory(category)).thenReturn(List.of(sampleProduct));

        ResponseEntity<List<Product>> response = productController.getProductsByCategory(category);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(productService, times(1)).getProductsByCategory(category);
    }

    @Test
    void searchProducts_ShouldReturnMatchingProducts() {
        String query = "Sample";
        when(productService.searchProducts(query)).thenReturn(List.of(sampleProduct));

        ResponseEntity<List<Product>> response = productController.searchProducts(query);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(productService, times(1)).searchProducts(query);
    }

    @Test
    void getProductById_ShouldReturnProduct_WhenFound() {
        when(productService.getProductById(1)).thenReturn(Optional.of(sampleProduct));

        ResponseEntity<Product> response = productController.getProductById(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(sampleProduct.getName(), response.getBody().getName());
        verify(productService, times(1)).getProductById(1);
    }

    @Test
    void getProductById_ShouldReturnNotFound_WhenProductDoesNotExist() {
        when(productService.getProductById(1)).thenReturn(Optional.empty());

        ResponseEntity<Product> response = productController.getProductById(1);

        assertEquals(404, response.getStatusCodeValue());
        verify(productService, times(1)).getProductById(1);
    }

    @Test
    void filterProductsByTags_ShouldReturnFilteredProducts() {
        List<Integer> tagIds = Arrays.asList(1, 2, 3);
        when(productService.filterProductsByTags(tagIds)).thenReturn(List.of(sampleProduct));

        ResponseEntity<List<Product>> response = productController.filterProductsByTags(tagIds);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(productService, times(1)).filterProductsByTags(tagIds);
    }

    @Test
    void createProduct_ShouldReturnCreatedProduct() throws Exception {
        MockMultipartFile image = new MockMultipartFile("image", "test.jpg", "image/jpeg", new byte[10]);
        List<ProductImage> productImages = List.of(new ProductImage());

        when(productService.convertImagesToEntities(any())).thenReturn(productImages);
        when(productService.createProduct(any(Product.class), any())).thenReturn(sampleProduct);

        ResponseEntity<Product> response = productController.createProduct(
                "Sample Product", "Description", BigDecimal.valueOf(19.99), 10, List.of(image));

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Sample Product", response.getBody().getName());
        verify(productService, times(1)).createProduct(any(Product.class), any());
    }

    @Test
    void updateProduct_ShouldReturnUpdatedProduct() throws Exception {
        MockMultipartFile image = new MockMultipartFile("image", "test.jpg", "image/jpeg", new byte[10]);
        List<ProductImage> productImages = List.of(new ProductImage());

        when(productService.convertImagesToEntities(any())).thenReturn(productImages);
        when(productService.updateProduct(any(Product.class), any())).thenReturn(sampleProduct);

        ResponseEntity<Product> response = productController.updateProduct(
                1, "Updated Name", "Updated Description", BigDecimal.valueOf(29.99), 5, List.of(image));

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Sample Product", response.getBody().getName());
        verify(productService, times(1)).updateProduct(any(Product.class), any());
    }

    @Test
    void deleteProduct_ShouldReturnNoContent() {
        doNothing().when(productService).deleteProduct(1);

        ResponseEntity<Void> response = productController.deleteProduct(1);

        assertEquals(204, response.getStatusCodeValue());
        verify(productService, times(1)).deleteProduct(1);
    }
}