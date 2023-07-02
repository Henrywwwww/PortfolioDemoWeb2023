package com.example.jsf_demo.Bean;

public class Project {

    private String account;
    private String user;
    private String id;
    private String title;
    private String summary;
    private String description;
    private String keywords;
    private String type;
    private String collaborators;
    public Project(){}
    public Project(String id,String title,String summary,String description,String keywords,String type,String collaborators) {
            this.id =id;
            this.title = title;
            this.summary = summary;
            this.description = description;
            this.keywords = keywords;
            this.type = type;
            this.collaborators = collaborators;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(String collaborators) {
        this.collaborators = collaborators;
    }

    @Override
    public String toString() {
        return "Project{" +
                "title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", description='" + description + '\'' +
                ", keywords='" + keywords + '\'' +
                ", type='" + type + '\'' +
                ", collaborators='" + collaborators + '\'' +
                '}';
    }
}
