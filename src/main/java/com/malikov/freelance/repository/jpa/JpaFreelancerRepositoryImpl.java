package com.malikov.freelance.repository.jpa;

import com.malikov.freelance.model.Freelancer;
import com.malikov.freelance.repository.FreelancerRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaFreelancerRepositoryImpl implements FreelancerRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Freelancer save(Freelancer freelancer) {
        if (freelancer.isNew()) {
            em.persist(freelancer);
            return freelancer;
        } else {
            return em.merge(freelancer);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Freelancer.DELETE).setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public Freelancer get(int id) {
        return em.find(Freelancer.class, id);
    }

    @Override
    public List<Freelancer> getAll() {
        return em.createNamedQuery(Freelancer.ALL_SORTED, Freelancer.class).getResultList();
    }

    @Override
    public Freelancer getByLogin(String login) {
        return em.createNamedQuery(Freelancer.BY_LOGIN, Freelancer.class).setParameter("login", login).getSingleResult();
    }

}
