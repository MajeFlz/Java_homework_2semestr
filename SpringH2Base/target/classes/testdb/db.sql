CREATE TABLE STUDENTS (	
                           id int NOT NULL AUTO_INCREMENT,
                           name varchar(15),
                           surname varchar(25),
                           faculty varchar(20),
                           age int,
                           PRIMARY KEY (id)
);

CREATE TABLE DISCIPLINE (
                           id int NOT NULL AUTO_INCREMENT, 
                           name varchar(15),
                           hours int,
                           PRIMARY KEY (id)
);