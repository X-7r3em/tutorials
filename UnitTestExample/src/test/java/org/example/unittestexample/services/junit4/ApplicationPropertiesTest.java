package org.example.unittestexample.services.junit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
//Uses a different application.properties file, as a different value may be needed.
//We can also just use the application.properties in the resources of the tests
@TestPropertySource(locations="classpath:test.properties")
public class ApplicationPropertiesTest {

    @Value(value = "${fakeRoute}")
    private String fakeRoute;

    @Test
    public void testRoute() {
        Assert.assertEquals("FAKE_ROUTE_FROM_TESTING_PROPERTY", fakeRoute);
    }
}
