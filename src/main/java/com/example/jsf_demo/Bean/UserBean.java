package com.example.jsf_demo.Bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@ManagedBean
@ViewScoped
public class UserBean {

    private List<User> userList;

    private User user;

    public UserBean(){init();}

    public void init(){
        this.userList =fetchUsersFromDatabase();
        this.user= new User("","");
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String matchUser(String account, String passwords){
        boolean res=false;
        System.out.println("This is your input"+passwords);

        for(User temp:this.userList){
            System.out.println("THis is the whole "+ temp.getAccount());
            if(temp.getAccount().equals(account)){
                if(temp.getPasswords().equals(passwords)){
                    res=true;
                    user.setAccount(account);
                    user.setPasswords(passwords);
                    break;
                }
            }
        }

        if(!res){
            return "login.jsf";
        }else{


            return "homepage.jsf";
        }
    }


    public String insertUser(String account,String passwords) {
        // get data of new project from form

        user.setAccount(account);
        user.setPasswords(passwords);

        //refresh data in database
        insertUserFromDatabase(user);

        return "login.jsf";
    }

    public void insertUserFromDatabase(User user) {

        System.out.println("This is ProjectID "+user.getAccount());

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/web2023?autoReconnect=true&useSSL=false&useServerPrepStmts=true";
        String username = "root";
        String password = "AlanWake752412";
        String sql = "INSERT INTO web2023.USER (Account, Passwords) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, user.getAccount());
            statement.setString(2, user.getPasswords());
            statement.executeUpdate();

            connection.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private List<User> fetchUsersFromDatabase() {
        List<User> users = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/web2023?autoReconnect=true&useSSL=false&useServerPrepStmts=true";
        String username = "root";
        String password = "123456";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM web2023.USER;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String account = resultSet.getString("Account");
                String passwords = resultSet.getString("Passwords");


                User user = new User(account, passwords);


                users.add(user);
            }

            connection.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }




}
