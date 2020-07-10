CREATE TABLE department (
  Id int(11) NOT NULL AUTO_INCREMENT,
  Name varchar(60) DEFAULT NULL,
  PRIMARY KEY (Id)
);

CREATE TABLE seller (
  Id int(11) NOT NULL AUTO_INCREMENT,
  Name varchar(60) NOT NULL,
  Email varchar(100) NOT NULL,
  BirthDate datetime NOT NULL,
  BaseSalary double NOT NULL,
  DepartmentId int(11) NOT NULL,
  PRIMARY KEY (Id),
  FOREIGN KEY (DepartmentId) REFERENCES department (id)
);

CREATE TABLE partner(
	Id int(11) NOT NULL AUTO_INCREMENT,
	Name varchar(60) DEFAULT NULL,
	PRIMARY KEY (Id)
);

CREATE TABLE store (
  Id int(11) NOT NULL AUTO_INCREMENT,
  Name varchar(60) DEFAULT NULL,
  Address varchar(60),
  PRIMARY KEY (Id)
);

INSERT INTO department (Name) VALUES 
  ('Computers'),
  ('Electronics'),
  ('Fashion'),
  ('Books');

INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES 
  ('Bob Brown','bob@gmail.com','1998-04-21 00:00:00',1000,1),
  ('Maria Green','maria@gmail.com','1979-12-31 00:00:00',3500,2),
  ('Alex Grey','alex@gmail.com','1988-01-15 00:00:00',2200,1),
  ('Martha Red','martha@gmail.com','1993-11-30 00:00:00',3000,4),
  ('Donald Blue','donald@gmail.com','2000-01-09 00:00:00',4000,3),
  ('Alex Pink','bob@gmail.com','1997-03-04 00:00:00',3000,2);
  
  INSERT INTO partner (Name) VALUES 
  ('Kaboom'),
  ('Multilaser'),
  ('Razer'),
  ('Red Dragon');
  
  INSERT INTO store (Name) VALUES 
  ('Charlie', 'Joinville - Santa Catarina'),
  ('unity2', 'Barra da Tijuca - Rio de Janeiro'),
  ('Alfa', 'Recreio dos Bandeirantes - Rio de Janeiro'),
  ('Bravo', 'Leblon - Rio de Janeiro');
