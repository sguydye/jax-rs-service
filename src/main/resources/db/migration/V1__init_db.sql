CREATE TABLE Entities (
ID INT PRIMARY KEY,
userID INT,
name VARCHAR(255) NOT NULL
)
CREATE TABLE Fields (
ID INT PRIMARY KEY,
entityID INT FOREIGN KEY REFERENCES Entities(id) ON DELETE CASCADE,
name VARCHAR(255) NOT NULL,
type INT NOT NULL,
length INT,
mantissa INT
)
CREATE TABLE Constraints (
ID INT PRIMARY KEY,
type INT NOT NULL
)
CREATE TABLE Fields_Constraints (
fieldID INT FOREIGN KEY REFERENCES Fields(id) ON DELETE CASCADE,
constraintID INT FOREIGN KEY REFERENCES Constraints(id) ON DELETE CASCADE
)
