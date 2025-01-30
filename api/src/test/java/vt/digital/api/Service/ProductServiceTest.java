package vt.digital.api.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;
import vt.digital.api.Models.Product;
import vt.digital.api.Models.ProductImage;
import vt.digital.api.Models.Tag;
import vt.digital.api.Repositories.ProductImageRepository;
import vt.digital.api.Repositories.ProductRepository;
import vt.digital.api.Repositories.TagRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductImageRepository productImageRepository;

    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private ProductService productService;

    private Product sampleProduct;
    private Tag sampleTag;

    @BeforeEach
    void setUp() {
        sampleTag = new Tag(1, "featured", new HashSet<>());
        sampleProduct = Product.builder()
                .productId(1)
                .name("Sample Product")
                .description("Sample Description")
                .price(BigDecimal.valueOf(99.99))
                .quantity(10)
                .productImages(new HashSet<>())
                .build();
    }

    @Test
    void testGetAllProducts() {
        List<Product> products = Arrays.asList(sampleProduct);
        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();
        assertEquals(1, result.size());
        assertEquals("Sample Product", result.get(0).getName());
    }

    @Test
    void testGetFeaturedProducts() {
        when(tagRepository.findByName("featured")).thenReturn(sampleTag);
        when(productRepository.findByProductTags_Tag_TagId(sampleTag.getTagId()))
                .thenReturn(List.of(sampleProduct));

        List<Product> result = productService.getFeaturedProducts();
        assertFalse(result.isEmpty());
        assertEquals("Sample Product", result.get(0).getName());
    }

    @Test
    void testGetProductsByCategory() {
        when(tagRepository.findByName("electronics")).thenReturn(sampleTag);
        when(productRepository.findByProductTags_Tag_TagId(sampleTag.getTagId()))
                .thenReturn(List.of(sampleProduct));

        List<Product> result = productService.getProductsByCategory("electronics");
        assertFalse(result.isEmpty());
        assertEquals("Sample Product", result.get(0).getName());
    }

    @Test
    void testGetProductsByCategory_NotFound() {
        when(tagRepository.findByName("non-existent")).thenReturn(null);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            productService.getProductsByCategory("non-existent");
        });

        assertTrue(exception.getMessage().contains("Category 'non-existent' not found"));
    }

    @Test
    void testSearchProducts() {
        when(productRepository.findByNameContaining("Sample")).thenReturn(List.of(sampleProduct));

        List<Product> result = productService.searchProducts("Sample");
        assertFalse(result.isEmpty());
        assertEquals("Sample Product", result.get(0).getName());
    }

    @Test
    void testFilterProductsByTags() {
        when(productRepository.findByProductTags_Tag_TagIdIn(List.of(1)))
                .thenReturn(List.of(sampleProduct));

        List<Product> result = productService.filterProductsByTags(List.of(1));
        assertFalse(result.isEmpty());
        assertEquals("Sample Product", result.get(0).getName());
    }

    @Test
    void testGetProductById() {
        when(productRepository.findById(1)).thenReturn(Optional.of(sampleProduct));

        Optional<Product> result = productService.getProductById(1);
        assertTrue(result.isPresent());
        assertEquals("Sample Product", result.get().getName());
    }

    @Test
    void testCreateProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(sampleProduct);
        List<ProductImage> images = new ArrayList<>();

        Product result = productService.createProduct(sampleProduct, images);
        assertNotNull(result);
        assertEquals("Sample Product", result.getName());
    }

    @Test
    void testUpdateProduct() {
        when(productRepository.findById(sampleProduct.getProductId())).thenReturn(Optional.of(sampleProduct));
        when(productRepository.save(any(Product.class))).thenReturn(sampleProduct);

        Product updatedProduct = productService.updateProduct(sampleProduct, new ArrayList<>());
        assertNotNull(updatedProduct);
        assertEquals("Sample Product", updatedProduct.getName());
    }

    @Test
    void testDeleteProduct() {
        doNothing().when(productImageRepository).deleteByProductId(sampleProduct.getProductId());
        doNothing().when(productRepository).deleteById(sampleProduct.getProductId());

        assertDoesNotThrow(() -> productService.deleteProduct(sampleProduct.getProductId()));
        verify(productImageRepository, times(1)).deleteByProductId(sampleProduct.getProductId());
        verify(productRepository, times(1)).deleteById(sampleProduct.getProductId());
    }

    @Test
    void testConvertImagesToEntities() throws IOException {
        MultipartFile mockFile = mock(MultipartFile.class);
        when(mockFile.getBytes()).thenReturn(new byte[]{1, 2, 3});

        List<MultipartFile> images = List.of(mockFile);
        List<ProductImage> result = productService.convertImagesToEntities(images);

        assertFalse(result.isEmpty());
        assertNotNull(result.get(0).getImage());
    }
}