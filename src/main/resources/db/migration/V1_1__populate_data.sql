INSERT INTO Entity(name, dbName)
VALUES ('Expenses','db1'),('Customers','db1'),('Taxes','db2')

INSERT INTO Field(entityID, name, type)
VALUES (1,'Name','VARCHAR'),(1,'Amount','FLOAT'),
       (2,'Title','VARCHAR'),(2,'Ceo','VARCHAR'),(2,'Profit','FLOAT'),
       (3,'Date','DATETIME'),(3,'Value','FLOAT')
