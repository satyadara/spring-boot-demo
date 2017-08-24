package com.satyadara.springdemo.repository;

import com.satyadara.springdemo.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgreSQLJDBC {
    private Connection connection;

    public PostgreSQLJDBC() {
        try {
            Class.forName("com.postgresql.Driver");
        } catch (ClassNotFoundException e)  {
            System.out.println("Driver not found : " + e.toString());
            e.printStackTrace();
        }

        connection = null;
    }

    public boolean createConnection()   {
        String url = "http://localhost:5432/springdemo";
        String username = "postgres";
        String password = "satyadara";

        boolean isConnected = true;

        try {
            connection = DriverManager.getConnection(url, username,password);
        } catch (SQLException e)    {
            System.out.println("Terjadi kesalahan : " + e.toString());
            e.getStackTrace();
            isConnected = false;
        }

        return  isConnected;
    }

    public boolean closeConnection()    {
        boolean isConnected = true;

        try {
            connection.close();
        }catch (SQLException e) {
            System.out.println("Terjadi kesalahan : " + e.toString());
            e.getStackTrace();
            isConnected = false;
        }

        return isConnected;
    }

    private Statement createStatement() {
        Statement statement = null;

        try {
            statement = connection.createStatement();
        }catch (SQLException e) {
            System.out.println("Terjadi kesalahan : " + e.toString());
            e.getStackTrace();
        }

        return statement;
    }

    /*********************************** SQL ***********************************/
    public ArrayList<User> getAlluser()  {
        ArrayList<User> users = new ArrayList<>();
        Statement statement = createStatement();

        if (statement != null)  {
            String sql = "SELECT * FROM users";

            try {
                ResultSet resultSet = null;
                resultSet = statement.executeQuery(sql);

                while (resultSet.next())    {
                    User user = new User();

                    user.setId(resultSet.getLong(1));
                    user.setName(resultSet.getString(2));
                    user.setAge(resultSet.getString(3));

                    users.add(user);
                }
            } catch (SQLException e) {
                System.out.println("Terjadi kesalahan, gagal mengambil data : " + e.toString());
                e.getStackTrace();
                users = null;
            }
        } else {
            System.out.println("Null Statement");
            users = null;
        }
        return users;
    }

    public User getAUser(Long id)  {
        User user = new User();
        Statement statement = createStatement();

        if (statement != null)  {
            String sql =    "SELECT * FROM users " +
                            "WHERE id_user = " + id;

            try {
                ResultSet resultSet = null;
                resultSet = statement.executeQuery(sql);

                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setAge(resultSet.getString(3));
            } catch (SQLException e) {
                System.out.println("Terjadi kesalahan, gagal mengambil data : " + e.toString());
                e.getStackTrace();
            }
        } else {
            System.out.println("Null Statement");
        }
        return user;
    }

    public void insertAUser(User user)    {
        Statement statement = createStatement();

        if (statement != null)  {
            String sql =    "INSERT INTO users " +
                            "(id_user, name, age) VALUES (" +
                            user.getId() + "," + user.getName() + "," + user.getAge()
                            + ")";
            try {
                statement.executeQuery(sql);
            } catch (SQLException e)    {
                System.out.println("Terjadi kesalahan, gagal menyimpan data : " + e.toString());
                System.out.println(e.getSQLState());
                e.getStackTrace();
            }
        } else {
            System.out.println("Null Statement");
        }
    }
}
