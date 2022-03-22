package com.example.bottledispenser;

public class Bottle {
    private String name = "Pepsi Max";
    private String manufacturer = "Pepsi";
    private double total_energy = 0.3;
    private double size = 0.5;
    private double prize = 1.8;

    public Bottle(){}
    public Bottle(String name, double size, double prize){
        this.name = name;
        this.size = size;
        this.prize = prize;
    }
    @Override
    public String toString() {
        String print = this.name + " | " + this.size + " | " + this.prize + " €";
        return print;
    }

    public String getName(){
        return(name);
    }

    public String getManufacturer(){
        return(manufacturer);
    }

    public double getEnergy(){
        return(total_energy);
    }

    public double getSize(){
        return(size);
    }

    public double getPrize(){
        return(prize);
    }
}