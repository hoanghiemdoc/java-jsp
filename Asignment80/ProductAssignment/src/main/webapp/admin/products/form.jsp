
<%@ page import="java.util.HashMap" %>
<%@ page import="com.product.productassignment.entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="com.product.productassignment.entity.Category" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.product.productassignment.entity.myenum.ProductStatus" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/12/2022
  Time: 2:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Product product = (Product) request.getAttribute("product");
    List<Category> categories = (List<Category>) request.getAttribute("categories");
    if (categories == null){
        categories = new ArrayList<>();
    }
    int action = (int) request.getAttribute("action");
    HashMap<String, String> errors = (HashMap<String, String>) request.getAttribute("errors") ;
    String url = "/admin/products/create";
    String title = "Create Product";
    if(action == 2){
        title="Edit Product";
        url = "/admin/products/edit";
    }
    if (errors == null){
        errors = new HashMap<>();
    }

%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="../inlclude/head.jsp">
    <jsp:param name="title" value="<%=title%>"/>
</jsp:include>
<body>
<div class="container-scroller">
    <!-- partial:../../partials/_navbar.html -->
    <jsp:include page="../inlclude/navbar.jsp"/>
    <!-- partial -->
    <div class="container-fluid page-body-wrapper"
    >
        <!-- partial:../../partials/_sidebar.html -->
        <jsp:include page="../inlclude/sidebar.jsp"/>
        <!-- partial -->
        <div class="main-panel">
            <div class="content-wrapper">
                <%
                    if(errors != null && errors.size() > 0){
                %>
                <div class="row">
                    <div class="col-12">
                        <div class="callout callout-danger">
                            <p class="text-danger fs-6">Please fix error below</p>
                            <ul>
                                <%
                                    for (String msg: errors.values()){
                                %>
                                <li class="text-danger"><%=msg%></li>
                                <%
                                    }
                                %>
                            </ul>
                        </div>
                    </div>
                </div>
                <%}%>
                <div class="page-header">
                    <h3 class="page-title"> Product Management </h3>
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="#">Product management</a></li>
                            <li class="breadcrumb-item active" aria-current="page"><%=title%></li>
                        </ol>
                    </nav>
                </div>
                <div class="row">
                    <div class="col-md-12 grid-margin stretch-card">
                        <div class="card">
                            <div class="card-body">
                                <h3 class="card-title"><%=title%></h3>


                                <form class="forms-sample" action="<%=url%>" method="post" name="product-form">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <input type="hidden" name="id" value="<%=product.getId()%>" class="form-control" placeholder="Enter product name">
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Product Name</label>

                                                <input type="text" name="name" value="<%=product.getName()%>" class="form-control" placeholder="Enter product name">
                                                <%
                                                    if (errors.containsKey("name")){%>
                                                <span class="text-danger">* <%=errors.get("name")%></span>
                                                <%}%>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-2">
                                            <div class="form-group">
                                                <label>Select Category</label>
                                                <select name="categoryId" class="form-control">
                                                    <option value="0">All</option>
                                                    <%
                                                        for (int i = 0; i < categories.size(); i++) {
                                                    %>
                                                    <option value="<%=categories.get(i).getId()%>"><%=categories.get(i).getName()%></option>
                                                    <%
                                                        }
                                                    %>

                                                </select>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-2">
                                            <div class="form-group">
                                                <label>Price</label>

                                                <input type="text" name="price" value="<%=product.getPrice()%>"  class="form-control" placeholder="Enter product price">
                                                <%
                                                    if (errors.containsKey("price")){%>
                                                <span class="text-danger">* <%=errors.get("price")%></span>
                                                <%}%>
                                            </div>
                                        </div>
                                    </div>


                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Description</label>

                                                <input type="text" name="description" value="<%=product.getDescription()%>"  class="form-control" placeholder="Enter product description">
                                                <%
                                                    if (errors.containsKey("description")){%>
                                                <span class="text-danger">* <%=errors.get("description")%></span>
                                                <%}%>
                                            </div>
                                        </div>
                                    </div>


                                    <div class="row">
                                        <div class="col-md-8">
                                            <div class="form-group">
                                                <label>File upload</label>
                                                <div class="input-group col-xs-12">
                                                    <input type="text" name="image" value="<%=product.getImage()%>" class="form-control" placeholder="Enter product image">

                                                    <div class="input-group-append" id="upload_widget">
                                                        <span class="input-group-text" >Upload</span>
                                                    </div>
                                                    <br>

                                                    <%
                                                        if (errors.containsKey("image")){%>
                                                    <span class="text-danger">* <%=errors.get("image")%></span>
                                                    <%}%>
                                                </div>
                                                <img id="preview-image" style="display: none; margin-top: 15px;" width="200" class="img-fluid" src="" alt="">
                                            </div>
                                        </div>
                                    </div>


                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Detail</label>
                                                <textarea name="detail">
                                                    <%=product.getDetail()%>
                                                </textarea>
                                                <%
                                                    if (errors.containsKey("detail")){%>
                                                <span class="text-danger">* <%=errors.get("detail")%></span>
                                                <%}%>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-2">
                                            <div class="form-group">
                                                <label>Status</label>
                                                <select name="status" class="form-control">
                                                    <%
                                                        for (int i = 0; i < ProductStatus.values().length; i++) {%>
                                                    <option <%=product.getStatus()==ProductStatus.values()[i] ? "selected" : ""%> value="<%=ProductStatus.values()[i].getValue()%>"><%=ProductStatus.values()[i].name()%></option>
                                                    <%
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                        </div>
                                    </div>

                                    <button type="submit" class="btn btn-primary me-2">Submit</button>
                                    <a href="/admin/products/list" class="btn btn-light">Cancel</a>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- content-wrapper ends -->
            <!-- partial:../../partials/_footer.html -->
            <jsp:include page="../inlclude/footer.jsp"/>
            <!-- partial -->
        </div>
        <!-- main-panel ends -->
    </div>
    <!-- page-body-wrapper ends -->
</div>
<!-- container-scroller -->
<jsp:include page="../inlclude/scripts.jsp"/>
<script src="../ckeditor/ckeditor.js"></script>
<script src="https://upload-widget.cloudinary.com/global/all.js" type="text/javascript"></script>

<script>
    document.addEventListener('DOMContentLoaded',function (){
        CKEDITOR.replace('detail');
        var myWidget = cloudinary.createUploadWidget({
                cloudName: 'bac-ninh',
                uploadPreset: 'x5r2fydu'}, (error, result) => {
                if (!error && result && result.event === "success") {
                    console.log('Done! Here is the image info: ', result.info.secure_url);
                    document.forms['product-form']['image'].value = result.info.secure_url;
                    document.getElementById('preview-image').src = result.info.secure_url;

                    document.getElementById('preview-image').style.display ='block';
                }
            }
        )
        document.getElementById("upload_widget").addEventListener("click", function(){
            myWidget.open();
        }, false);
    })

</script>

<script type="text/javascript">
</script>
</body>
</html>