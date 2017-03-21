DROP TABLE IF EXISTS freelancer_portfolio_projects;
DROP TABLE IF EXISTS project_applied_freelancers;
DROP TABLE IF EXISTS project_required_skills;
DROP TABLE IF EXISTS projects;
DROP TABLE IF EXISTS freelancer_skills;
DROP TABLE IF EXISTS skills;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
  id         SERIAL PRIMARY KEY,
  login      VARCHAR NOT NULL,
  password   VARCHAR NOT NULL,
  first_name VARCHAR NOT NULL,
  last_name  VARCHAR NOT NULL,
  email      VARCHAR NOT NULL
);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR NOT NULL,
  CONSTRAINT users_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE skills
(
  id   SERIAL PRIMARY KEY,
  name VARCHAR NOT NULL
);


CREATE TABLE freelancer_skills
(
  freelancer_id INTEGER NOT NULL,
  skill_id      INTEGER NOT NULL,
  CONSTRAINT freelancer_id_skill_id_idx UNIQUE (freelancer_id, skill_id),
  FOREIGN KEY (freelancer_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (skill_id) REFERENCES skills (id)
);

CREATE TABLE projects
(
  id            SERIAL PRIMARY KEY,
  name          VARCHAR,
  status        VARCHAR,
  description   VARCHAR,
  client_id     INTEGER,
  freelancer_id INTEGER,
  FOREIGN KEY (client_id) REFERENCES users (id),
  FOREIGN KEY (freelancer_id) REFERENCES users (id)
);

CREATE TABLE project_required_skills
(
  project_id INTEGER NOT NULL,
  skill_id   INTEGER NOT NULL,
  CONSTRAINT project_id_skill_id_idx UNIQUE (project_id, skill_id),
  FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE,
  FOREIGN KEY (skill_id) REFERENCES skills (id)
);

CREATE TABLE project_applied_freelancers
(
  project_id    INTEGER NOT NULL,
  freelancer_id INTEGER NOT NULL,
  CONSTRAINT project_id_freelancer_id_idx UNIQUE (project_id, freelancer_id),
  FOREIGN KEY (project_id) REFERENCES projects (id) ON DELETE CASCADE,
  FOREIGN KEY (freelancer_id) REFERENCES users (id)
);

CREATE TABLE freelancer_portfolio_projects
(
  freelancer_id INTEGER NOT NULL,
  project_id    INTEGER NOT NULL,
  CONSTRAINT freelancer_id_project_id_idx UNIQUE (freelancer_id, project_id),
  FOREIGN KEY (freelancer_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (project_id) REFERENCES skills (id)
);