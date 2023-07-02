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
public class RenderBean{

    private String power;

    private boolean render;

    public RenderBean(){
        this.power=fetchProjectsFromDatabase();
    }

    public boolean getRender(String render){
        return !render.equals("false");
    }
    public boolean getURender(String render){
        return render.equals("false");
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public boolean isRender() {
        System.out.println("Current power: "+power);
        this.render= !this.power.equals("false");

        return this.render;
    }


    public void setRenderFalse() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception e){
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/web2023?autoReconnect=true&useSSL=false&useServerPrepStmts=true";
        String username = "root";
        String password = "AlanWake752412";
        String sql = "UPDATE web2023.RENDER SET Render=? WHERE Render=true";

        try (Connection connection = DriverManager.getConnection(url, username, password)){
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, "false");
            statement.executeUpdate();
            connection.close();
            statement.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String fetchProjectsFromDatabase(){
        String res=null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception e){
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/web2023?autoReconnect=true&useSSL=false&useServerPrepStmts=true";
        String username = "root";
        String password = "AlanWake752412";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM web2023.RENDER;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                res = resultSet.getString("Render");
                System.out.println("Current result: "+res);
            }
            connection.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
