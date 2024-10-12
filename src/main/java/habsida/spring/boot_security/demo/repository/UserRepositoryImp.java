package habsida.spring.boot_security.demo.repository;

import habsida.spring.boot_security.demo.model.User;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepositoryImp implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAll() {
        return entityManager.createQuery("select distinct a from User a left join fetch a.roles", User.class).getResultList();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }


    @Override
    public void deleteUser(long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public User getUser(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByUsername(String username) {
        return entityManager.createQuery("select distinct a from User a left join fetch a.roles where a.username = :username", User.class)
                .setParameter("username", username).getSingleResult();
    }
}
