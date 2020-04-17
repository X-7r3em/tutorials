package MVCFramework.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class ServiceCollectionImpl implements ServiceCollection {
    private final Map<Class<?>, Class<?>> dependencyContainer = new ConcurrentHashMap<>();

    @Override
    public void add(Class<?> source, Class<?> destination) {
        dependencyContainer.put(source, destination);
    }

    @Override
    public Object createInstance(Class<?> type) {
        try {
            if (dependencyContainer.containsKey(type)) {
                type = dependencyContainer.get(type);
            }

            Constructor<?> constructor = null;
            Constructor<?>[] constructors = type.getConstructors();
            Arrays.sort(constructors, Comparator.comparingInt(Constructor::getParameterCount));
            for (Constructor<?> ctr : constructors) {
                boolean aPublic = Modifier.isPublic(ctr.getModifiers());
                boolean aStatic = Modifier.isStatic(ctr.getModifiers());
                if (aPublic && !aStatic) {
                    constructor = ctr;
                    break;
                }
            }

            if (constructor == null) {
                constructor = type.getConstructor();
            }

            List<Object> parameterValues = new ArrayList<>();
            for (Parameter parameterType : constructor.getParameters()) {
                Object instance = createInstance(parameterType.getType());
                parameterValues.add(instance);
            }

            return constructor.newInstance(parameterValues.toArray());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }
}
