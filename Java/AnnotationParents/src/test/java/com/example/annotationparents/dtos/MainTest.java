package com.example.annotationparents.dtos;

import com.example.annotationparents.annotations.*;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AnnotationTests {
    /**
     * This is how we can get the Annotations of a specific annotation, gotten from an object.
     */
    @Test
    public void gettingAnnotationsOfAnAnnotation() {
        Car car = new Car();
        Child childAnnotation = car.getClass().getAnnotation(Child.class);
        assertEquals("This is the child default name", childAnnotation.name());

        // With annotationType() we get the Type of the annotation as we would with getClass()
        Parent parentAnnotation = childAnnotation.annotationType().getAnnotation(Parent.class);

        assertEquals("This is the parent default name", parentAnnotation.name());

        GrandParent grandParent = parentAnnotation.annotationType().getAnnotation(GrandParent.class);
        assertEquals("Old boy here", grandParent.name());

        // If we have Annotation[] then we have to downcast to GrandParent
        Annotation[] allParentAnnotations = parentAnnotation.annotationType().getAnnotations();
        GrandParent grandParentFromArray = (GrandParent) allParentAnnotations[2];
        assertEquals("Old boy here", grandParentFromArray.name());
    }

    @Test
    public void subClass_givenAnnotationsWithInheritedAndWithOutInherited_willRetainOnlyInheritedOnes() {
        Cat cat = new Cat();
        InheritedAnnotation inherited = cat.getClass().getAnnotation(InheritedAnnotation.class);
        NotInheritedAnnotation notInherited = cat.getClass().getAnnotation(NotInheritedAnnotation.class);
        Annotation[] annotations = cat.getClass().getAnnotations();

        assertEquals(1, annotations.length);
        assertEquals("I am inherited", inherited.message());
        assertNull(notInherited);
    }

    @Test
    public void class_givenAnnotationsWithInheritedAndWithOutInherited_willRetainAllAnnotations() {
        Animal animal = new Animal();
        InheritedAnnotation inherited = animal.getClass().getAnnotation(InheritedAnnotation.class);
        NotInheritedAnnotation notInherited = animal.getClass().getAnnotation(NotInheritedAnnotation.class);
        Annotation[] annotations = animal.getClass().getAnnotations();

        assertEquals(2, annotations.length);
        assertEquals("I am inherited", inherited.message());
        assertEquals("I am not inherited", notInherited.message());
    }
}