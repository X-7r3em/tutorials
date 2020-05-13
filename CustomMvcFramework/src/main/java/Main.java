import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main {
    public static void main(String[] args) {
        int a = 5;
        Object b = (Object) a;

        System.out.println(b.getClass().getName());
    }

    public static boolean isGetter(Method method) {
        if (Modifier.isPublic(method.getModifiers()) &&
                method.getParameterTypes().length == 0) {
            return method.getName().matches("^(get|is|has)[A-Z].*") &&
                    !method.getReturnType().equals(void.class);
        }

        return false;
    }
}
