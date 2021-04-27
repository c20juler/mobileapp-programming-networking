package com.example.networking;

public class Mountain {
    private String ID;
    private String name;
    private String type;
    private String company;
    private String location;
    private String category;
    @SerializedName("size")
    private int meters;
    @SerializedName("cost")
    private int feet;
    private Auxdata auxdata;
}
