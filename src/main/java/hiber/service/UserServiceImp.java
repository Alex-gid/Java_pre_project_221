package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
public class UserServiceImp implements UserService{

   @Autowired
   private UserDao userDao;

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }
   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }
   @Transactional
   @Override
   public void addCar(Car car) {
      userDao.addCar(car);
   }
   @Transactional
   @Override
   public List<Car> listCars() {
      return userDao.listCars();
   }
   @Transactional
   @Override
   public void clearCars() {
      userDao.clearCars();
   }
   @Transactional
   @Override
   public void clearUsers() {
      userDao.clearUsers();
   }
   @Transactional
   @Override
   public void deleteUserById(long id) {
      userDao.deleteUserById(id);
   }
   @Transactional
   @Override
   public void deleteCarById(int id) {
      userDao.deleteCarById(id);
   }
   @Transactional
   @Override
   public String getUserByCarModelAndSeries(String carModel, int carSeries) {
      return userDao.getUserByCarModelAndSeries(carModel, carSeries);
   }
}
