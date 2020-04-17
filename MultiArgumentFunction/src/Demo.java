import util.HexaFunction;
import util.PentaFunction;

import java.util.List;
import java.util.function.Function;

public class Demo {

    public static void main(String[] args) {
        /**
         This is allowed, because it is code sugar for the implementation of a function.
         In other words, we are creating an anonymous function from the interface, as if we called it with new.
         */
        HexaFunction<String, Integer, Double, Void, List<Float>, Character> hexaFunction = (a, b, c, d, e) -> 'z';
        System.out.println(hexaFunction.apply(null, null, null, null, null));

        /**
         Example when we are creating a function with the new operator and an object util.Function2, which, we have to
         implement, because it is an interface.
         */
        PentaFunction<String, Integer, Double, Void, Character> pentaFunction = new PentaFunction<String, Integer, Double, Void, Character>() {
            @Override
            public Character apply(String a, Integer b, Double c, Void d) {
                return 'b';
            }
        };
        System.out.println(pentaFunction.apply(null, null, null, null));

        /**
         The two examples bellow are the same. The first shows that even the standard Function,
         provided in the Java Libraries, is an interface, and it creates an anonymous class and
         implements it.
         Bellow that is the lambda syntactic sugar.
         */
        Function<String, Integer> function = new Function<String, Integer>() {
            @Override
            public Integer apply(String a) {
                return Integer.parseInt(a);
            }
        };

        Function<String, Integer> functionWithCodeSugar = a -> Integer.parseInt(a + "B");
    }
}
