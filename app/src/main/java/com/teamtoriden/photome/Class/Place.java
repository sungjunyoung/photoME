package com.teamtoriden.photome.Class;

import android.media.Image;

/**
 * Created by Junyoung on 16. 6. 28..
 */
public class Place {
    private String name;
    private String description;
    private int x;
    private Image image;

    private int y;

    public Place(String name, String description){
        this.name = name;
        this.description = description;
        this.x = 0;
        this.y = 0;
    }

    public Place(String name, String description, int x, int y) {
        this.name = name;
        this.description = description;
        this.x = x;
        this.y = y;
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImage() {
        return image;
    }


    public void setImage(Image image) {
        this.image = image;
    }
}
