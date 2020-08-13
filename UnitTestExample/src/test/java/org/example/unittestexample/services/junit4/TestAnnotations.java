package org.example.unittestexample.services.junit4;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestAnnotations {
    private static final int[] before = new int[1];
    private static final int[] beforeClass = new int[1];

    // Is done before every test. Equal to @BeforeEach
    @Before
    public void beforeEach() {
        before[0] = 10;
    }

    // Is done once before all the tests. Equal to @BeforeAll
    @BeforeClass
    public static void beforeClass() {
        beforeClass[0] = 10;
    }

    @Test
    public void beforeFirstTest() {
        before[0] += 100;
        assertEquals(110, before[0]);
    }

    @Test
    public void beforeSecondTest() {
        before[0] += 25;
        assertEquals(35, before[0]);
    }

    @Test
    public void beforeClassFirstTest() {
        beforeClass[0] += 100;
        assertEquals(110, beforeClass[0]);
    }

    @Test
    public void beforeClassClassSecondTest() {
        beforeClass[0] += 25;
        assertEquals(135, beforeClass[0]);
    }
}
