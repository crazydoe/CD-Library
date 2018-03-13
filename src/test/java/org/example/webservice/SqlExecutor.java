package org.example.webservice;

import org.h2.tools.RunScript;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class SqlExecutor {
    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${spring.datasource.username}")
    private String dbUser;
    @Value("${spring.datasource.password}")
    private String dbPass;
    private Connection dbConn;

    public void exec(String resourcePath) throws IOException, SQLException {
        if (dbConn == null) {
            dbConn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        }
        RunScript.execute(dbConn, new FileReader(
                new ClassPathResource(resourcePath).getFile()
        ));
    }
}

