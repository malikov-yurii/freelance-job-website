package com.malikov.freelance.service;

import com.malikov.freelance.model.Freelancer;
import com.malikov.freelance.repository.FreelancerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FreeLancerServiceImpl implements FreelancerService {

    @Autowired
    FreelancerRepository repository;

    @Override
    public Freelancer save(Freelancer freelancer) {
        return repository.save(freelancer);
    }

    @Override
    public Freelancer update(Freelancer freelancer) {
        return repository.save(freelancer);
    }

    @Override
    public Freelancer get(int id) {
        return repository.get(id);
    }

    @Override
    public List<Freelancer> getAll() {
        return repository.getAll();
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }
}
