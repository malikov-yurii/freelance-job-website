package com.malikov.freelance.util;

import com.malikov.freelance.model.ProjectStatus;
import com.malikov.freelance.to.ProjectStatusTo;

public class ProjectStatusUtil {

    public static ProjectStatusTo asTo(ProjectStatus projectStatus) {
        return new ProjectStatusTo(projectStatus.name(), projectStatus.name().replace('_', ' '));
    }
}
