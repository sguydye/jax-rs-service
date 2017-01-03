CREATE TABLE Entity (
ID INT PRIMARY KEY,
name VARCHAR(255) NOT NULL,
dbName VARCHAR(255)
)
CREATE TABLE Field (
ID INT PRIMARY KEY,
entityID INT NOT NULL,
name VARCHAR(255) NOT NULL,
type VARCHAR(255) NOT NULL,
length SMALLINT,
mantissa TINYINT
)
CREATE TABLE Constraints (
ID INT PRIMARY KEY,
EntityID INT NOT NULL,
type VARCHAR(255) NOT NULL
)
CREATE TABLE Constraint_Field (
constraintID INT NOT NULL,
fieldID INT NOT NULL
)
