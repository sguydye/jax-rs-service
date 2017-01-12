IF NOT EXISTS (SELECT * FROM sysobjects WHERE name = 'Entity' and xtype='U')
CREATE TABLE Entity (
ID INT IDENTITY PRIMARY KEY,
name VARCHAR(255) NOT NULL,
dbName VARCHAR(255),
created DATETIME,
lastModified DATETIME
)
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name = 'Field' and xtype='U')
CREATE TABLE Field (
ID INT IDENTITY PRIMARY KEY,
entityID INT NOT NULL,
name VARCHAR(255) NOT NULL,
type VARCHAR(255) NOT NULL,
length SMALLINT,
mantissa TINYINT,
created DATETIME,
lastModified DATETIME
)
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name = 'Constraints' and xtype='U')
CREATE TABLE [Constraint] (
ID INT IDENTITY PRIMARY KEY,
EntityID INT NOT NULL,
type VARCHAR(255) NOT NULL,
created DATETIME,
lastModified DATETIME
)
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name = 'Constraint_Field' and xtype='U')
CREATE TABLE Constraint_Field (
constraintID INT NOT NULL,
fieldID INT NOT NULL
)
