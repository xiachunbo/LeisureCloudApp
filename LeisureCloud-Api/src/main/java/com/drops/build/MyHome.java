package com.drops.build;

public class MyHome {
    private String door;

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    @Override
    public String toString() {
        return "MyHome{" +
                "door='" + door + '\'' +
                '}';
    }
}
