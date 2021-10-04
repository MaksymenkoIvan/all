package sample.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Data extends Config {
    private Scanner scanner = new Scanner(System.in);

    public String connect(String login) {
        String result = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(HOST, USER, PASS);
            PreparedStatement select = connection.prepareStatement("SELECT pass FROM users WHERE login = ?");
            select.setString(1, login);
            ResultSet list = select.executeQuery();
            System.out.println("Success");
            while (list.next()) {
                result = list.getString(1);
                System.out.println(result);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public void auth() {
        String login = scanner.nextLine();
        String password = scanner.nextLine();
        if (password.equals(connect(login))) {
            System.out.println("authorized");
        } else {
            System.out.println("Incorrect password");
        }
    }

    public void insertNewMessage(String name, String message) {
        String result = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(HOST, USER, PASS);
            PreparedStatement insert = connection.prepareStatement("INSERT INTO messages(username,message)" + "VALUES(?,?)");
            insert.setString(1, name);
            insert.setString(2, message);
            insert.executeUpdate();
            System.out.println("Success Insert");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}