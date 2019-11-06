FROM mysql:5.7.28

COPY csv/* /var/lib/mysql-files/
COPY conf/my.cnf /etc/mysql/my.cnf
COPY dump/* docker-entrypoint-initdb.d/
