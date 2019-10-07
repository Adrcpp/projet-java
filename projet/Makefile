default: run

run:
		docker-compose up

recompile:
		@echo  "\n\n---------- RECOMPILING dao-service ------------ \n\n"; \
		cd dao-service; \
		mvn clean install -Dmaven.test.skip=true;
		@echo  "\n\n---------- RECOMPILING rest-api ------------ \n\n"; \
		cd rest-api; \
		mvn clean install;
		@echo "\n\n----------- Removing old docker image and container ------------ \n\n"; \
		docker rmi tomcat:latest -f; \
		docker rm projet_tomcat_1 -f;

test:
		@echo  "\n\n---------- TESTING dao-service ------------ \n\n"; \
		$(eval VAR="$(shell docker run -d -p 3306:3306 mysql-test)" ) \
		sleep 20; cd dao-service; mvn test; \
		docker stop $(VAR);

.PHONY: default recompile run test
