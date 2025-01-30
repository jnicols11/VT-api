package vt.digital.api.Models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

class TagTest {

    @Test
    void testTagCreation() {
        Tag tag = Tag.builder()
                .tagId(1)
                .name("Best Seller")
                .build();

        assertNotNull(tag);
        assertEquals(1, tag.getTagId());
        assertEquals("Best Seller", tag.getName());
    }

    @Test
    void testTagSetters() {
        Tag tag = new Tag();

        tag.setTagId(2);
        tag.setName("Limited Edition");

        assertEquals(2, tag.getTagId());
        assertEquals("Limited Edition", tag.getName());
    }

    @Test
    void testTagWithProductTags() {
        Tag tag = new Tag();
        tag.setTagId(3);
        tag.setName("Exclusive");

        Product product = new Product();
        product.setProductId(1);
        product.setName("Test Product");

        ProductTag productTag = new ProductTag();
        productTag.setProductTagId(1);
        productTag.setProduct(product);
        productTag.setTag(tag);

        Set<ProductTag> productTags = new HashSet<>();
        productTags.add(productTag);
        tag.setProductTags(productTags);

        assertNotNull(tag.getProductTags());
        assertEquals(1, tag.getProductTags().size());
        assertEquals(productTag, tag.getProductTags().iterator().next());
    }
}