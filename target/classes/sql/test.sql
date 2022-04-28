create table Offer(
                      id int NOT NULL IDENTITY,
                      client varchar(50),
                      years int,
                      amount_payment double,
                      percent double,
                      month_pay double,
                      PRIMARY KEY (id)
);

INSERT INTO OFFER (client, years, amount_payment, percent, month_pay) VALUES (?, ?, ?, ?, ?);

SELECT * FROM OFFER;

create table Dept(
                     id INT NOT NULL  IDENTITY,
                     limit INT,
                     PERCENT INT
);

INSERT INTO DEPT (LIMIT, PERCENT) VALUES (?, ?);

SELECT * FROM DEPT;

SELECT * FROM DEPT WHERE id=?;

UPDATE DEPT SET LIMIT=?, PERCENT=? WHERE id=?

DELETE FROM DEPT WHERE id=?

create table Person(
                       id int NOT NULL IDENTITY,
                       name varchar(50),
                       number bigint,
                       mail varchar(50),
                       passport varchar(50),
                       PRIMARY KEY (id)
);

INSERT INTO PERSON (name, number, mail, passport) VALUES  (?, ?, ?, ?);

SELECT * FROM PERSON;

SELECT * FROM PERSON WHERE id=?;

UPDATE PERSON SET NAME=?, NUMBER=?, MAIL=?, PASSPORT=? WHERE id=?

DELETE FROM Person WHERE id=?