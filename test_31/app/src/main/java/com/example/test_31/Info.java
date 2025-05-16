package com.example.test_31;

import java.io.Serializable;

public class Info implements Serializable {
    private static final long serialVersionUID = 1L;
    private String birthday = "";
    
    public String getBirthday() {
        return birthday;
    }
    
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
} 