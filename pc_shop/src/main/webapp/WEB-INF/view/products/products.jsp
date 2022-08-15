<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Form Elements | Zircos - Responsive Bootstrap 4 Admin Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="Responsive bootstrap 4 admin template" name="description">
    <meta content="Coderthemes" name="author">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- App favicon -->
    <link rel="shortcut icon" href="assets\images\favicon.ico">
    <link href="assets/js/sweetalert2.min.css" rel="stylesheet" type="text/css">
    <link href="assets\css\bootstrap.min.css" rel="stylesheet" type="text/css" id="bootstrap-stylesheet">
    <link href="assets\libs\toastr\toastr.min.css" rel="stylesheet" type="text/css">
    <!-- App css -->
    <link href="assets\css\icons.min.css" rel="stylesheet" type="text/css">
    <link href="assets\css\app.min.css" rel="stylesheet" type="text/css" id="app-stylesheet">
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page contentType="text/html" pageEncoding="UTF-8" %>

</head>

<body data-layout="horizontal">

<!-- Begin page -->
<div id="wrapper">
    <jsp:include page="/WEB-INF/view/admin/header.jsp"></jsp:include>
    <div class="content-page">
        <div class="content" style=" margin-top: 20px">
            <div class="row">
                <div class="col-sm-3">
                    <div class="card bg-light mb-3">
                        <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i>
                            Categories
                        </div>
                        <ul class="list-group category_block">
                            <c:forEach items="${listC}" var="o">
                                <li class="list-group-item text-white"><a
                                        href="category?idCategory=${o.getIdCategory()}">${o.getNameCategory()}</a></li>
                            </c:forEach>

                        </ul>
                    </div>
                    <div class="card bg-light mb-3">
                        <div class="card-header bg-success text-white text-uppercase">Last product</div>
                        <div class="card-body">
                            <img class="img-fluid" src="${lastP.getImage()}" style="height: 300px"/>
                            <h5 class="card-title">${lastP.getTitle()}</h5>
                            <p class="card-text">${lastP.getDescription()}</p>
                            <%--                            <p class="bloc_left_price">${lastP.getPrice()} $</p>--%>
                        </div>
                    </div>
                </div>
                <div class="col-sm-9">
                    <form action="/product?action=list&q=${q}" method="get">
                        <div class="input-group col-md-4" style="
                        margin-left: auto;
                         margin-bottom: 10px;">
                            <input type="text" class="form-control rounded" placeholder="Search..." aria-label="Search"
                                   aria-describedby="search-addon" name="q" id="myInput" value="${q}"
                                   onkeyup="myFunction(a =${q})"/>
                            <input type="submit" class="btn btn-primary" value="search">
                        </div>
                    </form>
                    <script>
                        function myFunction(a) {
                            window.location.href = "/product?action=list&q=" + a;
                        }
                    </script>
                    <a href="/product?action=create" class="btn btn-success">ADD PRODUCT</a>
                    <div class="row">
                        <c:forEach items="${listP}" var="o">
                            <div class="col-12 col-md-6 col-lg-4">
                                <div class="card">
                                    <img class="card-img-top" src="${o.getImage()}"
                                         style=" height: 350px;
                                                        " alt="Card image cap">
                                    <div class="card-body">
                                        <div style="height: 130px">
                                            <h4 class="card-title show_txt">
                                                <a href="#" title="View Product">${o.getTitle()}</a>
                                            </h4>
                                            <p class="card-text show_txt">${o.getDescription()}</p>
                                        </div>
                                        <div class="row">
                                            <div class="col">
                                                <p class="btn btn-dark btn-block">Price: ${o.getPrice()}$</p>
                                            </div>
                                            <div class="col">
                                                <p class="btn btn-dark btn-block">Quantity: ${o.getQuantity()}</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <c:if test="${sessionScope.acc.getIdRole() == 1}">
                                            <div class="col">
                                                <a href="/product?action=edit&idProduct=${o.getIdProduct()}"
                                                   class="btn btn-info btn-block">Edit</a>
                                            </div>
                                            <div class="col">
                                                <a href="#" class="btn btn-danger btn-block"
                                                   onclick="showMessage(idProduct=${o.getIdProduct()})">Delete</a>
                                            </div>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">

                            <c:if test="${requestScope.currentPage != 1}">
                                <li class="page-item"><a class="page-link"
                                                         href="product?page=${requestScope.currentPage - 1}">Previous</a>
                                </li>
                            </c:if>
                            <c:forEach begin="1" end="${noOfPages}" var="i">
                                <c:choose>
                                    <c:when test="${requestScope.currentPage eq i}">
                                        <li class="page-item"><a class="page-link"
                                                                 href="product?page=${i}">${i}</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item"><a class="page-link"
                                                                 href="product?page=${i}">${i}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
                                <li class="page-item"><a class="page-link"
                                                         href="product?page=${requestScope.currentPage + 1}">Next</a>
                                </li>
                            </c:if>
                        </ul>
                    </nav>
                </div>
            </div>
            <!-- end page title -->
            <script>
                function showMessage(id) {
                    Swal.fire({
                        title: 'Are you sure?',
                        text: "You won't be able to revert this!",
                        icon: 'warning',
                        showCancelButton: true,
                        confirmButtonColor: '#3085d6',
                        cancelButtonColor: '#d33',
                        confirmButtonText: 'Yes, delete it!'
                    }).then((result) => {
                        if (result.isConfirmed) {
                            window.location.href = "/product?action=delete&idProduct=" + id;
                        }
                    })
                }
            </script>
        </div>    <!-- end row -->
    </div>
    <jsp:include page="/WEB-INF/view/admin/footer.jsp"></jsp:include>
    <!-- end Footer -->
</div>
</div>

</body>
<jsp:include page="/WEB-INF/view/admin/vendor.jsp"></jsp:include>

</html>