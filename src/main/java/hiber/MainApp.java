package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.clearUsers();
        userService.clearCars();

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");
        Car car1 = new Car("BMW", 323);
        Car car2 = new Car("Opel", 232);
        Car car3 = new Car("Lada", 12);
        Car car4 = new Car("Niva", 102);

        user1.setCar(car1);
        user2.setCar(car2);
        user3.setCar(car3);
        user4.setCar(car4);
        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        List<User> users = userService.listUsers();
        for (User user : users) {

            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());

            if (user.getCar() != null) {

                System.out.println("Car: Model = " + user.getCar().getModel()
                        + ", Series: " + user.getCar().getSeries());
            }
            System.out.println();
        }

        userService.deleteUserById(66);
        userService.deleteCarById(48);
        System.out.println(userService.getUserByCarModelAndSeries("BMW",323));

        context.close();
    }
}
