package com.malikov.freelance.repository.jpa;

import com.malikov.freelance.model.Project;
import com.malikov.freelance.repository.ProjectRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaProjectRepositoryImpl implements ProjectRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Project save(Project Project) {
        if (Project.isNew()) {
            em.persist(Project);
            return Project;
        } else {
            return em.merge(Project);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Project.DELETE)
                .setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public Project get(int id) {
        return em.find(Project.class, id);
    }

    @Override
    public List<Project> getAll() {
        return em.createNamedQuery(Project.ALL_SORTED, Project.class)
                .getResultList();
    }

    @Override
    public List<Project> getPortfolio(int freelancerId) {
        return em.createNamedQuery(Project.GET_PORTFOLIO, Project.class)
                .setParameter("freelancerId" ,freelancerId)
                .getResultList();
    }
}
