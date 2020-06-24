package com.example.mahmoud_fetouh_task.model;

public class Data {

    public Integer id;
    public String brand;
    public String constructionYear;
    public Boolean isUsed;
    public String imageUrl;

    public Data(Integer id, String brand, String constructionYear, Boolean isUsed, String imageUrl) {
        this.id = id;
        this.brand = brand;
        this.constructionYear = constructionYear;
        this.isUsed = isUsed;
        this.imageUrl = imageUrl;
    }
}

