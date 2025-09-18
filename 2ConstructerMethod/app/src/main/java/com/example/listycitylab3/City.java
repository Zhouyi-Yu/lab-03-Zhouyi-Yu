package com.example.listycitylab3;

import androidx.annotation.NonNull;

public class City {
    private String name;
    private String province;
    public City(String name, String province){
        this.name = name;
        this.province = province;
    }

    //getters+setters
    public String getName(){return name;}
    public String getProvince(){return province;}
    public void setName(String name){this.name = name;}
    public void setProvince(String province){this.province = province;}

    @NonNull
    @Override
    public String toString(){
        return name + " " + province;
    }
}
