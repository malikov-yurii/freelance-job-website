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

//    @Override
//    public List< Client> getByName(String name) {
//        return em.createNamedQuery( Client.BY_NAME,  Client.class)
//                .setParameter("name", name).getResultList();
//    }
//
//    @Override
//    public List< Client> getByLastName(String lastName) {
//        return em.createNamedQuery( Client.BY_LAST_NAME,  Client.class)
//                .setParameter("lastName", lastName).getResultList();
//    }
//
//    @Override
//    public List< Client> getByFirstNameMask(String firstNameMask) {
//        return em.createNamedQuery( Client.BY_FIRST_NAME_MASK,  Client.class)
//                .setParameter("firstNameMask", "%" + firstNameMask + "%").getResultList();
//    }
//
//    @Override
//    public List< Client> getByLastNameMask(String lastNameMask) {
//        return em.createNamedQuery( Client.BY_LAST_NAME_MASK,  Client.class)
//                .setParameter("lastNameMask", "%" + lastNameMask + "%").getResultList();
//    }
//
//    @Override
//    public List< Client> getByPhoneNumberMask(String phoneNumberMask) {
//        return em.createNamedQuery( Client.BY_PHONE_NUMBER_MASK,  Client.class)
//                .setParameter("phoneNumberMask", "%" + phoneNumberMask + "%").getResultList();
//    }
//
//    @Override
//    public List< Client> getByCityMask(String cityMask) {
//        return em.createNamedQuery( Client.BY_CITY_MASK,  Client.class)
//                .setParameter("cityMask", "%" + cityMask + "%").getResultList();
//    }
//
//    @Override
//    public List< Client> getByCity(String city) {
//        return em.createNamedQuery( Client.BY_CITY,  Client.class)
//                .setParameter("city", city).getResultList();
//    }
//
//    @Override
//    public  Client getByEmail(String email) {
//        return em.createNamedQuery( Client.BY_EMAIL,  Client.class)
//                .setParameter("email", email).getSingleResult();
//    }
//
//    @Override
//    public  Client getByPhoneNumber(String phoneNumber) {
//        return em.createNamedQuery( Client.BY_PHONE_NUMBER,  Client.class)
//                .setParameter("phoneNumber", phoneNumber).getSingleResult();
//    }


}
