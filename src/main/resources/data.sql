INSERT INTO application_user (name, email, username, password, created_at)
    VALUES
        ('Adisaputra Zidha', 'hi.zidha@gmail.com', 'hizidha', '$2a$12$qDYYxWYq4Jt533f/XhFXkeSWylcjuVxei.0bCXfTssNkyVO/Z1ySm', CURRENT_TIMESTAMP),
        ('Andi Wijaya', 'andi.wijaya@example.com', 'andiwijaya', '$2a$12$qDYYxWYq4Jt533f/XhFXkeSWylcjuVxei.0bCXfTssNkyVO/Z1ySm', CURRENT_TIMESTAMP),
        ('Rina Ayu', 'rina.ayu@example.com', 'rinaayu', '$2a$12$qDYYxWYq4Jt533f/XhFXkeSWylcjuVxei.0bCXfTssNkyVO/Z1ySm', CURRENT_TIMESTAMP);

-- Insert some posts by 'hizidha'
INSERT INTO post (content, application_user, sum_of_comment, created_at, updated_at)
VALUES
    ('My first post!', 1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Loving the new features!', 1, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Does anyone have tips on performance optimization?', 2, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO comment (content, application_user, post, created_at)
VALUES
    ('Great first post!', 2, 1, CURRENT_TIMESTAMP),
    ('Keep up the good work!', 3, 1, CURRENT_TIMESTAMP),
    ('I would suggest using caching mechanisms.', 3, 3, CURRENT_TIMESTAMP);

