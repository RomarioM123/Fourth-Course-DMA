package com.ntukhpi.otp.momot.fourth_course_dma.lab5.navbar;

public class NavbarModel {
    private int icon;
    private String name;
    public NavbarModel(int icon, String name) {
        this.icon = icon;
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
