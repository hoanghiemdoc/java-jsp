package com.product.productassignment.model;

import com.product.productassignment.entity.Product;
import com.product.productassignment.entity.Product;

import java.util.List;

public interface ProductModel {
    Product save(Product product);
    List<Product> findAll();
    Product findById(int id);
    Product update(int id,Product updateProduct);
    boolean delete(int id);
    List<Product> searchByName(String txtSearch);
    List<Product> findProductById(int cid);
}
