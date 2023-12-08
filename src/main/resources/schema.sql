CREATE TABLE STAFF (
ID INT AUTO_INCREMENT  PRIMARY KEY,
NAME VARCHAR(250) NOT NULL,
SURNAME VARCHAR(250) NOT NULL,
PATRONYMIC VARCHAR(250) NOT NULL,
GENDER BOOLEAN DEFAULT FALSE,
BIRTH DATE NOT NULL,
POST VARCHAR(250) NULL,
SALARY INT NOT NULL
);

CREATE TABLE FOOTBALLORGANIZATION (
ID INT AUTO_INCREMENT  PRIMARY KEY,
clubName VARCHAR(250) NOT NULL,
LEAGUE VARCHAR(250) NOT NULL,
STAFF_ID INT NOT NULL
);
