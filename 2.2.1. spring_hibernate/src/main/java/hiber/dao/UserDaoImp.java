package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   @Override
   public User getDriverName(String model, int series) {
      try (Session session = sessionFactory.openSession()) {
         String hql = "SELECT u FROM User u WHERE u.car.model=:m AND u.car.series=:s";
         Query query = session.createQuery(hql);
         query.setParameter("m", model);
         query.setParameter("s", series);
         return (User) query.setMaxResults(1).uniqueResult();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return null;
   }
}
