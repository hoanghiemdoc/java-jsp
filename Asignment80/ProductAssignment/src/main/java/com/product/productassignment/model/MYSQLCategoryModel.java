package com.product.productassignment.model;

import com.product.productassignment.entity.Category;
import com.product.productassignment.entity.myenum.CategoryStatus;
import com.product.productassignment.util.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class MYSQLCategoryModel implements CategoryModel{

    @Override
    public Category save(Category category) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "insert into categories " +
                    "(name ,createdAt ,updatedAt ,status) "+
                    "values " +
                    "(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,category.getName());
            preparedStatement.setString(2,category.getCreatedAt().toString());
            preparedStatement.setString(3,category.getUpdatedAt().toString());
            preparedStatement.setInt(4,category.getStatus().getValue());
            System.out.println("Action success");
            preparedStatement.execute();
            return  category;
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Category> findAll() {
        List<Category> list = new ArrayList<>();
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "select * from categories where status = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, CategoryStatus.ACTIVE.getValue());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                LocalDateTime createdAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("createdAt").toInstant(), ZoneId.systemDefault());
                LocalDateTime updatedAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("updatedAt").toInstant(), ZoneId.systemDefault());
                int intStatus = resultSet.getInt("status");
                Category category = new Category(id, name);
                category.setCreatedAt(createdAt);
                category.setCreatedAt(updatedAt);
                category.setStatus(CategoryStatus.of(intStatus));
                list.add(category);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Category findById(int id) {
        Category category= null;
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "select * from categories where status = ? and id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, CategoryStatus.ACTIVE.getValue());
            preparedStatement.setInt(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                LocalDateTime createdAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("createdAt").toInstant(), ZoneId.systemDefault());
                LocalDateTime updatedAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("updatedAt").toInstant(), ZoneId.systemDefault());
                int intStatus = resultSet.getInt("status");
                category = new Category(id, name);
                category.setCreatedAt(createdAt);
                category.setCreatedAt(updatedAt);
                category.setStatus(CategoryStatus.of(intStatus));}
        }catch (SQLException e){
            e.printStackTrace();
        }
        return category;
    }



    @Override
    public Category update(int id, Category updateCategory) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "update categories " +
                    "set name = ?, createdAt = ?, updatedAt = ?, status = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1,updateCategory.getName());
            preparedStatement.setString(2,updateCategory.getCreatedAt().toString());
            preparedStatement.setString(3,updateCategory.getUpdatedAt().toString());
            preparedStatement.setInt(4,updateCategory.getStatus().getValue());
            preparedStatement.setInt(5,id);
            System.out.println("Action success");
            preparedStatement.execute();
            return updateCategory;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        try {
            Connection connection =  ConnectionHelper.getConnection();
            String sqlQuery = "update categories " +
                    "set status = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,CategoryStatus.DELETED.getValue());
            preparedStatement.setInt(2,id);
            System.out.println("Action success!");
            preparedStatement.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
