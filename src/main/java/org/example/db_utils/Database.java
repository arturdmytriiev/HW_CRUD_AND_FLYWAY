package org.example.db_utils;


import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
 private static Database instance;
 private Connection connection;
 private static final String DB_URL = "jdbc:h2:file:D:/JDBCDB/H2JDBC";


 private Database() {
     try{
        this.connection = DriverManager.getConnection(DB_URL);
         Flyway.configure()
                 .dataSource("jdbc:h2:file:D:/JDBCDB/H2JDBC","","")
                 .locations("db/migration")
                 .load()
                 .migrate();
     }
     catch(SQLException ex){
         throw new RuntimeException("Could not connect to H2 database", ex);
     }
 }
 public static Database getInstance() {
     if (instance == null) {
         instance = new Database();
     }
     return instance;
 }

 public Connection getConnection() {
     return connection;
 }
}
