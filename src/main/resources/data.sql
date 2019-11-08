-- Test users have password 'password'
INSERT INTO User (id, username, email, password) VALUES
    (1, 'test user 1', 'test1@email.com', '$2y$10$sfUKVntkjMUxiSaKynzr9OHxg19zIWiejbw/UbEAqiTmtzNJwpLhG'),
    (2, 'test user 2', 'test2@email.com', '$2y$10$sfUKVntkjMUxiSaKynzr9OHxg19zIWiejbw/UbEAqiTmtzNJwpLhG');
