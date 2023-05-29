#postgres

-- Login to postgres

psql -d dbname -U postgres

(password- root)

---Create database user and password

CREATE DATABASE blog;

CREATE USER blog_user WITH ENCRYPTED PASSWORD 'blog_password';

GRANT ALL PRIVILEGES ON DATABASE blog TO blog_user;

---login to blog db with postgres user and run the below command.

GRANT ALL ON SCHEMA public TO blog_user;
