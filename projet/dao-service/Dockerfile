FROM mysql/mysql-server:5.7

ENV MYSQL_DATABASE=projet-libre
ENV MYSQL_USER=application
ENV MYSQL_PASSWORD=passw0rd
ENV MYSQL_ALLOW_EMPTY_PASSWORD=true

COPY SQL/import/script.sql /docker-entrypoint-initdb.d/data-dump.sql

RUN /entrypoint.sh mysqld & sleep 30

EXPOSE 3306
