package com.dezc.coffeesaleapp.fragments.dummy;

import com.dezc.coffeesaleapp.models.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Product> ITEMS = new ArrayList<Product>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Product> ITEM_MAP = new HashMap<String, Product>();

    private static final int COUNT = 3;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    public static void addItem(Product item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getId(), item);
    }

    private static Product createDummyItem(int position) {
        return new Product(String.valueOf(position), "Product " + position, "$"+position);
    }



}
