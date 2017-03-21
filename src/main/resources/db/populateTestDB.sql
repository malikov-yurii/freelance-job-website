TRUNCATE TABLE freelancer_portfolio_projects, project_applied_freelancers,
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
   'vahtangov.vahtang@gmai.com'), -- password = '1111'
  ('admin2', '$2a$11$bRQR2FxnBrKnr/PS0eaDUeEQzO2ZtYJllGPIkdekZ0q6rJVJrCmXm', 'Goga', 'Gogov', 'gogov.goga@gmai.com'),
  ('client1', '$2a$11$bRQR2FxnBrKnr/PS0eaDUeEQzO2ZtYJllGPIkdekZ0q6rJVJrCmXm', 'Sima', 'Simov', 'simov.sima@gmai.com'),
  ('client2', '$2a$11$bRQR2FxnBrKnr/PS0eaDUeEQzO2ZtYJllGPIkdekZ0q6rJVJrCmXm', 'Roza', 'Rozova', 'rozova.roza@gmai.com'),
  ('client3', '$2a$11$bRQR2FxnBrKnr/PS0eaDUeEQzO2ZtYJllGPIkdekZ0q6rJVJrCmXm', 'Isaak', 'Isaakov',
   'isaakov.isaak@gmai.com');


INSERT INTO user_roles (role, user_id) VALUES
  ('USER', 1),
  ('USER', 2),
  ('USER', 3),
  ('USER', 4),
  ('USER', 5),
  ('USER', 6),
  ('USER', 7),
  ('USER', 8),
  ('USER', 9),
  ('FREELANCER', 1),
  ('FREELANCER', 2),
  ('FREELANCER', 3),
  ('FREELANCER', 4),
  ('ADMIN', 5),
  ('ADMIN', 6),
  ('CLIENT', 7),
  ('CLIENT', 8),
  ('CLIENT', 9);

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
  (2, 1),
  (2, 2),
  (2, 3),
  (3, 4),
  (3, 5),
  (3, 6),
  (3, 7),
  (4, 2),
  (4, 2),
  (4, 3),
  (4, 4),
  (4, 5);

INSERT INTO projects (name, status, description, payment, client_id, freelancer_id) VALUES
  ('Online shop of adult toys', 'NEW',
   'I need online shop to be developed very fast (maybe in 1-2 days not more than that)', 25.00, 7, NULL),
  ('New search system', 'LOOKING_FOR_FREELANCER',
   'I have idea. You should hack Google or Yandex. And to user its source code for our new search system. We''ll spit benefits 50/50',
   3000.00, 8, NULL),
  ('Shopping cart', 'FREELANCER_ASSINED', 'Add shopping cart to my online shop', 50.00, 9, 2),
  ('Filtering of products', 'IN_PROGRESS', 'Add convenient feature for my CRM to filter products in table', 250.00, 9,
   1),
  ('CRM', 'FINISHED', 'Built CRM for to manage my business (store information about products and customers)', 3000.00,
   9, NULL),
  ('Visit card website', 'LOOKING_FOR_FREELANCER', 'Need personal website for my dog Buddy', 75.00, 9, NULL),
  ('One page website', 'LOOKING_FOR_FREELANCER', 'Need single page website to sell my dog''s hilarious video tape',
   125.00, 9, NULL),
  ('Simple website for photos', 'FINISHED', 'Construct simple website where i would be able to show photos of my projects to peope.',
   135.00, 9, 1),
  ('Photo-slider', 'FINISHED', 'Photo-slider module for simple website',
   55.00, 9, 1)
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


