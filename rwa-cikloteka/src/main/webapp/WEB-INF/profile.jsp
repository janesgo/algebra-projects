<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Cikloteka - korisnički profil</title>
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
                    <li class="nav-item">
                        <a class="nav-link" href="index">Bicikli</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="cart">Košarica</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="profile">Profil <span class="sr-only">(current)</span></a>
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
                        <li class="breadcrumb-item active" aria-current="page">Korisnički profil</li>
                    </ol>
                </nav>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col mb-5">
                <div class="card">
                    <div class="card-header bg-primary text-white">Korisnički profil</div>
                    <div class="card-body">
                        <c:if test="${user != null}">
                            <ul class="list-unstyled mb-1-9">
                                <li class="m-2">Ime:</span> ${user.firstName}</li>
                                <li class="m-2">Prezime:</span> ${user.lastName}</li>
                                <li class="m-2">Email adresa:</span> ${user.email}</li>
                            </ul>
                            <p>Narudžbe:</p>
                            <c:forEach var="order" items="${user_orders}">
                                <div class="card text-dark bg-light mb-3">
                                    <div class="card-body">
                                        <h5 class="card-title">Narudžba ${order.id}</h5>
                                        <p class="card-text">
                                        <ul class="list-unstyled">
                                            <li><b>Datum kupnje:</b> ${order.orderDate.toLocalDate()} ${order.orderDate.toLocalTime()}</li>
                                            <li><b>Način kupovine:</b>
                                                    ${order.payment.name} USD
                                            </li>
                                            <li><b>Ukupna cijena:</b>
                                                    ${order.totalPrice} USD
                                            </li>
                                            <li class="mt-4"><b>Artikli:</b></li>
                                        </ul>
                                        <div class="table-responsive">
                                            <table class="table table-striped">
                                                <thead>
                                                <tr>
                                                    <th scope="col">Bicikl</th>
                                                    <th scope="col">Količina</th>
                                                    <th scope="col">Cijena</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach var="oi" items="${order.orderItems}">
                                                    <tr>
                                                        <td>${oi.bike.name}</td>
                                                        <td>${oi.quantity}</td>
                                                        <td>${oi.price} USD</td>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                </div>
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
