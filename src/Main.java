import cart.Cart;
import model.Customer;
import model.PerishableShippableProduct;
import model.Product;
import model.ShippableProduct;
import service.CheckoutService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("Ahmed", 1000);

        Product cheese = new PerishableShippableProduct("Cheese", 100, 5, 0.2, LocalDate.now().plusDays(3));
        Product biscuits = new PerishableShippableProduct("Biscuits", 150, 3, 0.7, LocalDate.now().plusDays(3));
        Product tv = new ShippableProduct("TV", 5000, 0, 5.0);
        Product scratchCard = new Product("Scratch Card", 50, 10);
        Product expiredBiscuits = new PerishableShippableProduct("Biscuits", 150, 2, 0.7, LocalDate.now().minusDays(2));


        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        cart.add(tv, 3);
        cart.add(scratchCard, 1);
        cart.add(expiredBiscuits, 1);
        CheckoutService.checkout(customer, cart);

    }
}
