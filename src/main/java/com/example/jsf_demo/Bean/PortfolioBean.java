package com.example.jsf_demo.Bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.sound.sampled.Port;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@ManagedBean
@ViewScoped
public class PortfolioBean {

    private List<Portfolio> portfolioList;

    private boolean editMode;

    private Portfolio portfolio;

    public PortfolioBean(){init();}

    public void init(){
        this.editMode = false;
        this.portfolioList =fetchPortfoliosFromDatabase();
        this.portfolio= new Portfolio("","","","","","","","");
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void enableEditMode() {
        System.out.println("Editing enabled");editMode = true;
    }

    public List<Portfolio> getPortfolioList() {
        return portfolioList;
    }

    public void setPortfolioList(List<Portfolio> portfolioList) {
        this.portfolioList = portfolioList;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public void getPortfolioByAccount(String account){
        boolean res=false;

        for(Portfolio temp:this.portfolioList){
            if(temp.getAccount().equals(account)){
                    res=true;
                    portfolio=temp;
                    break;
            }
        }

    }


//    public String insertUser(String account,String passwords) {
//        // 获取用户输入的新项目信息
//
//        user.setAccount(account);
//        user.setPasswords(passwords);
//
//        // 执行保存逻辑，更新数据库中的项目数据
//        insertUserFromDatabase(user);
//
//        return "projectpage.jsf";
//        //记得更改成提示页面哦！！！
//    }
//
//    public void insertUserFromDatabase(User user) {
//
//        System.out.println("This is ProjectID "+user.getAccount());
//
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        String url = "jdbc:mysql://localhost:3306/web2023?autoReconnect=true&useSSL=false&useServerPrepStmts=true";
//        String username = "root";
//        String password = "AlanWake752412";
//        String sql = "INSERT INTO web2023.USER (Account, Passwords) VALUES (?, ?)";
//
//        try (Connection connection = DriverManager.getConnection(url, username, password)) {
//            PreparedStatement statement = connection.prepareStatement(sql);
//
//            statement.setString(1, user.getAccount());
//            statement.setString(2, user.getPasswords());
//            statement.executeUpdate();
//
//            connection.close();
//            statement.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
public String saveChanges(String account,String paccount,String name,String gender,String age,String university,String major,String IDnumber,String content) {

        if (paccount!= null && paccount!=""){
            System.out.println("This is the PACCOUNT"+paccount);
            updateProjectFromDatabase(new Portfolio(account,name,gender,age,university,major,IDnumber,content));
        }else{
            addProjectFromDatabase(new Portfolio(account,name,gender,age,university,major,IDnumber,content));
        }

    return "homepage.jsf";
}

    public void addProjectFromDatabase(Portfolio portfolio) {

        System.out.println("Adding project for "+portfolio.getAccount());

        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception e){
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/web2023?autoReconnect=true&useSSL=false&useServerPrepStmts=true";
        String username = "root";
        String password = "AlanWake752412";
        String sql = "INSERT INTO web2023.PORTFOLIO (Account, Name, Gender, Age, University, Major, IDnumber, Content) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, portfolio.getAccount());
            statement.setString(2, portfolio.getName());
            statement.setString(3, portfolio.getGender());
            statement.setString(4, portfolio.getAge());
            statement.setString(5, portfolio.getUniversity());
            statement.setString(6, portfolio.getMajor());
            statement.setString(7, portfolio.getIDnumber());
            statement.setString(8, portfolio.getContent());

            statement.executeUpdate();

            connection.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void updateProjectFromDatabase(Portfolio portfolio) {

        System.out.println("This is ProjectID FInallly"+portfolio.getAccount());

        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception e){
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/web2023?autoReconnect=true&useSSL=false&useServerPrepStmts=true";
        String username = "root";
        String password = "AlanWake752412";
        String sql = "UPDATE web2023.PORTFOLIO SET Name=?, Gender=?, Age=?, University=?, Major=?, IDnumber=?, Content=? WHERE Account=?";

        try (Connection connection = DriverManager.getConnection(url, username, password)){
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, portfolio.getName());
            statement.setString(2, portfolio.getGender());
            statement.setString(3, portfolio.getAge());
            statement.setString(4, portfolio.getUniversity());
            statement.setString(5, portfolio.getMajor());
            statement.setString(6, portfolio.getIDnumber());
            statement.setString(7, portfolio.getContent());
            statement.setString(8, portfolio.getAccount());

            statement.executeUpdate();

            connection.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private List<Portfolio> fetchPortfoliosFromDatabase() {
        List<Portfolio> portfolios = new ArrayList<Portfolio>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/web2023?autoReconnect=true&useSSL=false&useServerPrepStmts=true";
        String username = "root";
        String password = "AlanWake752412";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM web2023.PORTFOLIO;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String account = resultSet.getString("Account");
                String name = resultSet.getString("Name");
                String gender = resultSet.getString("Gender");
                String age = resultSet.getString("Age");
                String university = resultSet.getString("University");
                String major = resultSet.getString("Major");
                String IDnumber = resultSet.getString("IDnumber");
                String content = resultSet.getString("Content");


                Portfolio portfolio = new Portfolio(account, name,gender,age,university,major,IDnumber,content);


                portfolios.add(portfolio);
            }

            connection.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return portfolios;
    }




}
