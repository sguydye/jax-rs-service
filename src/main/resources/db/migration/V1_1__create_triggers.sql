CREATE PROCEDURE pr_createdDateTrigger @tbl nvarchar(30)
AS
BEGIN
    DECLARE @sql NVARCHAR(MAX) = 'CREATE TRIGGER dateCreated' + QUOTENAME(@tbl) + ' ' +
                                  'ON ' + QUOTENAME(@tbl) + ' ' +
                                  'AFTER INSERT AS
                                      UPDATE ' + QUOTENAME(@tbl) + ' ' +
                                      'SET created = GETDATE()
                                      WHERE ID IN (SELECT ID FROM Inserted) '
     EXEC sp_executesql @sql
END
GO

CREATE PROCEDURE pr_lastModifiedTrigger @tbl nvarchar(30)
AS
BEGIN
    DECLARE @sql NVARCHAR(MAX) = 'CREATE TRIGGER lastModified' + @tbl + ' ' +
                                  'ON ' + @tbl + ' ' +
                                  'AFTER UPDATE AS
                                      UPDATE ' + @tbl + ' ' +
                                      'SET lastModified = GETDATE()
                                      WHERE ID IN (SELECT ID FROM Inserted) '
     EXEC sp_executesql @sql
END
GO

EXEC pr_createdDateTrigger @tbl = 'Field'
EXEC pr_lastModifiedTrigger @tbl = 'Field'
EXEC pr_createdDateTrigger @tbl = 'Entity'
EXEC pr_lastModifiedTrigger @tbl = 'Entity'
GO

