package com.malikov.freelance.repository;

import com.malikov.freelance.model.Project;

import java.util.List;

public interface ProjectRepository extends Repository<Project> {


    List<Project> getPortfolio(int freelancerId);
}
