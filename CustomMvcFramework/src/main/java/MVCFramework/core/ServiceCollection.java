package MVCFramework.core;

public interface ServiceCollection {
    void add(Class<?> source, Class<?> destination);

    Object createInstance(Class<?> type);
}
