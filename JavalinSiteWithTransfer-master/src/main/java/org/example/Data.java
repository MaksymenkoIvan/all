package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(HOST, USER, PASS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String connect(String login) {
        String result = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
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

    public boolean register(String login, String pass, String repass){
        if (pass.equals(repass)){
            try {
                PreparedStatement insert  = connection.prepareStatement("INSERT INTO users (login, pass)" + "VALUES(?,?)");
                insert.setString(1,login);
                insert.setString(2,pass);
                insert.executeUpdate();
            }catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }else{
            System.out.println("pass != re-pass");
            return false;
        }
    }

    public boolean auth(String username, String pass) {
        try {
            PreparedStatement select = connection.prepareStatement("SELECT pass FROM users WHERE login=?");
            select.setString(1, username);
            ResultSet set = select.executeQuery();
            while (set.next()){
                if (set.getString(1).equals(pass)){
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public int getId(String login){
        int myid = 0;
        try{
            PreparedStatement select = connection.prepareStatement(
                    "SELECT id FROM users WHERE login = ?");
            select.setString(1, login);
            ResultSet set = select.executeQuery();
            while (set.next()){
                myid = set.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return myid;
    }

    public double getBalance(String login){
        double balance = 0;
        try{
            PreparedStatement select = connection.prepareStatement(
                    "SELECT balance FROM users WHERE login = ?");
            select.setString(1, login);
            ResultSet set = select.executeQuery();
            while (set.next()){
                 balance = set.getDouble(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return balance;
    }
    public void changelogin(String cLogin, String fLogin){
        try{
            PreparedStatement first = connection.prepareStatement(
                    "UPDATE users SET login =  ? WHERE login = ?"
            );
            first.setString(1, fLogin);
            first.setString(2, cLogin);
            first.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void setbalance(double balance, String login){
        try{
            PreparedStatement first = connection.prepareStatement(
                    "UPDATE users SET balance = balance + ? WHERE login = ?"
            );
            first.setDouble(1, balance);
            first.setString(2, login);
            first.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public String getUserName(String login){
        try{
            PreparedStatement select = connection.prepareStatement("SELECT login FROM users WHERE users.id = ?");
            select.setString(1, login);
            ResultSet set = select.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
        }
        return login;
    }

    public void transfer(int id, int uid,
                         double balance, double ubalance){
        try{
            PreparedStatement first = connection.prepareStatement(
                    "UPDATE users SET balance = balance - ? WHERE id = ?"
            );
            first.setDouble(1, ubalance);
            first.setInt(2, id);
            first.executeUpdate();
            PreparedStatement second = connection.prepareStatement(
                    "UPDATE users SET balance = balance + ? WHERE id = ?");
            second.setDouble(1, ubalance);
            second.setInt(2, uid);
            second.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}