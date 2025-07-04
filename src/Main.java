import cart.Cart;
import model.Customer;
import model.PerishableShippableProduct;
import model.Product;
import service.CheckoutService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("Ahmed", 1000);

        Product cheese = new PerishableShippableProduct("Cheese", 100, 5, 0.2, LocalDate.now().plusDays(3));
        Product biscuits = new PerishableShippableProduct("Biscuits", 150, 3, 0.7, LocalDate.now().plusDays(3));


        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(biscuits, 1);

        CheckoutService.checkout(customer, cart);
    }
}
