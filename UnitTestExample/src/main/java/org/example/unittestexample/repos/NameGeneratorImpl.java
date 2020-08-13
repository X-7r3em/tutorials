package org.example.unittestexample.repos;

import org.springframework.stereotype.Component;

@Component
public class NameGeneratorImpl implements NameGenerator {
    public String getName() {
        return "Generated Mock Name";
    }
}
