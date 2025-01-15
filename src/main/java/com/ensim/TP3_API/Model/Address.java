package com.ensim.TP3_API.Model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Address {
    @Id
    @GeneratedValue
    private Long id;
    private Date creation;
    private String content;

    public void setCreation(Date _creation){
        creation = _creation;
    }

    public void setContent(String _content){
        content = _content;
    }

    public Date getCreation(){
        return creation;
    }

    public String getContent(){
        return content;
    }

}