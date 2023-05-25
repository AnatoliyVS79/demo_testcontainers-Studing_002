

CREATE TABLE IF NOT EXISTS users
(
    id UUID PRIMARY KEY NOT NULL,
    u_login VARCHAR NOT NULL,
    email VARCHAR NOT NULL
);

-- INSERT INTO users (id, u_login, email) VALUES
-- ('uuid_generate_v4()', 'login_test1', 'email_test1'),
-- ('uuid_generate_v4()', 'login_test2', 'email_test2'),
-- ('uuid_generate_v4()', 'login_test3', 'email_test3'),
-- ('uuid_generate_v4()', 'login_test4', 'email_test4'),
-- ('uuid_generate_v4()', 'login_test5', 'email_test5');