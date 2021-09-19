package com.example.clashroyale.db;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlCon {
     String db_name = "namdb";
    String mysql_ip = "150.117.238.70";
//    String mysql_ip = "192.168.0.1";
    String mysql_port = "3306";
    String username = "isaac";
    String password = "abc";
    String url = "jdbc:mysql://"+mysql_ip+":"+mysql_port+"/"+db_name;
    Connection connection;

    public void run()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Log.v("DB", "LOAD SUCCESSFUL");
        }
        catch (ClassNotFoundException e)
        {
            Log.e("DB", "LOADED FAILED");
            return;
        }

        try
        {

            Connection con = DriverManager.getConnection(url, username, password);
            Log.v("DB", "Remote Connection SUCCESS");
        }
        catch (SQLException e)
        {
            Log.e("DB", "Remote Connection FAILED");
            Log.e("DB", e.toString());
        }

    }

    public String getData()
    {
        String data = "";
        try
        {
            connection = DriverManager.getConnection(url, username, password);
            String sql = "SELECT * FROM customer";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next())
            {
                String firstname = result.getString(2);
                String lastName = result.getString("lastName");
                data += firstname + ", " + lastName + "\n";
            }
            statement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return data;
    }

}
