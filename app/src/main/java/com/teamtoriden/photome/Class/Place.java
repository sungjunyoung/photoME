package com.teamtoriden.photome.Class;

import android.graphics.drawable.Drawable;

/**
 * Created by Junyoung on 16. 6. 28..
 */
public class Place {
    private String name;
    private String description;
    private double x;
    private double y;
    private String image;
    private boolean flag;
    private int id;

    public Place(){

    }

    public Place(String name, String description, String image, boolean flag, double x, double y) {
        this.name = name;
        this.description = description;
        this.x = x;
        this.y = y;
        this.image = image;
        this.flag = flag;
        this.id = -1;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getImage() {
        return image;
    }


    public void setImage(String image) {
        this.image = image;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
