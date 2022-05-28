package com.product.productassignment.controller.products;

import com.product.productassignment.entity.Product;
import com.product.productassignment.model.MYSQLProductModel;
import com.product.productassignment.model.ProductModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListProductServlet extends HttpServlet {
    private ProductModel productModel;
    public ListProductServlet() {
        this.productModel = new MYSQLProductModel();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> list = productModel.findAll();
        request.setAttribute("title","List Products");
        request.setAttribute("list",list);
        request.getRequestDispatcher("/admin/products/list.jsp").forward(request,response);
    }
}
