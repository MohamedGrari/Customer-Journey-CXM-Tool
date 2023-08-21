package com.mitacs.customerjourney.service;

import com.mitacs.customerjourney.model.Bike;
import com.mitacs.customerjourney.model.Customer;
import com.mitacs.customerjourney.model.Product;
import com.mitacs.customerjourney.model.enums.BikeType;
import com.mitacs.customerjourney.model.enums.Colour;
import com.mitacs.customerjourney.model.enums.Material;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class TemporalService {
    public boolean isNewPurchaseCheck(Product product, Customer customer) {
        Random random = new Random();
        return random.nextBoolean();
    }

    public List<Product> recommendSimilarProducts(Product targetedProduct) {
        Product product1 = new Bike();
        Product product2 = new Bike();
        Product product3 = new Bike();
        Product product4 = new Bike();
        List<Product> recommendedProducts = new ArrayList<>();
        recommendedProducts.add(product1);
        recommendedProducts.add(product2);
        recommendedProducts.add(product3);
        recommendedProducts.add(product4);
        return recommendedProducts;
    }

    public List<Product> recommendPersonalisedProducts(Product targetedProduct) {
        Bike product1 = new Bike(
                "PROD123",           // ref
                "Mountain Bike",     // name
                1500.0f,              // price
                "High-performance mountain bike", // description
                "image-url",         // imageUrl
                null,                // brand (you can set this)
                BikeType.MOUNTAIN,   // bikeType
                21,                  // speed
                "Medium",            // size
                Colour.BLUE,          // colour
                Material.ALUMINUM,   // material
                12
        );
        Product product2 = new Bike();
        Product product3 = new Bike();
        Product product4 = new Bike();
        List<Product> recommendedProducts = new ArrayList<>();
        recommendedProducts.add(product1);
        recommendedProducts.add(product2);
        recommendedProducts.add(product3);
        recommendedProducts.add(product4);
        return recommendedProducts;
    }
}
