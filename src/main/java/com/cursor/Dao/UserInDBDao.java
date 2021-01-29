package com.cursor.Dao;

import com.cursor.Models.Author;
import com.cursor.Models.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

@Component
public class UserInDBDao extends ModelDao<User> {
    public UserInDBDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public User read(Integer id) {
        try {
            User user;
            Session session = sessionFactory.openSession();

            user = session.load(User.class, id);
            session.close();

            return user;
        } catch (HibernateException exception) {
            System.out.println("\nSomething goes wrong...");
            exception.printStackTrace();
            return null;
        }
    }
}
