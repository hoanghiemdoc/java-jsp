package com.product.productassignment.entity;

import com.product.productassignment.entity.base.BaseEntity;
import com.product.productassignment.entity.myenum.ProductStatus;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Product extends BaseEntity {
    private int id;//nen de String id UUID
    private int categoryId;
    private String name;
    private String description;
    private double price;//BIGDECIMAL
    private String detail;
    private String image;
    private ProductStatus status;
    private HashMap<String, String> errors = new HashMap<>();
    public Product() {
        this.name="";
        this.description="";
        this.detail="";
        this.price=0;
        this.image="";
        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
        this.status= ProductStatus.ACTIVE;
    }
    public Product(int id, String name) {
        this.id = id;
        this.name = name;
        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
        this.status=ProductStatus.ACTIVE;
    }

    public Product(int id, int categoryId, String name, String description, double price, String detail, String image, ProductStatus status, HashMap<String, String> errors) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.detail = detail;
        this.image = image;
        this.status = status;
        this.errors = errors;
    }

    public boolean isValid(){
        checkValidate();
        return errors.size() ==0;
    }

    private void checkValidate() {
        //validate du lieu theo kieu cui bap
        if (name == null || name.length()>7){
            errors.put("name","Please enter name");
        }
        if (price == 0){
            errors.put("price","Please enter price");
        }
        if (image == null || image.length()==0){
            errors.put("image","Please enter image");
        }
        if (description == null || description.length()==0){
            errors.put("description","Please enter description");
        }
        if (detail == null || detail.length()==0){
            errors.put("detail","Please enter detail");
        }

    }

    public HashMap<String, String> getErrors() {
        return errors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", detail='" + detail + '\'' +
                ", image='" + image + '\'' +
                ", status=" + status +
                ", errors=" + errors +
                '}';
    }
}
