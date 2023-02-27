<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en" class="h-100">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Cikloteka - Blagajna</title>
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
                    <li class="nav-item active">
                        <a class="nav-link" href="cart">Košarica <span class="sr-only">(current)</span></a>
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
    <section class="jumbotron text-center">
        <div class="container">
            <h1 class="jumbotron-heading">BLAGAJNA</h1>
        </div>
    </section>
    <div class="container mb-4">
        <div class="row">
            <div class="col-12">
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th scope="col"></th>
                            <th scope="col">Bicikl</th>
                            <th scope="col" class="text-center">Količina</th>
                            <th scope="col" class="text-right">Cijena</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${cart.orderItems}">
                            <tr>
                                <td><img src="https://dummyimage.com/50x50/2e2c30/0073ff&text=slika+bicikla"/></td>
                                <td>${item.bike.name}</td>
                                <td>${item.quantity}</td>
                                <td class="text-right"><fmt:formatNumber type="number" maxFractionDigits="2"
                                                                         value="${item.price}"/> USD
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                        <tfoot>
                        <tr class="spacer"></tr>
                        <tr>
                            <td colspan="2"></td>
                            <td class="text-right"><strong>Ukupna cijena:</strong></td>
                            <td class="text-right">
                                <strong>
                                    <c:set var="totalPrice" value="${0}"/>
                                    <c:forEach var="item" items="${cart.orderItems}">
                                        <c:set var="totalPrice" value="${totalPrice + item.price}"/>
                                    </c:forEach>
                                    <fmt:formatNumber type="number" maxFractionDigits="2" value="${totalPrice}"/> USD
                                </strong>
                            </td>
                        </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
            <div class="col mt-3 mb-3">
                <div class="row">
                    <div class="col-sm-12 col-md-12 d-flex justify-content-center">
                        <form action="pay" method="post">
                            <div class="mb-3">
                                <label for="payment" class="form-label">Način plaćanja</label>
                                <select id="payment" name="payment" class="form-label" required>
                                    <c:forEach items="${payments}" var="payment">
                                        <option value="${payment.id}">${payment.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary btn-block mb-6">Kupi</button>
                        </form>
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
