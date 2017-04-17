package com.malikov.freelance.service;

import com.malikov.freelance.model.Project;

import java.util.List;

public interface ProjectService extends Service<Project> {

    List<Project> getPortfolio(int freelancerId);
}
