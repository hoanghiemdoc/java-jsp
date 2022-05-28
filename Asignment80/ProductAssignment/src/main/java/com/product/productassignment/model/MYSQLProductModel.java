package com.product.productassignment.model;

import com.product.productassignment.entity.Product;
import com.product.productassignment.entity.myenum.ProductStatus;
import com.product.productassignment.util.ConnectionHelper;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class MYSQLProductModel implements ProductModel{
    @Override
    public Product save(Product product) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "insert into products " +
                    "(categoryId,name,description,detail,price,image,createdAt,updatedAt,status) "+
                    "values " +
                    "(?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,product.getCategoryId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3,product.getDescription());
            preparedStatement.setString(4,product.getDetail());
            preparedStatement.setDouble(5,product.getPrice());
            preparedStatement.setString(6,product.getImage());
            preparedStatement.setString(7,product.getCreatedAt().toString());
            preparedStatement.setString(8,product.getUpdatedAt().toString());
            preparedStatement.setInt(9,product.getStatus().getValue());
            System.out.println("Connection success");
            preparedStatement.execute();
            return  product;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        List<Product> list =  new ArrayList<>();
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "select * from products where status = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, ProductStatus.ACTIVE.getValue());
            System.out.println("Action success!");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int categoryId = resultSet.getInt("categoryId");
                String name =  resultSet.getString("name");
                String description = resultSet.getString("description");
                String detail = resultSet.getString("detail");
                Double price = resultSet.getDouble("price");
                String image  = resultSet.getString("image");
                LocalDateTime createdAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("createdAt").toInstant(), ZoneId.systemDefault());
                LocalDateTime updatedAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("updatedAt").toInstant(), ZoneId.systemDefault());
                int intStatus = resultSet.getInt("status");
                Product product =new Product();
                product.setId(id);
                product.setCategoryId(categoryId);
                product.setName(name);
                product.setDescription(description);
                product.setDetail(detail);
                product.setImage(image);
                product.setPrice(price);
                product.setCreatedAt(createdAt);
                product.setUpdatedAt(updatedAt);
                product.setStatus(ProductStatus.of(intStatus));
                list.add(product);
            }
            System.out.println("Action success");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public List<Product> findProductById(int cid){
        List<Product> list = new ArrayList<>();
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery =  "select * from products where categoryId = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,cid);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id =resultSet.getInt("id");
                int categoryId = resultSet.getInt("categoryId");
                String name =  resultSet.getString("name");
                String description = resultSet.getString("description");
                String detail = resultSet.getString("detail");
                Double price = resultSet.getDouble("price");
                String image  = resultSet.getString("image");
                LocalDateTime createdAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("createdAt").toInstant(), ZoneId.systemDefault());
                LocalDateTime updatedAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("updatedAt").toInstant(), ZoneId.systemDefault());
                int intStatus = resultSet.getInt("status");
                Product product = new Product();
                product.setId(id);
                product.setCategoryId(categoryId);
                product.setName(name);
                product.setDescription(description);
                product.setDetail(detail);
                product.setImage(image);
                product.setPrice(price);
                product.setCreatedAt(createdAt);
                product.setUpdatedAt(updatedAt);
                product.setStatus(ProductStatus.of(intStatus));
                list.add(product);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Product findById(int id) {
        Product product = null;

        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "select * from products where status = ? and id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, ProductStatus.ACTIVE.getValue());
            preparedStatement.setInt(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){

                int categoryId = resultSet.getInt("categoryId");
                String name =  resultSet.getString("name");
                String description = resultSet.getString("description");
                String detail = resultSet.getString("detail");
                Double price = resultSet.getDouble("price");
                String image  = resultSet.getString("image");
                LocalDateTime createdAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("createdAt").toInstant(), ZoneId.systemDefault());
                LocalDateTime updatedAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("updatedAt").toInstant(), ZoneId.systemDefault());
                int intStatus = resultSet.getInt("status");
                product =new Product();
                product.setId(id);
                product.setCategoryId(categoryId);
                product.setName(name);
                product.setDescription(description);
                product.setDetail(detail);
                product.setImage(image);
                product.setPrice(price);
                product.setCreatedAt(createdAt);
                product.setUpdatedAt(updatedAt);
                product.setStatus(ProductStatus.of(intStatus));}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public Product update(int id, Product updateProduct) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "update products " +
                    "set categoryId = ?, name = ?, price = ?, detail = ?, description = ?,image= ?, createdAt = ?, updatedAt = ?, status = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,updateProduct.getCategoryId());
            preparedStatement.setString(2,updateProduct.getName());
            preparedStatement.setDouble(3,updateProduct.getPrice());
            preparedStatement.setString(4,updateProduct.getDetail());
            preparedStatement.setString(5,updateProduct.getDescription());
            preparedStatement.setString(6,updateProduct.getImage());
            preparedStatement.setString(7,updateProduct.getCreatedAt().toString());
            preparedStatement.setString(8,updateProduct.getUpdatedAt().toString());
            preparedStatement.setInt(9,updateProduct.getStatus().getValue());
            preparedStatement.setInt(10,id);
            System.out.println("Connection success");
            preparedStatement.execute();
            return updateProduct;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        try {
            Connection connection =  ConnectionHelper.getConnection();
            String sqlQuery = "update products " +
                    "set status = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,ProductStatus.DELETED.getValue());
            preparedStatement.setInt(2,id);
            System.out.println("Connection success!");
            preparedStatement.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Product> searchByName(String txtSearch){
        List<Product> list = new ArrayList<>();
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery =  "select * from products where name like ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,"%" + txtSearch + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id =resultSet.getInt("id");
                int categoryId = resultSet.getInt("categoryId");
                String name =  resultSet.getString("name");
                String description = resultSet.getString("description");
                String detail = resultSet.getString("detail");
                Double price = resultSet.getDouble("price");
                String image  = resultSet.getString("image");
                LocalDateTime createdAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("createdAt").toInstant(), ZoneId.systemDefault());
                LocalDateTime updatedAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("updatedAt").toInstant(), ZoneId.systemDefault());
                int intStatus = resultSet.getInt("status");
                Product product = new Product();
                product.setId(id);
                product.setCategoryId(categoryId);
                product.setName(name);
                product.setDescription(description);
                product.setDetail(detail);
                product.setImage(image);
                product.setPrice(price);
                product.setCreatedAt(createdAt);
                product.setUpdatedAt(updatedAt);
                product.setStatus(ProductStatus.of(intStatus));
                list.add(product);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }


    public int getTotalProduct(){
        try {
            String query = "select count(*) from products";
            Connection connectionHelper = ConnectionHelper.getConnection();
            PreparedStatement preparedStatement = connectionHelper.prepareCall(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                return resultSet.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public List<Product> pagingProduct(int index){
        List<Product> list = new ArrayList<>();
        try {
            Connection connection = ConnectionHelper.getConnection();
            String query = "SELECT * FROM products ORDER BY id  OFFSET ? ROWS FETCH NEXT 3 ROWS ONLY";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,(index-1)*3);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id =resultSet.getInt("id");
                int categoryId = resultSet.getInt("categoryId");
                String name =  resultSet.getString("name");
                String description = resultSet.getString("description");
                String detail = resultSet.getString("detail");
                Double price = resultSet.getDouble("price");
                String image  = resultSet.getString("image");
                LocalDateTime createdAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("createdAt").toInstant(), ZoneId.systemDefault());
                LocalDateTime updatedAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("updatedAt").toInstant(), ZoneId.systemDefault());
                int intStatus = resultSet.getInt("status");
                Product product = new Product();
                product.setId(id);
                product.setCategoryId(categoryId);
                product.setName(name);
                product.setDescription(description);
                product.setDetail(detail);
                product.setImage(image);
                product.setPrice(price);
                product.setCreatedAt(createdAt);
                product.setUpdatedAt(updatedAt);
                product.setStatus(ProductStatus.of(intStatus));
                list.add(product);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        MYSQLProductModel model = new MYSQLProductModel();
        List<Product> list = model.pagingProduct(1);
        for (Product p:list) {
            System.out.println(p);
        }
    }

}
