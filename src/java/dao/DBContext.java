/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author huypd
 */
public class DBContext {

    protected Connection connection;
    public DBContext()
    {
        try {
            // Edit URL , username, password to authenticate with your MS SQL Server
//            String url = "jdbc:sqlserver://DESKTOP-JGD1LSU:1433;databaseName=SellCourses;encrypt=true;trustServerCertificate=true;sslProtocol=TLS;authentication=SqlPassword";
            String url = "jdbc:sqlserver://DESKTOP-K0IAB0C:1433;databaseName=SellCourses;encrypt=true;trustServerCertificate=true;sslProtocol=TLS;authentication=SqlPassword";
//            String url = "jdbc:sqlserver://DESKTOP-JGD1LSU:1433;databaseName=SellCourses;encrypt=true;trustServerCertificate=true;sslProtocol=TLS;authentication=SqlPassword";
            String username = "sa";
            String password = "12345678";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
    }
}
