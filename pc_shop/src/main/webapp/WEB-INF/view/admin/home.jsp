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
    <link href="assets/js/sweetalert2.min.css" rel="stylesheet" type="text/css">
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
        <div class="content-fluid">
            <!-- Start Content-->
           <div class="row">
               <image src="https://wallpaper.dog/large/5446616.jpg" style="width: 100%"></image>
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