CREATE  TABLE Person (
  person_id  int(11) NOT NULL AUTO_INCREMENT,
  first_name VARCHAR2(50),
  last_name VARCHAR2(60),
  mail_id VARCHAR2(100),
  address VARCHAR2(500)
);

INSERT INTO Person (first_name,last_name,mail_id,address) values ('Rakesh','Chandran','racky@gmail.com','chennai , India');
INSERT INTO Person (first_name,last_name,mail_id,address) values ('Vamsy','Raju','vamsy@gmail.com','Texas , US');
INSERT INTO Person (first_name,last_name,mail_id,address) values ('Madhan','Babu','madhan@gmail.com','chennai , India');
INSERT INTO Person (first_name,last_name,mail_id,address) values ('Srini','Krishnmorty','srini@gmail.com','sydney , Australia');