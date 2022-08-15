<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header id="topnav">
    <!-- Topbar Start -->
    <div class="navbar-custom">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-6">
                    <div class="logo-box">
                        <a href="/home" class="logo text-center">
                                    <span class="logo-lg">
                                        <img src="assets\images\logo.png" alt="" height="30">
                                        <!-- <span class="logo-lg-text-light">Zircos</span> -->
                                    </span>
                            <span class="logo-sm">
                                        <!-- <span class="logo-sm-text-dark">Z</span> -->
                                        <img src="assets\images\logo-sm.png" alt="" height="22">
                                    </span>
                        </a>
                    </div>

                </div>
                <div class="col-md-6" style="
                            margin-bottom: auto;
                            margin-top: auto;
                        ">
                    <div class="text-right justify-content-between">
                        <c:if test="${sessionScope['acc'] == null}">
                            <a href="/login" class="text-success">Login</a>
                        </c:if>
                        <c:if test="${sessionScope.acc != null}">
                        <span class="text-info">Hello, ${sessionScope['acc'].getFullName()}</span>
                        <a href="/logout" class="text-danger">Logout</a></div>
                    </c:if>
                </div>
        </div>

        <div class="clearfix"></div>
    </div>
    </div>
    <!-- end Topbar -->

    <div class="topbar-menu">
        <div class="container-fluid">
            <div id="navigation">
                <!-- Navigation Menu-->
                <ul class="navigation-menu">
                    <li class="has-submenu">
                        <a href="#"> <i class="mdi mdi-view-dashboard"></i>Dashboard</a>
                        <ul class="submenu">
                            <c:if test="${sessionScope.acc.getIdRole() == 1}">
                                <li><a href="/customer">List User</a></li>

                            </c:if>
                            <c:if test="${sessionScope.acc.getIdRole() == 2 or sessionScope.acc.getIdRole() == 1}">
                                <li><a href="/product">Product</a></li>
                            </c:if>
                        </ul>
                    </li>
                </ul>
                <!-- End navigation menu -->

                <div class="clearfix"></div>
            </div>
            <!-- end #navigation -->
        </div>
        <!-- end container -->
    </div>
    <!-- end navbar-custom -->
</header>
