package com.product.productassignment.model;

import com.product.productassignment.entity.Category;

import java.util.List;

public interface CategoryModel {
    Category save(Category category);
    List<Category> findAll();
    Category findById(int id);
    Category update(int id,Category updateObj);
    boolean delete(int id);
}
