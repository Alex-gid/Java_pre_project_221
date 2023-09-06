package hiber.dao;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserDao {
   void add(User user);
   List<User> listUsers();
   void addCar(Car car);
   List<Car> listCars();
   void clearUsers();
   void clearCars();
   void deleteUserById(long id);
   void deleteCarById(int id);
   String getUserByCarModelAndSeries(String carModel, int carSeries);
}
