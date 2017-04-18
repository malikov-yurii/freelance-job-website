package com.malikov.freelance.repository.jpa;

import com.malikov.freelance.model.Client;
import com.malikov.freelance.repository.ClientRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaClientRepositoryImpl implements ClientRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public  Client save( Client customer) {
        if (customer.isNew()) {
            em.persist(customer);
            return customer;
        } else {
            return em.merge(customer);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery( Client.DELETE).setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public  Client get(int id) {
        return em.createNamedQuery( Client.GET,  Client.class)
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public List< Client> getAll() {
        return em.createNamedQuery( Client.ALL_SORTED,  Client.class).getResultList();
    }

    @Override
    public Client getByLogin(String login) {
        return em.createNamedQuery(Client.BY_LOGIN, Client.class).setParameter("login", login).getSingleResult();
    }

}
