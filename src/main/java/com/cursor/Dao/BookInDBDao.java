package com.cursor.Dao;

import com.cursor.Models.Author;
import com.cursor.Models.Book;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

@Component
public class BookInDBDao extends ModelDao<Book> {
    public BookInDBDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Book read(Integer id) {
        try {
            Session session = sessionFactory.openSession();

            Book book = session.load(Book.class, id);
            session.close();

            return book;
        } catch (HibernateException exception) {
            System.out.println("\nSomething goes wrong...");
            exception.printStackTrace();
            return null;
        }
    }
}
