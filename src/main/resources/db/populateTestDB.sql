TRUNCATE TABLE project_applied_freelancers,
project_required_skills, projects, freelancer_skills, skills, user_roles, users CASCADE;

ALTER SEQUENCE projects_id_seq RESTART WITH 1;
ALTER SEQUENCE skills_id_seq RESTART WITH 1;
ALTER SEQUENCE users_id_seq RESTART WITH 1;

INSERT INTO users (login, password, first_name, last_name, email) VALUES
  ('freelancer1', '$2a$11$bRQR2FxnBrKnr/PS0eaDUeEQzO2ZtYJllGPIkdekZ0q6rJVJrCmXm', 'Yurii', 'Malikov',
   'malikov.yurii@gmail.com'), -- password = '1111'
  ('freelancer2', '$2a$11$bRQR2FxnBrKnr/PS0eaDUeEQzO2ZtYJllGPIkdekZ0q6rJVJrCmXm', 'Denis', 'Malikov',
   'malikov.denis@gmail.com'), -- password = '1111'
  ('freelancer3', '$2a$11$bRQR2FxnBrKnr/PS0eaDUeEQzO2ZtYJllGPIkdekZ0q6rJVJrCmXm', 'Egor', 'Egorov',
   'egorov.egor@gmail.com'), -- password = '1111'
  ('freelancer4', '$2a$11$bRQR2FxnBrKnr/PS0eaDUeEQzO2ZtYJllGPIkdekZ0q6rJVJrCmXm', 'Ivan', 'Ivanov',
   'ivanov.ivan@gmail.com'), -- password = '1111'
  ('admin1', '$2a$11$bRQR2FxnBrKnr/PS0eaDUeEQzO2ZtYJllGPIkdekZ0q6rJVJrCmXm', 'Vahtang', 'Vahtangov',
   'vahtangov.vahtang@gmail.com'), -- password = '1111'
  ('admin2', '$2a$11$bRQR2FxnBrKnr/PS0eaDUeEQzO2ZtYJllGPIkdekZ0q6rJVJrCmXm', 'Goga', 'Gogov', 'gogov.goga@gmail.com'),
  ('client1', '$2a$11$bRQR2FxnBrKnr/PS0eaDUeEQzO2ZtYJllGPIkdekZ0q6rJVJrCmXm', 'Sima', 'Simov', 'simov.sima@gmail.com'),
  ('client2', '$2a$11$bRQR2FxnBrKnr/PS0eaDUeEQzO2ZtYJllGPIkdekZ0q6rJVJrCmXm', 'Roza', 'Rozova', 'rozova.roza@gmail.com'),
  ('client3', '$2a$11$bRQR2FxnBrKnr/PS0eaDUeEQzO2ZtYJllGPIkdekZ0q6rJVJrCmXm', 'Isaak', 'Isaakov',
   'isaakov.isaak@gmail.com');


INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 1),
  ('ROLE_USER', 2),
  ('ROLE_USER', 3),
  ('ROLE_USER', 4),
  ('ROLE_USER', 5),
  ('ROLE_USER', 6),
  ('ROLE_USER', 7),
  ('ROLE_USER', 8),
  ('ROLE_USER', 9),
  ('ROLE_FREELANCER', 1),
  ('ROLE_FREELANCER', 2),
  ('ROLE_FREELANCER', 3),
  ('ROLE_FREELANCER', 4),
  ('ROLE_ADMIN', 5),
  ('ROLE_ADMIN', 6),
  ('ROLE_CLIENT', 7),
  ('ROLE_CLIENT', 8),
  ('ROLE_CLIENT', 9);

INSERT INTO skills (name) VALUES
  ('HTML'),
  ('CSS'),
  ('Javascript'),
  ('Java'),
  ('SQL'),
  ('Hibernate'),
  ('Spring'),
  ('Maven');

INSERT INTO freelancer_skills (freelancer_id, skill_id) VALUES
  (1, 1),
  (1, 2),
  (1, 3),
  (1, 4),
  (1, 5),
  (1, 6),
  (1, 7),
  (1, 8),
  (2, 1),
  (2, 2),
  (2, 3),
  (3, 4),
  (3, 5),
  (3, 6),
  (3, 7),
  (4, 1),
  (4, 2),
  (4, 3),
  (4, 4),
  (4, 5);

INSERT INTO projects (name, status, description, payment, client_id, freelancer_id, client_last_name) VALUES
  ('Online shop of adult toys', 'NEW',
   'I need online shop to be developed very fast (maybe in 1-2 days not more than that)', 25, 7, NULL, 'Simov'),
  ('New search system', 'LOOKING_FOR_FREELANCER',
   'I have idea. You should hack Google or Yandex. And to user its source code for our new search system. We''ll spit benefits 50/50',
   3000, 8, NULL, 'Rozova'),
  ('Shopping card', 'FREELANCER_ASSIGNED', 'Add shopping card to my online shop', 50, 9, 2, 'Isaakov'),
  ('Filter for products', 'IN_PROGRESS', 'Add convenient feature for my CRM to filter products in table', 250, 9, 1, 'Isaakov'),
  ('CRM', 'FINISHED', 'Built CRM for to manage my business (store information about products and customers)', 3555,
   9, 1, 'Isaakov'),
  ('Visit card website', 'LOOKING_FOR_FREELANCER', 'Need personal website for my dog Buddy', 75, 9, NULL, 'Isaakov'),
  ('One page website', 'LOOKING_FOR_FREELANCER', 'Need single page website to sell my dog''s hilarious video tape',
   125, 9, NULL, 'Isaakov'),
  ('Simple website for photos', 'FINISHED', 'Construct simple website where i would be able to show photos of my projects to peope.',
   135, 9, 1, 'Isaakov'),
  ('Photo-slider', 'FINISHED', 'Photo-slider module for simple website',
   55, 9, 1, 'Isaakov')
;

INSERT INTO project_required_skills (project_id, skill_id) VALUES
  (1, 1),
  (1, 2),
  (1, 3),
  (2, 4),
  (3, 1),
  (3, 2),
  (4, 4),
  (5, 1),
  (5, 2),
  (5, 3),
  (5, 4),
  (5, 5),
  (5, 6),
  (5, 7),
  (5, 8),
  (6, 1),
  (6, 2),
  (7, 1),
  (7, 2);

INSERT INTO project_applied_freelancers (project_id, freelancer_id) VALUES
  (6, 1),
  (6, 2),
  (7, 1),
  (7, 2);


