package com.example.springdata.endpoints;

import com.example.springdata.db.entities.School;
import com.example.springdata.db.entities.Person;
import com.example.springdata.db.repositories.PersonRepository;
import com.example.springdata.db.repositories.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashSet;
import java.util.List;

@RestController
public class HomeController {
    private final PersonRepository personRepository;
    private final SchoolRepository schoolRepository;

    @Autowired
    public HomeController(PersonRepository personRepository, SchoolRepository schoolRepository) {
        this.personRepository = personRepository;
        this.schoolRepository = schoolRepository;
    }

    @GetMapping("/")
    public List<Person> getPeople() {
        return personRepository.findAll();
    }

    @PostMapping("/")
    public Person createPerson(@RequestBody Person person) {
        return personRepository.saveAndFlush(person);
    }

    @Transactional
    @GetMapping("/update")
    public void doUpdate() {
        Person person = personRepository.getOne(1L);

        School school = new School();
        school.setName("Some School");
        school.setTeachers(new HashSet<>());

        person.setSchool(school);

        school.getTeachers().add(person);

        schoolRepository.saveAndFlush(school);
    }
}
