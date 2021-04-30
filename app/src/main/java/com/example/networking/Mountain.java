package com.example.networking;

public class Mountain {
    private String ID;
    private String name;
    private String type;
    private String company;
    private String location;
    private String category;
    private int size;
    private int cost;
    private Auxdata auxdata;

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getToastMsg() {
        return "Mountain: "+name+"\nMeters: "+size+"\nFeet: "+cost;
    }
}
