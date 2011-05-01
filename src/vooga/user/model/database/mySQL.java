package vooga.user.model.database;

import java.sql.*;

public class mySQL{
  public static void main(String[] args) {
    System.out.println("MySQL Connect Example.");
    Connection conn = null;
    String url = "jdbc:mysql://www.mysql14.000webhost.com:3307";
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