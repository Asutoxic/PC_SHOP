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

    <link href="assets\libs\toastr\toastr.min.css" rel="stylesheet" type="text/css">
    <!-- App css -->
    <link href="assets\css\bootstrap.min.css" rel="stylesheet" type="text/css" id="bootstrap-stylesheet">
    <link href="assets\css\icons.min.css" rel="stylesheet" type="text/css">
    <link href="assets\css\app.min.css" rel="stylesheet" type="text/css" id="app-stylesheet">
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


</head>

<body data-layout="horizontal">

<!-- Begin page -->
<div id="wrapper">

    <!-- Navigation Bar-->
<%--    <%@include file="header.jsp" %>--%>
    <jsp:include page="/WEB-INF/view/admin/header.jsp"></jsp:include>
    <div class="content-page">
        <div class="content">
            <!-- Start Content-->
            <!-- start page title -->
            <div class="row">
                <div class="col-12">
                    <div class="page-title-box">
                        <h4 class="page-title">Add Customer</h4>
                        <a href="/customer" class="btn btn-success">List User</a>
                    </div>
                </div>
            </div>
            <!-- end page title -->

            <div class="row">
                <div class="col-sm-12">
                    <div class="card-box">
                        <div class="row">
                            <div class="col-md-12">
                                <form class="form-horizontal" method="post">
                                    <input type="hidden" name="idCustomer" value="${customer.getIdCustomer()}"/>
                                    <div class="form-group row">
                                        <label class="col-md-2 control-label">Full Name</label>
                                        <div class="col-md-10">
                                            <input type="text" class="form-control" name="fullName"
                                                   value="${customer.getFullName()}">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-md-2 control-label" for="example-password">Password</label>
                                        <div class="col-md-10">
                                            <input type="password" id="example-password" name="password"
                                                   value="${customer.getPassword()}"
                                                   class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-md-2 control-label">Addresse</label>
                                        <div class="col-md-10">
                                            <input type="text" class="form-control" name="address"
                                                   value="${customer.getAddress()}">
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <label class="col-md-2 control-label">Phone</label>
                                        <div class="col-md-10">
                                            <input type="text" class="form-control" name="phone"
                                                   value="${customer.getPhone()}">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-md-2 control-label">Email</label>
                                        <div class="col-md-10">
                                            <input type="email" class="form-control" name="email"
                                                   value="${customer.getEmail()}">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-md-2 control-label">Role</label>
                                        <div class="col-md-10">
                                            <select name="idRole">
                                                <c:forEach items="${applicationScope.listRole}" var="role">
                                                    <c:choose>
                                                        <c:when test="${role.getIdRole() == customer.getIdRole() }">
                                                            <option value="${role.getIdRole()}"
                                                                    selected> ${role.getRole()}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${role.getIdRole() }">${role.getRole()}</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <input type="submit" class="btn btn-primary form-control" value="ADD">
                                    </div>
                                </form>
                            </div>
                            <div>
                                ${errors}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- end row -->
        </div>
        <!-- end content -->

        <c:if test="${requestScope.message != null}">

            <script>
                let strMessage = '${requestScope.message}';

                window.onload = function (e) {
                    toastr.options = {
                        "closeButton": true,
                        "debug": false,
                        "newestOnTop": false,
                        "progressBar": false,
                        "positionClass": "toast-top-right",
                        "preventDuplicates": false,
                        "onclick": null,
                        "showDuration": "300",
                        "hideDuration": "1000",
                        "timeOut": "5000",
                        "extendedTimeOut": "1000",
                        "showEasing": "swing",
                        "hideEasing": "linear",
                        "showMethod": "fadeIn",
                        "hideMethod": "fadeOut"
                    }
                    toastr["success"](strMessage)
                }
            </script>
        </c:if>


        <!-- Footer Start -->
        <jsp:include page="/WEB-INF/view/admin/footer.jsp"></jsp:include>
        <!-- end Footer -->

    </div>
</div>
<jsp:include page="/WEB-INF/view/admin/vendor.jsp"></jsp:include>

</body>

</html>