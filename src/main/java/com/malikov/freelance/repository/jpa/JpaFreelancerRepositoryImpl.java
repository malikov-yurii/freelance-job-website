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



    //    @Override
//    public Collection<Freelancer
//Extended> getByCustomerId(int customerId) {
//        return em.createNamedQuery(Freelancer
//Extended.BY_CUSTOMER_ID, Freelancer
//Extended.class)
//                .setParameter("customerId", customerId).getResultList();
//    }

//    @Override
//    public Collection<Freelancer
//Extended> getByProductId(int productId) {
//        return em.createNamedQuery(Freelancer
//Extended.BY_PRODUCT_ID, Freelancer
//Extended.class)
//                .setParameter("productId", productId).getResultList();
//    }
}
