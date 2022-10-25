package ru.erichev.spring.boot.web.dao;

import org.springframework.stereotype.Repository;
import ru.erichev.spring.boot.web.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        List<User> users = entityManager.createQuery("SELECT u from User u", User.class).getResultList();
        return users;
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUser(long id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public void deleteUser(long id) {
        User user = entityManager.find(User.class,id);
        if (user != null) {
            entityManager.remove(user);
        } else {
            throw new IllegalArgumentException("User не найден");
        }
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }
}