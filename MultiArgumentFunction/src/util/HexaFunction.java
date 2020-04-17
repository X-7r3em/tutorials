package util;

@FunctionalInterface
public interface HexaFunction<One, Two, Three, Four, Five, Six> {
    public Six apply(One one, Two two, Three three, Four four, Five five);
}

