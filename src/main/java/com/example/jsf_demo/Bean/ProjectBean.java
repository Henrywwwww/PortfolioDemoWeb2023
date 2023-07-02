package com.example.jsf_demo.Bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@ManagedBean
@ViewScoped
public class ProjectBean {

    private boolean mainFile = true;

    private boolean secondFile = false;



    private List<Project> projectList = new ArrayList<Project>();

    private Project selectedProject;
    public ProjectBean(){
        init();
    }
    public void init(){
        this.projectList =fetchProjectsFromDatabase();
        this.selectedProject= new Project("","","","","","","");
    }


    public void toggleMainFile() {
        System.out.println("This is main now+"+mainFile);

        this.mainFile = !this.mainFile;
        this.secondFile=!this.secondFile;
        System.out.println("This is main after+"+mainFile);
    }

    public boolean getMainFile() {
        return mainFile;
    }

    public void setMainFile(boolean mainFile) {
        this.mainFile = mainFile;
    }

    public boolean getSecondFile() {
        return secondFile;
    }

    public void setSecondFile(boolean secondFile) {
        this.secondFile = secondFile;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            sb.append(randomChar);
        }

        String randomString = sb.toString();
        return randomString;
    }


    public List<Project> getProjectList() {
        return this.projectList;
    }


    public Project getSelectedProject() {
        return selectedProject;
    }

    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
    }

    public void setProjectListByAccount(String account){
        List<Project>  projects=new ArrayList<Project>();

        for(Project temp:this.projectList){
            if(temp.getAccount() != null){
                if(temp.getAccount().equals(account)){
                    projects.add(temp);
                }
            }

        }

        this.projectList=projects;
    }

    public void getSelectedProjectById(String id){
        Project res=null;
        for (Project project : this.projectList) {
            if(project.getId().equals(id)){
                res = project;
                break;
            }
        }
        this.selectedProject=res;
    }

    public void getSelectedProjectByName(String title){
        Project res=null;
        for (Project project : this.projectList) {
            if(project.getTitle().equals(title)){
                res = project;
                break;
            }
        }

        this.selectedProject=res;
    }

    public String deleteProject(String title) {
        // 获取用户输入的新项目信息
        if(title!=null){
            String newTitle = title;
            getSelectedProjectByName(title);


            // 执行保存逻辑，更新数据库中的项目数据
            if(selectedProject != null){
                deleteProjectFromDatabase(selectedProject);
            }
        }
        return "projectpage.jsf";
    }

    public void deleteProjectFromDatabase(Project project) {

        System.out.println("ProjectID: "+project.getId());
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url = "jdbc:mysql://localhost:3306/web2023?autoReconnect=true&useSSL=false&useServerPrepStmts=true";
        String username = "root";
        String password = "AlanWake752412";
        String sql = "DELETE FROM web2023.PROJECT WHERE Id=?";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, project.getId());
            statement.executeUpdate();
            connection.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String insertProject(String account,String title,String summary,String description,String keywords,String type,String collaborators) {
        // 获取用户输入的新项目信息
        String newTitle = title;
        String newSummary = summary;
        String newDescription = description;
        String newKeywords = keywords;
        String newType = type;
        String newCollaborators = collaborators;

        System.out.println("adding "+account);
        // 更新项目对象的属性
        selectedProject.setAccount(account);
        selectedProject.setId(generateRandomString(10));
        selectedProject.setTitle(newTitle);
        selectedProject.setSummary(newSummary);
        selectedProject.setDescription(newDescription);
        selectedProject.setKeywords(newKeywords);
        selectedProject.setType(newType);
        selectedProject.setCollaborators(newCollaborators);

        // 执行保存逻辑，更新数据库中的项目数据
        if(selectedProject != null){
            insertProjectFromDatabase(selectedProject);
        }


        return "projectpage.jsf";
    }

    public void insertProjectFromDatabase(Project project) {

        System.out.println("This is ProjectID "+project.getId());

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/web2023?autoReconnect=true&useSSL=false&useServerPrepStmts=true";
        String username = "root";
        String password = "AlanWake752412";
        String sql = "INSERT INTO web2023.PROJECT (Title, Summary, Description, Keywords, Type, Collaborators, Id,Account) VALUES (?,?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, project.getTitle());
            statement.setString(2, project.getSummary());
            statement.setString(3, project.getDescription());
            statement.setString(4, project.getKeywords());
            statement.setString(5, project.getType());
            statement.setString(6, project.getCollaborators());
            statement.setString(7, project.getId());
            statement.setString(8, project.getAccount());
            statement.executeUpdate();

            connection.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String saveChanges(String id,String title,String summary,String description,String keywords,String type,String collaborators) {
        // 获取用户输入的新项目信息
        String newTitle = title;
        String newSummary = summary;
        String newDescription = description;
        String newKeywords = keywords;
        String newType = type;
        String newCollaborators = collaborators;

        // 更新项目对象的属性
        selectedProject.setId(id);
        selectedProject.setTitle(newTitle);
        selectedProject.setSummary(newSummary);
        selectedProject.setDescription(newDescription);
        selectedProject.setKeywords(newKeywords);
        selectedProject.setType(newType);
        selectedProject.setCollaborators(newCollaborators);

        // 执行保存逻辑，更新数据库中的项目数据
        updateProjectFromDatabase(selectedProject);

        return "projectDetails.jsf";
    }


    public void updateProjectFromDatabase(Project project) {

        System.out.println("Current ProjectID in updating: "+project.getId());

        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception e){
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/web2023?autoReconnect=true&useSSL=false&useServerPrepStmts=true";
        String username = "root";
        String password = "AlanWake752412";
        String sql = "UPDATE web2023.PROJECT SET Title=?, Summary=?, Description=?, Keywords=?, Type=?, Collaborators=? WHERE Id=?";

        try (Connection connection = DriverManager.getConnection(url, username, password)){
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, project.getTitle());
            statement.setString(2, project.getSummary());
            statement.setString(3, project.getDescription());
            statement.setString(4, project.getKeywords());
            statement.setString(5, project.getType());
            statement.setString(6, project.getCollaborators());
            statement.setString(7, project.getId());


            statement.executeUpdate();

            connection.close();
            statement.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }




    private List<Project> fetchProjectsFromDatabase(){
        List<Project> projects = new ArrayList<>();

        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(Exception e){
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/web2023?autoReconnect=true&useSSL=false&useServerPrepStmts=true";
        String username = "root";
        String password = "AlanWake752412";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM web2023.PROJECT;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("Id");
                String title = resultSet.getString("Title");
                String summary = resultSet.getString("Summary");
                String description = resultSet.getString("Description");
                String keywords = resultSet.getString("Keywords");
                String type = resultSet.getString("Type");
                String collaborators = resultSet.getString("Collaborators");
                String account = resultSet.getString("Account");

                Project project = new Project(id,title, summary, description,keywords,type,collaborators);
                project.setAccount(account);
                projects.add(project);
            }
            connection.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projects;
    }

}


