package com.qa.ims.persistence.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class OrderTest {

    @Test
    public void testEquals() {
        EqualsVerifier.simple().forClass(Order.class).verify();
    }
}
