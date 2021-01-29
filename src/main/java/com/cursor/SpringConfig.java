package com.cursor;

import com.cursor.Dao.AuthorInDBDao;
import com.cursor.Dao.BookInDBDao;
import com.cursor.Dao.UserInDBDao;
import com.cursor.Models.Author;
import com.cursor.Models.Book;
import com.cursor.Models.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.cursor")
public class SpringConfig {
    @Bean
    public SessionFactory sessionFactory() {
        org.hibernate.cfg.Configuration configuration =
                new org.hibernate.cfg.Configuration().configure();

        configuration.addAnnotatedClass(Author.class);
        configuration.addAnnotatedClass(Book.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(AuthorInDBDao.class);
        configuration.addAnnotatedClass(BookInDBDao.class);
        configuration.addAnnotatedClass(UserInDBDao.class);

        StandardServiceRegistryBuilder builder =
                new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());

        return configuration.buildSessionFactory(builder.build());
    }
}
