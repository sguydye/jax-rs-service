INSERT INTO Entity(name, dbName)
VALUES ('Expenses','db1'),('Customers','db1'),('Taxes','db2')

INSERT INTO Field(entityID, name, type)
VALUES (1,'Name','VARCHAR'),(1,'Amount','FLOAT'),
       (2,'Title','VARCHAR'),(2,'Ceo','VARCHAR'),(2,'Profit','FLOAT'),
       (3,'Date','DATETIME'),(3,'Value','FLOAT')

INSERT INTO [Constraint](entityID, type)
VALUES (1,'NOTNULL'),(1,'NOTNULL'),
       (2,'NOTNULL'),
       (3,'NOTNULL'),(3,'NOTNULL')

INSERT INTO Constraint_Field(constraintID, fieldID)
VALUES (1,1),
       (2,2),
       (3,3),
       (4,6),
       (5,7)
