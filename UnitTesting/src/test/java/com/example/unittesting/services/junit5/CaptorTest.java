package com.example.unittesting.services.junit5;

import com.example.unittesting.dtos.Car;
import com.example.unittesting.dtos.User;
import com.example.unittesting.repos.CarRepository;
import com.example.unittesting.repos.UserRepository;
import com.example.unittesting.services.CarService;
import com.example.unittesting.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

@SpringBootTest
public class CaptorTest {

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @SpyBean
    private UserRepository userRepository;

    @SpyBean
    private CarRepository carRepository;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Captor
    private ArgumentCaptor<Car> carArgumentCaptor;

    @Test
    public void addUser_givenUser_willSaveUser() {
        User user = new User("Vasko", 15);
        User expected = new User("Vasko", 15);

        userService.addUser(user);

        // The Captor is used to catch the argument with which the Mock or Spy was invoked with
        Mockito.verify(userRepository).save(userArgumentCaptor.capture());

        User actual = userArgumentCaptor.getValue();

        Assertions.assertEquals(expected, actual);
    }

    /**
     * {@link ArgumentCaptor} is used when I want to capture the argument of verify. This can be because,
     * the argument has not defined an equals() and we can not use the normal verify to check if the passed
     * argument is the required one. So we need to assert the argument fields manually after capturing.
     */
    @Test
    public void save_givenCar_willSaveCar() {
        Car car = new Car("Maker", "Brand");
        Car expected = new Car("Maker", "Brand");

        carService.save(car);

        /**
         * As the {@link Car} does not have the equals() defined, we can not use verify to check the
         * arguments passed, we can check only the number of times called. So we need to capture the
         * argument with an argument captor and then we need to manually assert each of the fields
         * of the argument.
         */
        Mockito.verify(carRepository).save(carArgumentCaptor.capture());

        Car actual = carArgumentCaptor.getValue();

        /**
         * This should be invoked 0 times, as the expected is not equal by reference to the actual.
         */
        Mockito.verify(carRepository, Mockito.times(0)).save(expected);

        /**
         * The expected and actual are not the same, as there is no equals() defined
         */
        Assertions.assertNotEquals(expected, actual);

        /**
         * The actual assertion of the fields as there is no equals()
         */
        Assertions.assertEquals(expected.getBrand(), actual.getBrand());
        Assertions.assertEquals(expected.getMake(), actual.getMake());
    }
}
