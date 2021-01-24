package com.cursor.Dao;

import com.cursor.Dao.interfaces.CRUD;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Component
public abstract class ModelDao<T> implements CRUD<T> {
    protected final SessionFactory sessionFactory;

    public ModelDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public boolean create(T model) {
        try {
            boolean isCreated = false;
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            session.save(model);
            isCreated = session.contains(model);

            session.getTransaction().commit();
            session.close();
            return isCreated;
        } catch (HibernateException exception) {
            System.out.println("\nSomething goes wrong...");
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public void update(T model) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            session.update(model);

            session.getTransaction().commit();
            session.close();
        } catch (HibernateException exception) {
            System.out.println("\nSomething goes wrong...");
            exception.printStackTrace();
        }
    }

    @Override
    public boolean delete(T model) {
        try {
            boolean isDeleted = false;
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            session.delete(model);
            isDeleted = !session.contains(model);

            session.getTransaction().commit();
            session.close();

            return isDeleted;
        } catch (HibernateException exception) {
            System.out.println("\nSomething goes wrong...");
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public List<T> getAll(Class<T> type) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        List<T> models = session.createQuery(criteria).getResultList();
        session.close();
        return models;
    }
}
