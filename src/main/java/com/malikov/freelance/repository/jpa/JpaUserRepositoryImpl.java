package com.malikov.freelance.repository.jpa;

import com.malikov.freelance.model.User;
import com.malikov.freelance.repository.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaUserRepositoryImpl implements UserRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public User save(User User) {
        if (User.isNew()) {
            em.persist(User);
            return User;
        } else {
            return em.merge(User);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(User.DELETE)
                .setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public User get(int id) {
        return em.find(User.class, id);
    }

    @Override
    public User getByLogin(String login) {
        return em.createNamedQuery(User.BY_LOGIN, User.class)
                .setParameter("login", login).getSingleResult();
    }

    @Override
    public List<User> getAll() {
        return em.createNamedQuery(User.ALL_SORTED, User.class).getResultList();
    }

    @Override
    public List<User> getAllAdmins() {
        return em.createNamedQuery(User.ALL_ADMINS_SORTED, User.class).getResultList();
    }
}
