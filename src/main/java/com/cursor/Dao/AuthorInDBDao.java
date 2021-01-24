package com.cursor.Dao;

import com.cursor.Models.Author;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

@Component
public class AuthorInDBDao extends ModelDao<Author> {
    public AuthorInDBDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Author read(Integer id) {
        try {
            Author author;
            Session session = sessionFactory.openSession();

            author = session.load(Author.class, id);
            session.close();

            return author;
        } catch (HibernateException exception) {
            System.out.println("\nSomething goes wrong...");
            exception.printStackTrace();
            return null;
        }
    }
}
