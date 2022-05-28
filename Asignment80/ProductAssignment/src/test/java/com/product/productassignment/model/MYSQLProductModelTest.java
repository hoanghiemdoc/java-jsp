package com.product.productassignment.model;

import com.product.productassignment.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MYSQLProductModelTest {
    private ProductModel productModel;

    @BeforeEach
    void setUp() {
        productModel = new MYSQLProductModel();
    }


    @Test
    void save() {
        Product product = new Product();
        product.setCategoryId(1);
        product.setName("Apple");
        product.setPrice(100);
        product.setDescription("Lorem ipsum");
        product.setDetail("Lorem ipsum");
        product.setImage("Lorem ipsum");
        productModel.save(product);
    }

    @Test
    void findAll() {
    }

    @Test
    void findProductById() {
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void searchByName() {
    }

    @Test
    void pagingProduct() {
    }

    @Test
    void main() {
    }
}