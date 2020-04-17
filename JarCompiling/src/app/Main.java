package app;

import cw.ConsoleWriter;
import dto.Person;
import dto.PersonDto;

import org.modelmapper.ModelMapper;

public class Main {
    public static void main(String[] args) {
        ConsoleWriter consoleWriter = new ConsoleWriter();
        Person person = new Person("Vasil");
        ModelMapper mapper = new ModelMapper();
        PersonDto personDto = mapper.map(person, PersonDto.class);
        consoleWriter.writeLine("Hello World! " + personDto.getName());
    }
}
