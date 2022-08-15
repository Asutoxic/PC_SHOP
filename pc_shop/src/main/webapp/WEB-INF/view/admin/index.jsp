<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>List Customer</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="Responsive bootstrap 4 admin template" name="description">
    <meta content="Coderthemes" name="author">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- App favicon -->
    <%--    <link href="assets\libs\sweetalert2\sweetalert2.min.css" rel="stylesheet" type="text/css">--%>
    <link rel="shortcut icon" href="assets\images\favicon.ico">
    <link href="../../../assets/js/sweetalert2.min.css" rel="stylesheet" type="text/css">
    <link href="assets\css\bootstrap.min.css" rel="stylesheet" type="text/css" id="bootstrap-stylesheet">
    <link href="assets\css\icons.min.css" rel="stylesheet" type="text/css">
    <link href="assets\css\app.min.css" rel="stylesheet" type="text/css" id="app-stylesheet">
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body data-layout="horizontal">

<!-- Begin page -->
<div id="wrapper">
    <jsp:include page="/WEB-INF/view/admin/header.jsp"></jsp:include>
    <!-- Navigation Bar-->
    <!-- End Navigation Bar-->

    <!-- ============================================================== -->
    <!-- Start Page Content here -->
    <!-- ============================================================== -->

    <div class="content-page">
        <div class="content">
            <!-- Start Content-->
            <div class="col-md-12">
                <div class="card-box">
                    <div class="table-responsive">
                        <h4 class="header-title mb-4"><a href="/customer" >List Customers</a></h4>
                        <form method="get">
                            <div class="input-group col-md-4" style="
                        margin-left: auto;
                         margin-bottom: 10px;">
                                <input type="text" class="form-control rounded" placeholder="Search..." aria-label="Search"
                                       aria-describedby="search-addon" name="q" id="myInput" value="${q}"/>
                                <input type="submit" class="btn btn-primary" value="search">
                            </div>
                        </form>
                        <a href="customer?action=create" class="btn btn-success">Add</a>
                        <table class="table table-hover table-centered m-0 text-center">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Full Name</th>
<%--                                <th>Password</th>--%>
                                <th>Address</th>
                                <th>Phone</th>
<%--                                <th>Email</th>--%>
                                <th>Role</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody id="myTable">
                            <c:forEach items="${requestScope.listCustomer}" var="customer">
                                <tr>
                                    <th>
                                        <span class="avatar-sm-box bg-success">
                                            <c:out value="${customer.getIdCustomer()}">

                                            </c:out></span>
                                    </th>
                                    <td>
                                        <h5 class="m-0 font-15">${customer.getFullName()}</h5>
                                    </td>
<%--                                    <td><c:out value="${customer.getPassword()}">--%>

<%--                                    </c:out></td>--%>
                                    <td><c:out value="${customer.getAddress()}">

                                    </c:out></td>
                                    <td><c:out value="${customer.getPhone()}">

                                    </c:out></td>
<%--                                    <td><c:out value="${customer.getEmail()}">--%>

<%--                                    </c:out></td>--%>
                                    <td>
                                        <c:forEach items="${applicationScope.listRole}" var="role">
                                            <c:if test="${customer.getIdRole() == role.getIdRole()}">
                                                <c:out value="${role.getRole()}"/>
                                            </c:if>

                                        </c:forEach>
                                    </td>
                                    <td>
                                        <a href="customer?action=edit&idCustomer=${customer.getIdCustomer()}"
                                           class="btn btn-info">Edit</a>
                                        <a href="#" class="btn btn-danger"
                                           onclick="showMessage(idCustomer=${customer.getIdCustomer()})">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
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
                                            window.location.href = "/customer?action=delete&idCustomer=" + id;
                                        }
                                    })
                                }
                            </script>
                        </table>
                        <nav aria-label="Page navigation example">
                            <ul class="pagination">

                                <c:if test="${requestScope.currentPage != 1}">
                                    <li class="page-item"><a class="page-link"
                                                             href="customer?page=${requestScope.currentPage - 1}">Previous</a>
                                    </li>
                                </c:if>
                                <c:forEach begin="1" end="${noOfPages}" var="i">
                                    <c:choose>
                                        <c:when test="${requestScope.currentPage eq i}">
                                            <li class="page-item"><a class="page-link"
                                                                     href="customer?page=${i}">${i}</a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="page-item"><a class="page-link"
                                                                     href="customer?page=${i}">${i}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
                                    <li class="page-item"><a class="page-link"
                                                             href="customer?page=${requestScope.currentPage + 1}">Next</a>
                                    </li>
                                </c:if>
                            </ul>
                        </nav>


                        <%--For displaying Next link --%>


                    </div>


                    <!-- table-responsive -->
                </div>
                <!-- end card -->
            </div>
            <!-- end col -->

        </div>
        <!-- end row -->
        <!-- end container-fluid -->

    </div>


    <!-- end content -->
    <!-- Footer Start -->
    <jsp:include page="/WEB-INF/view/admin/footer.jsp"></jsp:include>

    <!-- end Footer -->

</div>

<!-- ============================================================== -->
<!-- End Page content -->
<!-- ============================================================== -->

</div>
<!-- END wrapper -->

<!-- Right Sidebar -->
<!-- Vendor js -->

<jsp:include page="/WEB-INF/view/admin/vendor.jsp"></jsp:include>

<%--    <jsp:param name="vendor_js" value="index"></jsp:param>--%>

</body>

</html>