CREATE PROCEDURE pr_createdDateTrigger @tbl NVARCHAR(128)
AS
BEGIN
     DECLARE @sql NVARCHAR(MAX);
     SET @sql = 'CREATE TRIGGER dateCreated' + @tbl + ' ' +
                                  'ON ' + QUOTENAME(@tbl) + ' ' +
                                  'AFTER INSERT AS
                                      UPDATE ' + QUOTENAME(@tbl) + ' ' +
                                      'SET created = GETDATE()
                                      WHERE ID IN (SELECT ID FROM Inserted) '
     EXEC sp_executesql @sql
END
GO

CREATE PROCEDURE pr_lastModifiedTrigger @tbl NVARCHAR(128)
AS
BEGIN

     DECLARE @sql NVARCHAR(MAX);
     SET @sql = 'CREATE TRIGGER lastModified' + @tbl + ' ' +
                                  'ON ' + QUOTENAME(@tbl) + ' ' +
                                  'AFTER UPDATE AS
                                      UPDATE ' + QUOTENAME(@tbl) + ' ' +
                                      'SET lastModified = GETDATE()
                                      WHERE ID IN (SELECT ID FROM Inserted) '
     EXEC sp_executesql @sql
END
GO

EXEC pr_createdDateTrigger @tbl = N'Entity'
EXEC pr_createdDateTrigger @tbl = N'Field'
EXEC pr_createdDateTrigger @tbl = N'Constraint'
EXEC pr_lastModifiedTrigger @tbl = N'Entity'
EXEC pr_lastModifiedTrigger @tbl = N'Field'
EXEC pr_lastModifiedTrigger @tbl = N'Constraint'
GO

