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

//Dependency Injection

constructor injection is preferred over the field injection. User field level @Autowired if constructor injection
is not possible. Since Constructor is injected first and then the fields. Here I have used both in controller class
I have used constructor injection and in Service class used field injection.


HAILSTORM - uber testing strategy