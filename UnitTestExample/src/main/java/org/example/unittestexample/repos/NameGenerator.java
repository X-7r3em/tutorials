package org.example.unittestexample.repos;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class NameGenerator {
    private static List<String> names = Arrays.asList("Tom");

    public String getName() {
        return names.get(0);
    }
}
