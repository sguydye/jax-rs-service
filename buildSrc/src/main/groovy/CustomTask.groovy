package org.gradle

import groovy.sql.Sql
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import java.sql.SQLException

class SQLTask extends DefaultTask {

    @TaskAction
    def run() {
        Properties properties = new Properties()
        File propertiesFile = new File('gradle.properties');
        propertiesFile.withInputStream {
            properties.load(it)
        }
        String dbName;
        properties.getProperty("flyway.url").split(";").each {
            if (it.contains("databaseName"))
                dbName = it.substring(13)
            else if (it.contains("jdbc:"))
                properties.setProperty("flyway.url", it);
        }

        Sql sql = Sql.newInstance(properties.getProperty("flyway.url"), properties.getProperty("flyway.user"),
                properties.getProperty("flyway.password"), properties.getProperty("flyway.driver"));

        def query = "IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = $dbName) CREATE DATABASE ${Sql.expand(dbName)}"
        try {
            sql.execute(query)
        } catch (SQLException e) {
            logger.quiet e.message
        } finally {
            sql.close()
        }
    }
}