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
                    <li class="nav-item active">
                        <a class="nav-link" href="admin-prod">Bicikli<span
                                class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="admin-log">Logovi</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="admin-ord">Narudžbe</a>
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
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th scope="col">Brand</th>
                            <th scope="col">Naziv</th>
                            <th scope="col">Godina</th>
                            <th scope="col">Kategorija</th>
                            <th scope="col">Cijena</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="bike" items="${bikes}">
                            <tr>
                                <td>${bike.brand.name}</td>
                                <td>${bike.name}</td>
                                <td>${bike.modelYear}</td>
                                <td>${bike.category.name}</td>
                                <td>${bike.price} USD</td>
                                <td class="text-right">
                                    <a type="button" class="btn btn-sm btn-info"
                                       href="admin-prod-form?action=update&id=${bike.id}">Uredi</a>
                                    <a type="button" class="btn btn-sm btn-danger"
                                       href="admin-prod-delete?id=${bike.id}">Obriši</a></td>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col mt-3 mb-3">
                    <div class="row">
                        <div class="col-sm-12 col-md-4 text-right">
                            <a class="btn btn-lg btn-block btn-success text-uppercase" href="admin-prod-form?action=add">Dodaj
                                bicikl</a>
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
