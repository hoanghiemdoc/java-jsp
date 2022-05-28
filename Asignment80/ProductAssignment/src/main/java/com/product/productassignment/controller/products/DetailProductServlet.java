package com.product.productassignment.controller.products;

import com.product.productassignment.entity.Product;
import com.product.productassignment.model.MYSQLProductModel;
import com.product.productassignment.model.ProductModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DetailProductServlet extends HttpServlet {
    private ProductModel productModel;
    public DetailProductServlet(){
        this.productModel = new MYSQLProductModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productModel.findById(id);
        if (product==null){
            req.setAttribute("message","Product not found!");
            req.getRequestDispatcher("/errors/404.jsp");
        }else {
            req.setAttribute("product",product);
            req.getRequestDispatcher("/admin/products/detail.jsp").forward(req,resp);
        }
    }
}
