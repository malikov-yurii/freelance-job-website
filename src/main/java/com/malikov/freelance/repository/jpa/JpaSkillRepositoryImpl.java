package com.malikov.freelance.repository.jpa;

import com.malikov.freelance.model.Skill;
import com.malikov.freelance.repository.SkillRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaSkillRepositoryImpl implements SkillRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Skill save(Skill Skill) {
        if (Skill.isNew()) {
            em.persist(Skill);
            return Skill;
        } else {
            return em.merge(Skill);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Skill.DELETE)
                .setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public Skill get(int id) {
        return em.find(Skill.class, id);
    }


    @Override
    public List<Skill> getAll() {
        return em.createNamedQuery(Skill.ALL_SORTED, Skill.class)
                .getResultList();
    }

}
