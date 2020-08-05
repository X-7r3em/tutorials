package org.example.unittestexample.repos;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NameGenerator {

    private final List<String> names;

    public NameGenerator(List<String> names) {
        this.names = names;
    }

    public String getName() {
        return names.get(0);
    }

    public String echo(String echo) {
        return echo;
    }
}
