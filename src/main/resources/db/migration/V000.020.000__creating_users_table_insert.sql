
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

INSERT INTO users (id, u_login, email) VALUES
((SELECT uuid_generate_v4()), 'login_test1', 'email_test1'),
((SELECT uuid_generate_v4()), 'login_test2', 'email_test2'),
((SELECT uuid_generate_v4()), 'login_test3', 'email_test3'),
((SELECT uuid_generate_v4()), 'login_test4', 'email_test4'),
((SELECT uuid_generate_v4()), 'login_test5', 'email_test5');