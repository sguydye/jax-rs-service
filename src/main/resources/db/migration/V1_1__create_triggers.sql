IF EXISTS ( SELECT  *
            FROM    sys.procedures
            WHERE   object_id = OBJECT_ID(N'pr_created_date_trigger') )
DROP PROCEDURE [dbo].[pr_created_date_trigger]
GO

CREATE PROCEDURE pr_created_date_trigger
AS
BEGIN

	 DECLARE @tableNames TABLE(name varchar(100) NOT NULL);
	 DECLARE @tbl nvarchar(100);
	 DECLARE @sql NVARCHAR(MAX);
	 DECLARE @triggerName nvarchar(100);
	 DECLARE @ParmDefinition nvarchar(500);
     SET @ParmDefinition =  N'@TRGRNM nvarchar(100), @TR nvarchar(10)'

	 INSERT INTO @tableNames
		SELECT t.name
		FROM sys.columns AS c
		INNER JOIN sys.tables AS t ON c.object_id = t.object_id
		WHERE c.name LIKE '%created%'

	 DECLARE c1 CURSOR READ_ONLY
		FOR
		SELECT name
		FROM @tableNames

	OPEN c1

	FETCH NEXT FROM c1
	INTO @tbl

	WHILE @@FETCH_STATUS = 0
		BEGIN
		    SET @triggerName = N'date_created_' + @tbl
		    SET @sql = 'IF OBJECT_ID(@TRGRNM, @TR) IS NOT NULL ' +
                        'DROP TRIGGER [dbo].' + QUOTENAME(@triggerName)
            EXEC sp_executesql @sql, @ParmDefinition, @TRGRNM = @triggerName, @TR = N'TR'
			SET @sql = 'CREATE TRIGGER ' + @triggerName + ' ' +
                       'ON ' + QUOTENAME(@tbl) + ' ' +
                       'AFTER INSERT AS
                       UPDATE ' + QUOTENAME(@tbl) + ' ' +
                       'SET created = GETDATE()
                       WHERE ID IN (SELECT ID FROM Inserted) '
			EXEC sp_executesql @sql
			FETCH NEXT FROM c1
			INTO @tbl
		END

	CLOSE c1
	DEALLOCATE c1

END
GO

IF EXISTS ( SELECT  *
            FROM    sys.procedures
            WHERE   object_id = OBJECT_ID(N'pr_last_modified_trigger') )
DROP PROCEDURE [dbo].[pr_last_modified_trigger]
GO

CREATE PROCEDURE pr_last_modified_trigger
AS
BEGIN

	 DECLARE @tableNames TABLE(name varchar(100) NOT NULL);
	 DECLARE @tbl varchar(100);
	 DECLARE @sql NVARCHAR(MAX);
	 DECLARE @triggerName nvarchar(100);
     DECLARE @ParmDefinition nvarchar(500);
     SET @ParmDefinition =  N'@TRGRNM nvarchar(100), @TR nvarchar(10)'

	 INSERT INTO @tableNames
     	SELECT t.name
     	FROM sys.columns AS c
     	INNER JOIN sys.tables AS t ON c.object_id = t.object_id
     	WHERE c.name LIKE '%lastModified%'


	 DECLARE c1 CURSOR READ_ONLY
		FOR
		SELECT name
		FROM @tableNames

	 OPEN c1

	 FETCH NEXT FROM c1
	 INTO @tbl

	 WHILE @@FETCH_STATUS = 0
		BEGIN
		     SET @triggerName = N'last_modified_' + @tbl
        	 SET @sql = 'IF OBJECT_ID(@TRGRNM, @TR) IS NOT NULL ' +
                        'DROP TRIGGER [dbo].' + QUOTENAME(@triggerName)
             EXEC sp_executesql @sql, @ParmDefinition, @TRGRNM = @triggerName, @TR = N'TR'
		     SET @sql = 'CREATE TRIGGER ' + @triggerName + ' ' +
                        'ON ' + QUOTENAME(@tbl) + ' ' +
                        'AFTER UPDATE AS
                           UPDATE ' + QUOTENAME(@tbl) + ' ' +
                           'SET lastModified = GETDATE()
                           WHERE ID IN (SELECT ID FROM Inserted) '
			EXEC sp_executesql @sql
			FETCH NEXT FROM c1
			INTO @tbl
		END

	 CLOSE c1
	 DEALLOCATE c1

END
GO

EXEC pr_created_date_trigger
EXEC pr_last_modified_trigger
GO