package com.example.unittesting.repos;

import org.springframework.stereotype.Component;

@Component
public class NameGeneratorImpl implements NameGenerator {
    public String getName() {
        return "Generated Mock Name";
    }
}
