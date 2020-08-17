import annotations.Child;
import annotations.GrandParent;
import annotations.Parent;

import java.lang.annotation.Annotation;

/**
 * Example for getting the annotations of an annotation of a class.
 */
public class Main {
    public static void main(String[] args) {
        Car car = new Car();
        Child childAnnotation = car.getClass().getAnnotation(Child.class);
        System.out.println(childAnnotation.name());

        // With annotationType() we get the Type of the annotation as we would with getClass()
        Parent parentAnnotation = childAnnotation.annotationType().getAnnotation(Parent.class);
        System.out.println(parentAnnotation.name());

        GrandParent grandParent = parentAnnotation.annotationType().getAnnotation(GrandParent.class);
        System.out.println(grandParent.name());

        // If we have Annotation[] then we have to downcast to GrandParent
        Annotation[] allParentAnnotations = parentAnnotation.annotationType().getAnnotations();
        GrandParent grandParentFromArray = (GrandParent) allParentAnnotations[2];
        System.out.println(grandParentFromArray.name());

    }
}
