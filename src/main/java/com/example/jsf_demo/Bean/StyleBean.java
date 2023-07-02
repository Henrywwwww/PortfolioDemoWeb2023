package com.example.jsf_demo.Bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class StyleBean {
    private boolean mainFile ;

    private boolean secondFile ;
    public boolean isMainFile() {
        return mainFile;
    }

    public void setMainFile(boolean mainFile) {
        this.mainFile = mainFile;
    }

    public StyleBean(){
        mainFile = true;
        secondFile= false;
    }
    public void toggleMainFile() {
        System.out.println("This is mainnow!+"+mainFile);

        this.mainFile=!mainFile;
        this.secondFile=!secondFile;

        System.out.println("This is mainafter!+"+mainFile);
    }


    public boolean isSecondFile() {
        return secondFile;
    }

    public void setSecondFile(boolean secondFile) {
        this.secondFile = secondFile;
    }
}

