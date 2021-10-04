package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Data extends  Config {
    private Scanner scanner = new Scanner(System.in);
    private Connection connection = null;
    private List<String> messages = new ArrayList<>();
    private StringBuilder string = new StringBuilder();
    public Data() {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(HOST, USER, PASS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String connect(String login) {
        String result = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(HOST, USER, PASS);
            PreparedStatement select =  connection.prepareStatement("SELECT pass FROM users WHERE login=? ");
            select.setString(1,login);
            ResultSet list = select.executeQuery();
            while (list.next()){
                result = list.getString(1);

                //  System.out.println(list.getInt(1) + "  " + list.getString(2) + "  " + list.getString(3));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return result;

    }

    public boolean register(String login, String pass){
        try {
            PreparedStatement insert  = connection.prepareStatement("INSERT INTO users1 (login, pass)" + "VALUES(?,?)");
            insert.setString(1,login);
            insert.setString(2,pass);
            insert.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean register1(String login){
        try {
            PreparedStatement insert  = connection.prepareStatement("INSERT INTO codes (login)" + "VALUES (?)");
            insert.setString(1,login);
            insert.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}