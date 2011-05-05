package vooga.user.model.database;

import java.sql.*;

public class mySQLConnection{
  public static void main(String[] args) {
    System.out.println("MySQL Connect Example.");
    Connection conn = null;
    String url = "jdbc:mysql://64.120.149.69:3306";
    String dbName = "/a8014122_vooga";
    String driver = "com.mysql.jdbc.Driver";
    String userName = "a8014122_duke"; 
    String password = "vooga108";
    try {
      Class.forName(driver).newInstance();
      System.out.println("ert");
      conn = DriverManager.getConnection(url+dbName,userName,password);
      System.out.println("Connected to the database");
      conn.close();
      System.out.println("Disconnected from database");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}