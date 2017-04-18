package com.malikov.freelance.service;

import com.malikov.freelance.model.Skill;
import com.malikov.freelance.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    SkillRepository repository;

    @Override
    public Skill save(Skill skill) {
        Assert.notNull(skill, "skill must not be null");
        return repository.save(skill);
    }

    @Override
    public Skill update(Skill skill) {
        Assert.notNull(skill, "skill must not be null");
        return repository.save(skill);
    }

    @Override
    public Skill get(int id) {
        return repository.get(id);
    }

    @Override
    public List<Skill> getAll() {
        return repository.getAll();
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }
}
