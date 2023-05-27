#postgres

-- Login to postgres

psql -U postgres

(password- root)

---Create database user and password

CREATE DATABASE blog;

CREATE USER blog_user WITH ENCRYPTED PASSWORD 'blog_password';

GRANT ALL PRIVILEGES ON DATABASE blog TO blog_user;
