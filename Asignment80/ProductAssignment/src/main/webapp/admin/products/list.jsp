<%@ page import="java.util.List" %>
<%@ page import="com.product.productassignment.entity.Product" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/12/2022
  Time: 2:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Product> list = (List<Product>)request.getAttribute("list");
    if (list==null){
        list = new ArrayList<>();
    }
%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="../inlclude/head.jsp">
    <jsp:param name="title" value="List product"/>
</jsp:include>
<body>
<div class="container-scroller">
    <!-- partial:../../partials/_navbar.html -->
    <jsp:include page="../inlclude/navbar.jsp"></jsp:include>
    <!-- partial -->
    <div class="container-fluid page-body-wrapper">
        <!-- partial:../../partials/_sidebar.html -->
        <jsp:include page="../inlclude/sidebar.jsp"></jsp:include>
        <!-- partial -->
        <div class="main-panel">
            <div class="content-wrapper">
                <div class="page-header">
                    <h3 class="page-title"> Product Management </h3>
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="#">Product management</a></li>
                            <li class="breadcrumb-item active" aria-current="page">List products</li>
                        </ol>
                    </nav>
                </div>
                <div class="row">
                    <div class="col-lg-12 grid-margin stretch-card">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">List Products</h4>
                                <a href="/admin/products/create" class="btn btn-success">Create product</a>

                                <table class="table table-hover">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Product Name</th>
                                        <th>Price</th>
                                        <th>Image</th>
                                        <th>Action</th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    <%
                                        for (Product pr: list) {
                                    %>
                                    <tr>
                                        <th><%=pr.getId()%></th>
                                        <td><%=pr.getName()%></td>
                                        <td><%=pr.getPrice()%></td>
                                        <td >
                                            <img class="img-fluid" style="border-radius: 0;width: 150px;height: 150px;" width="150" src="<%=pr.getImage()%>" alt="">
                                        </td>
                                        <td>
                                            <a href="/admin/products/detail?id=<%=pr.getId()%>"><i class="mdi mdi-glasses size-big"></i></a> |
                                            <a href="/admin/products/edit?id=<%=pr.getId()%>"> <i class="mdi mdi-border-color size-big"></i></a> |
                                            <a href="/admin/products/delete?id=<%=pr.getId()%>" onclick="return confirm('Are you sure?')"><i class="mdi mdi-delete size-big"></i></a>
                                        </td>
                                    </tr>
                                    <% }%>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- content-wrapper ends -->
            <!-- partial:../../partials/_footer.html -->
            <jsp:include page="../inlclude/footer.jsp"></jsp:include>
            <!-- partial -->
        </div>
        <!-- main-panel ends -->
    </div>
    <!-- page-body-wrapper ends -->
</div>
<!-- container-scroller -->
<jsp:include page="../inlclude/scripts.jsp"></jsp:include>
</body>
</html>