package com.product.productassignment.model;

import com.product.productassignment.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MYSQLCategoryModelTest {


    private MYSQLCategoryModel model;
    @BeforeEach
    void setUp() {
        model = new MYSQLCategoryModel();
    }

    @Test
    void save() {
        Category category = new Category();
        category.setName("Grill.");
        model.save(category);
        Category category2 = new Category();
        category2.setName("Boiled food.");
        model.save(category2);
        Category category3 = new Category();
        category3.setName("Grill.");
        model.save(category3);
        Category category4 = new Category();
        category4.setName("Boiled food.");
        model.save(category4);
        assertEquals( 4 ,model.findAll().size());

    }

    @Test
    void findAll() {
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
}