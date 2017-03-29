package com.malikov.freelance.service;

import com.malikov.freelance.model.Freelancer;

public interface FreelancerService extends Service<Freelancer> {

    Freelancer getByLogin(String login);

}
