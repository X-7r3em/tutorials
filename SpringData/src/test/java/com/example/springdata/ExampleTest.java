package com.example.springdata;

import com.example.springdata.db.entities.Child;
import com.example.springdata.db.entities.Person;
import com.example.springdata.db.repositories.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExampleTest {

    @Autowired
    PersonRepository personRepository;

    @Test
    public void testing() {
        Person person = new Person();
        person.setAge(5);
        Person person1 = personRepository.save(person);
        
        System.out.println();
    }

}
