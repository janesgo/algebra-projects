<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html class="h-100">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Cikloteka</title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,300,600" rel="stylesheet" type="text/css">
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body class="d-flex flex-column h-100">
<header>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="index">Cikloteka</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault"
                    aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="index">Bicikli <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="cart">Košarica</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="profile">Profil</a>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="nav-item mr-5"><a class="btn btn-success btn-sm" href="cart">
                        <i class="fa fa-shopping-cart"></i> Košarica
                        <span class="badge badge-light">${cart.orderItems.size()}</span>
                    </a></li>
                    <c:choose>
                        <c:when test="${user == null}">
                            <li class="nav-item"><a class="btn btn-danger btn-sm" href="login"><i
                                    class="fa fa-user-circle"></i> Prijava</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="nav-item"><a class="btn btn-danger btn-sm" href="logout"><i
                                    class="fa fa-user-circle"></i> Odjava</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
    </nav>
</header>
<main role="main" class="flex-shrink-0">
    <div class="container">
        <div class="row">
            <div class="col">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="index">Početna</a></li>
                        <c:if test="${currentCategory != 0}">
                            <li class="breadcrumb-item active"
                                aria-current="page">${selected}</li>
                        </c:if>
                    </ol>
                </nav>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-12 col-sm-3">
                <div class="card bg-light mb-3">
                    <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Kategorije
                    </div>
                    <ul class="list-group category_block">
                        <c:forEach var="c" items="${categories}">
                            <li class="list-group-item"><a href="index?category=${c.id}">${c.name}</a></li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <div class="col">
                <div class="row">
                    <c:forEach var="b" items="${bikes}">
                        <div class="col-12 col-md-6 col-lg-4 mb-1">
                            <div class="card">
                                <img class="card-img-top"
                                     src="https://dummyimage.com/600x400/2e2c30/0073ff&text=slika+bicikla"
                                     alt="Card image cap">
                                <div class="card-body">
                                    <h4 class="card-title"><a href="bike?i=${b.id}" title="Pregledaj bicikl">${b.name}
                                    </a></h4>
                                    <p class="card-text">
                                        <b>Kategorija: </b>${b.category.name}<br>
                                        <b>Marka: </b>${b.brand.name}<br>
                                        <b>Godina: </b>${b.modelYear}<br>
                                        <b>Cijena: </b><fmt:formatNumber type="number" maxFractionDigits="2" value="${b.price}"/> USD
                                    </p>
                                    <div class="row">
                                        <div class="col-6">
                                            <form action="item" method="post">
                                                <input type="hidden" id="action" name="action" value="1">
                                                <input type="hidden" id="bike" name="bike" value="${b.id}">
                                                <input type="hidden" id="quantity" name="quantity" value="1">
                                                <button type="submit" class="btn btn-sm btn-danger btn-block">U
                                                    košaricu
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <c:if test="${(!bikes.isEmpty())}">
                        <div class="col-12 mt-3">
                            <nav aria-label="...">
                                <ul class="pagination justify-content-center">
                                    <% for (int i = 1; i <= (int) request.getSession().getAttribute("nrPages"); ++i) {
                                        if ((int) request.getSession().getAttribute("currentCategory") == 0) {
                                            if ((int) request.getSession().getAttribute("currentPage") == i) { %>
                                    <li class="page-item active"><a class="page-link"
                                                                    href="index?page=<%= i %>"><%= i %><span
                                            class="sr-only">(current)</span></a></li>
                                    <% } else { %>
                                    <li class="page-item"><a class="page-link" href="index?page=<%= i %>"><%= i %>
                                    </a></li>
                                    <% }
                                    } else {
                                        if ((int) request.getSession().getAttribute("currentPage") == i) { %>
                                    <li class="page-item active"><a class="page-link"
                                                                    href="index?category=<%= request.getSession().getAttribute("currentCategory") %>&page=<%= i %>"><%= i %><span
                                            class="sr-only">(current)</span></a></li>
                                    <% } else { %>
                                    <li class="page-item"><a class="page-link"
                                                             href="index?category=<%= request.getSession().getAttribute("currentCategory") %>&page=<%= i %>"><%= i %>
                                    </a></li>
                                    <% }
                                    }
                                    } %>
                                </ul>
                            </nav>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</main>
<footer class="footer bg-dark text-light mt-auto py-3">
    <div class="container">
        <div class="row">
            <div class="col-12 copyright mt-3">
                <p class="float-left"><a href="#">Nazad na vrh</a></p>
                <p class="text-right text-muted">Cikloteka - Java Web Shop projekt</p>
            </div>
        </div>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="js/bootstrap.bundle.min.js" type="text/javascript"></script>
<script src="https://kit.fontawesome.com/3d5d9a5e7a.js" crossorigin="anonymous"></script>
</body>
</html>
