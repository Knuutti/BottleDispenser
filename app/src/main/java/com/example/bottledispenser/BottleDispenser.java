package com.example.bottledispenser;

import android.view.View;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class BottleDispenser {
    private static BottleDispenser bottleDispenser;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private double money = 0.00;
    private String billString = "***KUITTI***\n\n";
    protected ArrayList<Bottle> bottle_array = new ArrayList<>();

    public static BottleDispenser getInstance() {
        if (bottleDispenser == null) {
            bottleDispenser = new BottleDispenser();
        }
        return bottleDispenser;
    }

    private BottleDispenser() {
        money = 0.00;
        bottle_array.add(new Bottle("Pepsi Max", 0.5, 1.8));
        bottle_array.add(new Bottle("Pepsi Max", 1.5, 2.2));
        bottle_array.add(new Bottle("Coca-Cola Zero", 0.5, 2.0));
        bottle_array.add(new Bottle("Coca-Cola Zero", 1.5, 2.5));
        bottle_array.add(new Bottle("Fanta Zero", 0.5, 1.95));
    }

    public void addMoney(int moneyToAdd) {
        money += moneyToAdd;
        System.out.println("Klink! Added more money!");
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getBillString() {
        return billString;
    }

    public void setBillString() {
        this.billString = "***KUITTI***\n\n";
    }

    public ArrayList<Bottle> getBottleArray() {
        return bottle_array;
    }

    public void buyBottle(int bottleChoice) {
        if (money < bottle_array.get(bottleChoice).getPrize()) {

        }
        else {
            money -= bottle_array.get(bottleChoice).getPrize();
            System.out.println("KACHUNK! " + bottle_array.get(bottleChoice).getName() + " came out of the dispenser!");
            billString = "***KUITTI***\n\n"
                    + bottle_array.get(bottleChoice).getName() + "\t"
                    + bottle_array.get(bottleChoice).getSize() + "\t"
                    + bottle_array.get(bottleChoice).getPrize() + "\n";
            bottle_array.remove(bottleChoice);
        }
    }

    public void returnMoney() {
        System.out.println("Klink klink. Money came out! You got " + df.format(money) + "€ back");
        money = 0;
    }

    public String getMoney() {
        return df.format(money);
    }

}