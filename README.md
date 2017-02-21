# username-validator
Full stack web applicaton, Implemented to lean about Spring MVC, Rest Services, AngularJS

For deploy it:

For easy deployment I created a docker container shared in my docker hub account, for deploy locally with docker and debian based OS please execute the following commands:

# Deploying the database

1.- Git clone https://github.com/frederickfaria/username-validator.git

2.- cd validator/deployment/database/databasebuild.sh

3.- ./databasebuild.sh

This will create the database and insert 456 rows in restrictedword table and 269558 rows in username table for performance testing.

# Deploying the application:

1.- cd validator/deployment/server/

2.- ./dockerbuild.sh (for susccessfuly compilation shoud be maven installed)

This will compile the application, run the docker build command and create locally the docker application image.

# Deployment

Execute: docker run -d -p 8080:8080 --name tomcat --link postgres:postgres --volumes-from postgres frederickfaria/username-validator;docker logs -f tomcat

This will deploy locally the application and will be accessible in http://localhost:8080/validator/index.
