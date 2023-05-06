package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;

import java.util.List;

public interface UserDao {
   SessionFactory getSessionFactory();
   void add(User user);
   List<User> listUsers();
}
