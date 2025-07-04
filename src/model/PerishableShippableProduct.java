package model;

import interfaces.Shippable;

import java.time.LocalDate;

public class PerishableShippableProduct extends PerishableProduct implements Shippable {
    private double weight;

    public PerishableShippableProduct(String name, double price, int quantity, double weight, LocalDate expiryDate) {
        super(name, price, quantity, expiryDate);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }
}
