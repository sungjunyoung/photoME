package com.teamtoriden.photome.Class;

import android.graphics.drawable.Drawable;
import android.media.Image;

/**
 * Created by Junyoung on 16. 6. 28..
 */
public class Place {
    private String name;
    private String description;
    private int x;
    private Drawable image;

    private int y;

    public Place(String name, String description){
        this.name = name;
        this.description = description;
        this.x = 0;
        this.y = 0;
        this.image = null;
    }
    public Place(String name, String description, Drawable image){
        this.name = name;
        this.description = description;
        this.x = 0;
        this.y = 0;
        this.image = image;
    }

    public Place(String name, String description, int x, int y, Drawable image) {
        this.name = name;
        this.description = description;
        this.x = x;
        this.y = y;
        this.image = image;
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

    public Drawable getImage() {
        return image;
    }


    public void setImage(Drawable image) {
        this.image = image;
    }
}
