package com.trademax.Models;

public class DemoGridItems {

    private String firstname;
    private String secondname;
    private int image;

    public DemoGridItems(String firstname, String secondname, int image) {
        this.firstname = firstname;
        this.secondname = secondname;
        this.image = image;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
