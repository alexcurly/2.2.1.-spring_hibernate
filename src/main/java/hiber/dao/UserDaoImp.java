package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Repository;
import javax.persistence.TypedQuery;
import javax.security.auth.login.Configuration;
import java.util.List;


//@Repository - указывает, что класс выполняет роль хранилища (объект доступа к DAO) = @Component
@Repository
public class UserDaoImp implements UserDao {

   private SessionFactory sessionFactory;



   @Autowired
   public void setSessionFactory(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }


   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

//   public void add(User user ) {
//      try (Session session = sessionFactory.openSession()) {
//         Transaction transaction = session.beginTransaction();
//         session.save(user);
//         transaction.commit();
//      }
//   }

//   @Override
//   @SuppressWarnings("unchecked")
//   public List<User> listUsers() {
//      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
//      return query.getResultList();
//   }

//   @Override
//   @SuppressWarnings("unchecked")
//   public List<User> listUsers() {
//      try (Session session = sessionFactory.openSession()) {
//         Query<User> query = sessionFactory.getCurrentSession().createQuery("from User");
//         return query.getResultList();
//      }
//   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }


//   @Override
//   @SuppressWarnings("unchecked")
//   public User getUserByCar(String model, int series) {
//      String hql = "from User user where user.car.model = :model and user.car.series = :series";
//      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql);
//      query.setParameter("model", model).setParameter("series", series);
//      return query.setMaxResults(1).getSingleResult();
//   }

   @Override
   @SuppressWarnings("unchecked")
   public User getUserByCar(String model, int series) {
      try (Session session = sessionFactory.openSession()) {
         String hql = "from User user where user.car.model = :model and user.car.series = :series";
         Query<User> query = session.createQuery(hql);
         query.setParameter("model", model).setParameter("series", series);
         return query.setMaxResults(1).getSingleResult();
      }
   }
}