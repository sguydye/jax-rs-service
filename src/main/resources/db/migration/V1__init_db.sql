IF NOT EXISTS (SELECT * FROM sysobjects WHERE name = 'Entity' and xtype='U')
CREATE TABLE Entity (
ID INT PRIMARY KEY,
name VARCHAR(255) NOT NULL,
dbName VARCHAR(255)
)
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name = 'Field' and xtype='U')
CREATE TABLE Field (
ID INT PRIMARY KEY,
entityID INT NOT NULL,
name VARCHAR(255) NOT NULL,
type VARCHAR(255) NOT NULL,
length SMALLINT,
mantissa TINYINT
)
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name = 'Constraints' and xtype='U')
CREATE TABLE Constraints (
ID INT PRIMARY KEY,
EntityID INT NOT NULL,
type VARCHAR(255) NOT NULL
)
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name = 'Constraint_Field' and xtype='U')
CREATE TABLE Constraint_Field (
constraintID INT NOT NULL,
fieldID INT NOT NULL
)
