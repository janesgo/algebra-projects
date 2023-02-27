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
            <div class="col-6">
                <h3 class="text-center mt-5 mb-5">Bicikl</h3>
                <form action="admin-prod-addupdate" method="post">
                    <div class="form-outline mb-4">
                        <c:choose>
                        <c:when test="${action == 'update' && bike != null}">
                        <input type="hidden" id="action" name="action" value="update">
                        <input type="hidden" id="id" name="id" value="${bike.id}">
                        <div class="mb-3">
                            <label for="bike-br" class="form-label">Brand</label>
                            <select id="bike-br" name="bike-br" class="form-label" required>
                                <c:forEach items="${brands}" var="brand">
                                    <c:choose>
                                        <c:when test="${brand.name eq bike.brand.name}">
                                            <option value="${brand.id}" selected>${brand.name}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${brand.id}">${brand.name}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="bike-n" class="form-label">Naziv</label>
                            <input type="text" id="bike-n" name="bike-name" class="form-control"
                                   value="${bike.name}" required>
                        </div>
                        <div class="mb-3">
                            <label for="bike-ca" class="form-label">Kategorija</label>
                            <select id="bike-ca" name="bike-ct" class="form-label" required>
                                <c:forEach items="${cats}" var="cat">
                                    <c:choose>
                                        <c:when test="${cat.name eq bike.category.name}">
                                            <option value="${cat.id}" selected>${cat.name}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${cat.id}">${cat.name}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="bike-my" class="form-label">Godina</label>
                            <input type="number" id="bike-my" name="bike-modelyear" class="form-control"
                                   value="${bike.modelYear}" required>
                        </div>
                        <div class="mb-3">
                            <label for="bike-d" class="form-label">Opis</label>
                            <input type="text" id="bike-d" name="bike-dsc" class="form-control"
                                   value="${bike.description}" required>
                        </div>
                        <div class="mb-3">
                            <label for="bike-p" class="form-label">Cijena</label>
                            <input type="number" id="bike-p" name="bike-price" class="form-control"
                                   value="${bike.price}" required></div>
                    </div>
                    <button type="submit" class="btn btn-primary btn-block mb-6">Uredi</button>
                    </c:when>
                    <c:otherwise>
                    <input type="hidden" id="action" name="action" value="add">
                    <div class="mb-3">
                        <label for="bike-b" class="form-label">Brand</label>
                        <select id="bike-b" name="bike-brand" class="form-label" required>
                            <c:forEach items="${brands}" var="brand">
                                <option value="${brand.id}">${brand.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="bike-n" class="form-label">Naziv</label>
                        <input type="text" id="bike-cn" name="bike-name" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label for="bike-c" class="form-label">Kategorija</label>
                        <select id="bike-c" name="bike-cat" class="form-label" required>
                            <c:forEach items="${cats}" var="cat">
                                <option value="${cat.id}">${cat.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="bike-my" class="form-label">Godina</label>
                        <input type="number" id="bike-cmy" name="bike-modelyear" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label for="bike-cd" class="form-label">Opis</label>
                        <input type="text" id="bike-cd" name="bike-dsc" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label for="bike-p" class="form-label">Cijena</label>
                        <input type="number" id="bike-cp" name="bike-price" class="form-control" required></div>
            </div>
            <button type="submit" class="btn btn-primary btn-block mb-6">Izradi</button>
            </c:otherwise>
            </c:choose>
            </form>
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
