package cart;

import model.Product;

import java.util.*;

public class Cart {
    private Map<Product, Integer> items = new HashMap<>();

    public void add(Product product, int quantity) {
        if (quantity <= product.getQuantity()) {
            items.put(product, quantity);
        } else {
            System.out.println("⚠️ Product \"" + product.getName() + "\" is not available in the requested quantity. Available stock: " + product.getQuantity());
        }
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
