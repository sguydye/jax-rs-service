package org.gradle

import groovy.sql.Sql
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import java.sql.SQLException

class SQLTask extends DefaultTask {

    @TaskAction
    def run() {
        String url = project.ext["flyway.url"].toString().substring(0, project.ext["flyway.url"].toString().indexOf(";"));

        Sql sql = Sql.newInstance(url, project.ext["flyway.user"],
                project.ext["flyway.password"], project.ext["flyway.driver"]);

        def query1 = "IF EXISTS (SELECT * FROM sys.databases WHERE name = 'metadata') DROP DATABASE metadata"
        def query2 = "CREATE DATABASE metadata"
        try {
            sql.execute(query1)
            sql.execute(query2)
        } catch (SQLException e) {
            logger.quiet e.message
        } finally {
            sql.close()
        }
    }
}