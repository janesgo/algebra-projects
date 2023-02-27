<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html class="h-100">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Cikloteka - admin</title>
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
                        <a class="nav-link" href="admin-cat">Kategorije</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="admin-prod">Bicikli</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="admin-log">Logovi</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="admin-ord">Narudžbe<span class="sr-only">(current)</span></a>
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="nav-item"><a class="btn btn-secondary btn-sm" href="logout"><i
                            class="fa fa-user-circle"></i> Odjava</a></li>
                </ul>
            </div>
        </div>
    </nav>
</header>
<main role="main" class="flex-shrink-0">
    <div class="container mb-4">
        <div class="row">
            <div class="col-12">
                <div class="col-6 mb-4">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Filtriranje narudžbi</h5>
                            <form action="admin-ord" method="post">
                                <div class="mb-3">
                                    <label for="customer" class="form-label">Kupac</label>
                                    <input type="text" class="form-control" id="customer" name="customer">
                                </div>
                                <div class="mb-3">
                                    <label for="date_from" class="form-label">Datum od</label>
                                    <input type="date" class="form-control" id="date_from" name="date_from">
                                </div>
                                <div class="mb-3">
                                    <label for="date_to" class="form-label">Datum do</label>
                                    <input type="date" class="form-control" id="date_to" name="date_to">
                                </div>
                                <button type="submit" class="btn btn-primary">Filtriraj</button>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="col-12">
                    <c:forEach var="order" items="${orders}">
                        <div class="card text-dark bg-light mb-3">
                            <div class="card-body">
                                <h5 class="card-title">Narudžba ${order.id}</h5>
                                <p class="card-text">
                                <ul class="list-unstyled">
                                    <li><b>Kupac: </b></li>
                                    <li class="ml-3"><b>Ime i prezime:</b>
                                            ${order.customer.firstName} ${order.customer.lastName}</li>
                                    <li class="ml-3"><b>E-mail:</b>
                                            ${order.customer.email}</li>
                                    <li><b>Datum kupnje:</b> ${order.orderDate.toLocalDate()} ${order.orderDate.toLocalTime()}</li>
                                    <li><b>Način kupovine:</b>
                                            ${order.payment.name}
                                    </li>
                                    <li><b>Ukupna cijena:</b>
                                            ${order.totalPrice} USD
                                    </li>
                                    <li class="mt-4"><b>Artikli:</b></li>
                                </ul>
                                <div class="table-responsive">
                                    <table class="table table-striped">
                                        <thead class="thead-dark">
                                        <tr>
                                            <th scope="col">Bicikl</th>
                                            <th scope="col">Brand</th>
                                            <th scope="col">Kategorija</th>
                                            <th scope="col">Količina</th>
                                            <th scope="col">Cijena</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="oi" items="${order.orderItems}">
                                            <tr>
                                                <td>${oi.bike.name}</td>
                                                <td>${oi.bike.brand.name}</td>
                                                <td>${oi.bike.category.name}</td>
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
