package com.qa.ims.persistence.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class ProductTest {

    @Test
    public void testEquals() {
        EqualsVerifier.simple().forClass(Product.class).verify();
    }
}
