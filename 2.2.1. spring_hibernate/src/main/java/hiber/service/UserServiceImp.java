package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Service
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

   @Override
   public User getDriver(String model, int series) {
      Transaction transaction = null;
      Query query = null;
      try (Session session = userDao.getSessionFactory().openSession()) {
         String hql = "SELECT u FROM User u WHERE u.car.model=:m AND u.car.series=:s";
         query = session.createQuery(hql);
         query.setParameter("m", model);
         query.setParameter("s", series);
         return (User) query.setMaxResults(1).uniqueResult();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return null;
   }
}
