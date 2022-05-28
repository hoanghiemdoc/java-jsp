package com.product.productassignment.controller.products;

import com.product.productassignment.entity.Product;
import com.product.productassignment.entity.myenum.ProductStatus;
import com.product.productassignment.model.CategoryModel;
import com.product.productassignment.model.MYSQLCategoryModel;
import com.product.productassignment.model.MYSQLProductModel;
import com.product.productassignment.model.ProductModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditProductServlet extends HttpServlet {
    private ProductModel productModel;
    private CategoryModel categoryModel;
    public EditProductServlet(){
        this.productModel = new MYSQLProductModel();
        this.categoryModel=new MYSQLCategoryModel();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productModel.findById(id);
        if (product==null){
            req.setAttribute("message","Product not found!");
            req.getRequestDispatcher("/errors/404.jsp").forward(req,resp);
        }else{
            req.setAttribute("product",product);
            req.setAttribute("categories",categoryModel.findAll());
            req.setAttribute("action",2);
            req.getRequestDispatcher("/admin/products/form.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));
        Product existingProduct = productModel.findById(id);
        if (existingProduct==null){
            req.setAttribute("message","Product not found!");
            req.getRequestDispatcher("/errors/404.jsp").forward(req,res);
        }else{
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));
            String name= req.getParameter("name");
            Double price = Double.valueOf(req.getParameter("price"));
            String description = req.getParameter("description");
            String image = req.getParameter("image");
            String detail = req.getParameter("detail");
            int status  = Integer.parseInt(req.getParameter("status"));
            System.out.println(name);
            Product product = new Product();
            product.setName(name);
            product.setCategoryId(categoryId);
            product.setStatus(ProductStatus.of(status));
            product.setDetail(detail);
            product.setPrice(price);
            product.setDescription(description);
            product.setImage(image);
            if (!product.isValid()){
                req.setAttribute("product", product);
                req.setAttribute("categories",categoryModel.findAll());
                req.setAttribute("action",2);
                req.setAttribute("errors",product.getErrors());
                req.getRequestDispatcher("/admin/products/form.jsp").forward(req,res);
            }
            if (productModel.update(id,product)!=null){
                res.sendRedirect("/admin/products/list");
            }else{
                req.setAttribute("categories",categoryModel.findAll());
                req.setAttribute("product",product);
                req.setAttribute("action",2);
                req.getRequestDispatcher("/admin/products/form.jsp").forward(req,res);
            }
        }

    }
}
