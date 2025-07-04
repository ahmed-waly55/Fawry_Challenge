package service;

import interfaces.Shippable;

import java.util.List;

import java.util.*;

public class ShippingService {
    public static void ship(List<Shippable> items) {
        Map<String, Double> productWeights = new LinkedHashMap<>();
        Map<String, Integer> productCounts = new LinkedHashMap<>();

        for (Shippable item : items) {
            String name = item.getName();
            productWeights.put(name, item.getWeight());
            productCounts.put(name, productCounts.getOrDefault(name, 0) + 1);
        }

        System.out.println("** Shipment notice **");
        double totalWeight = 0;

        for (String name : productWeights.keySet()) {
            int count = productCounts.get(name);
            double singleWeight = productWeights.get(name);
            double totalItemWeight = singleWeight * count;
            System.out.printf("%dx %s\t\t%.0fg\n", count, name, totalItemWeight * 1000);
            totalWeight += totalItemWeight;
        }

        System.out.printf("Total package weight %.1fkg\n", totalWeight);
    }
}
