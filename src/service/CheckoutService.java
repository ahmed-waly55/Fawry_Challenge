package service;

import cart.Cart;
import interfaces.Shippable;
import model.Customer;
import model.Product;

import java.util.*;

public class CheckoutService {
    public static void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) throw new IllegalStateException("Cart is empty");

        double subtotal = 0;
        List<Shippable> itemsToShip = new ArrayList<>();

        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int qty = entry.getValue();

            if (product.isExpired()) throw new IllegalStateException(product.getName() + " is expired.");
            if (qty > product.getQuantity()) throw new IllegalStateException(product.getName() + " out of stock.");

            subtotal += product.getPrice() * qty;

            if (product instanceof Shippable) {
                for (int i = 0; i < qty; i++) {
                    itemsToShip.add((Shippable) product);
                }
            }
        }

        double totalWeight = itemsToShip.stream().mapToDouble(Shippable::getWeight).sum();
        double shipping = Math.ceil(totalWeight / 0.5) * 10;

        double total = subtotal + shipping;

        if (customer.getBalance() < total) throw new IllegalStateException("Insufficient balance");

        customer.pay(total);

        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            entry.getKey().reduceQuantity(entry.getValue());
        }

        if (!itemsToShip.isEmpty()) {
            ShippingService.ship(itemsToShip);
        }

        System.out.println("\n** Checkout receipt **");
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            System.out.printf("%dx %s\t%.0f\n", entry.getValue(), entry.getKey().getName(), entry.getValue() * entry.getKey().getPrice());
        }
        System.out.println("----------------------");
        System.out.printf("Subtotal\t%.0f\n", subtotal);
        System.out.printf("Shipping\t%.0f\n", shipping);
        System.out.printf("Amount\t\t%.0f\n", total);
    }
}
