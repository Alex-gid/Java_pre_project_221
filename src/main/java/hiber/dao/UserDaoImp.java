package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void deleteUserById(long id) {

        User user = sessionFactory.getCurrentSession().get(User.class, id);

        if (user != null) {
            sessionFactory.getCurrentSession().delete(user);
            System.out.println("Пользователь с ID: " + id + " удалён!");
        } else {
            System.out.println("Пользователь с таким ID не найден!");
        }
    }

    @Override
    public void addCar(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    public void deleteCarById(int id) {

        Car car = sessionFactory.getCurrentSession().get(Car.class, id);

        if (car != null) {
            sessionFactory.getCurrentSession().delete(car);
            System.out.println("Автомобиль с ID: " + id + " удалён!");
        } else {
            System.out.println("Автомобиль с таким ID не найден!");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> listCars() {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car");
        return query.getResultList();
    }

    @Override
    public void clearCars() {
        sessionFactory.getCurrentSession().createQuery("delete from Car").executeUpdate();
    }

    @Override
    public void clearUsers() {
        sessionFactory.getCurrentSession().createQuery("delete from User").executeUpdate();
    }

    @Override
    @SuppressWarnings("unchecked")
    public String getUserByCarModelAndSeries(String carModel, int carSeries) {

        String hql = "select u from User u join u.car c where c.model = :model and c.series = :series";

        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql);

        query.setParameter("model", carModel);
        query.setParameter("series", carSeries);

        try {
            User user = query.getSingleResult();
            System.out.println("это!");
            return "Пользователь " + user.getFirstName() + " владеет автомобилем с моделью " + carModel + " и серией " + carSeries;
        } catch (NoResultException ex) {
            return "Автомобиль с моделью " + carModel + " и серией " + carSeries + " не найден";
        }
    }
}
