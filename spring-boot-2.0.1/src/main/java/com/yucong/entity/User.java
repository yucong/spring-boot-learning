package com.yucong.entity;

import java.io.Serializable;

import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="t_user")
public class User implements Serializable {

    private static final long serialVersionUID = 8655851615465363473L;

    private Long id;
    private String username;
    private String password;
  
    public User() {
    }
    
    public User(String username,String password) {
    	this.username = username;
    	this.password = password;
    }
}
