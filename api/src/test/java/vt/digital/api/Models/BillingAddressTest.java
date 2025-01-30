package vt.digital.api.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BillingAddressTest {

    private BillingAddress billingAddress;

    @BeforeEach
    void setUp() {
        User user = new User(); // Mock user object
        user.setUserId(1);

        billingAddress = BillingAddress.builder()
                .billingAddressId(1)
                .street("123 Main St")
                .city("Springfield")
                .state("IL")
                .country("USA")
                .zipCode("62704")
                .user(user)
                .build();
    }

    @Test
    void testBillingAddressCreation() {
        assertNotNull(billingAddress);
        assertEquals(1, billingAddress.getBillingAddressId());
        assertEquals("123 Main St", billingAddress.getStreet());
        assertEquals("Springfield", billingAddress.getCity());
        assertEquals("IL", billingAddress.getState());
        assertEquals("USA", billingAddress.getCountry());
        assertEquals("62704", billingAddress.getZipCode());
        assertNotNull(billingAddress.getUser());
        assertEquals(1, billingAddress.getUser().getUserId());
    }

    @Test
    void testSettersAndGetters() {
        billingAddress.setStreet("456 Elm St");
        assertEquals("456 Elm St", billingAddress.getStreet());

        billingAddress.setCity("Chicago");
        assertEquals("Chicago", billingAddress.getCity());

        billingAddress.setState("CA");
        assertEquals("CA", billingAddress.getState());

        billingAddress.setCountry("Canada");
        assertEquals("Canada", billingAddress.getCountry());

        billingAddress.setZipCode("90001");
        assertEquals("90001", billingAddress.getZipCode());
    }

    @Test
    void testEqualsAndHashCode() {
        User user = new User();
        user.setUserId(1);

        BillingAddress sameBillingAddress = BillingAddress.builder()
                .billingAddressId(1)
                .street("123 Main St")
                .city("Springfield")
                .state("IL")
                .country("USA")
                .zipCode("62704")
                .user(user)
                .build();

        assertEquals(billingAddress, sameBillingAddress);
        assertEquals(billingAddress.hashCode(), sameBillingAddress.hashCode());
    }

    @Test
    void testBillingAddressBuilder() {
        BillingAddress newAddress = BillingAddress.builder()
                .billingAddressId(2)
                .street("789 Pine St")
                .city("Seattle")
                .state("WA")
                .country("USA")
                .zipCode("98101")
                .user(new User())
                .build();

        assertNotNull(newAddress);
        assertEquals("789 Pine St", newAddress.getStreet());
        assertEquals("Seattle", newAddress.getCity());
        assertEquals("WA", newAddress.getState());
        assertEquals("USA", newAddress.getCountry());
        assertEquals("98101", newAddress.getZipCode());
        assertNotNull(newAddress.getUser());
    }
}