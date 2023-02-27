<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html class="h-100">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Cikloteka - Bicikl</title>
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
                        <li class="breadcrumb-item"><a
                                href="index?category=${bike.category.id}">${bike.category.name}</a>
                        </li>
                        <li class="breadcrumb-item active" aria-current="page">${bike.name}</li>
                    </ol>
                </nav>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-12 col-lg-6">
                <div class="card bg-light mb-3">
                    <div class="card-body">
                        <img class="img-fluid" src="https://dummyimage.com/800x800/2e2c30/0073ff&text=slika+bicikla"/>
                    </div>
                </div>
            </div>
            <div class="col-12 col-lg-6 add_to_cart_block">
                <div class="card bg-light mb-3">
                    <div class="card-body">
                        <h4 class="card-title">${bike.name}</h4>
                        <p><b>Kategorija: </b>${bike.category.name}</p>
                        <p><b>Marka: </b>${bike.brand.name}</p>
                        <p><b>Godina: </b>${bike.modelYear}</p>
                        <p><b>Opis:</b></p>
                        <p class="card-text">${bike.description}</p>
                        <p><b>Cijena: </b><fmt:formatNumber type="number" maxFractionDigits="2" value="${bike.price}"/>
                            USD</p>
                        <form action="item" method="post">
                            <div class="form-group">
                                <label>Količina:</label>
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <button type="button" class="quantity-left-minus btn btn-secondary btn-number"
                                                data-type="minus" data-field="">
                                            <i class="fa fa-minus"></i>
                                        </button>
                                    </div>
                                    <input type="hidden" id="action" name="action" value="1">
                                    <input type="hidden" id="bike" name="bike" value="${bike.id}">
                                    <input type="number" class="form-control" id="quantity" name="quantity" min="1"
                                           max="10"
                                           value="1" oninput="validity.valid||(value='');">
                                    <div class="input-group-append">
                                        <button type="button" class="quantity-right-plus btn btn-secondary btn-number"
                                                data-type="plus" data-field="">
                                            <i class="fa fa-plus"></i>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <button type="submit" class="btn btn-danger btn-lg btn-block text-uppercase">
                                <i class="fa fa-shopping-cart"></i> Dodaj u košaricu
                            </button>
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
<div class="modal fade" id="productModal" tabindex="-1" role="dialog" aria-labelledby="productModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="productModalLabel">Product title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <img class="img-fluid" src="https://dummyimage.com/1200x1200/55595c/fff"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="js/bootstrap.bundle.min.js" type="text/javascript"></script>
<script src="https://kit.fontawesome.com/3d5d9a5e7a.js" crossorigin="anonymous"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var quantity = 1;

        $('.quantity-right-plus').click(function (e) {
            e.preventDefault();
            var quantity = parseInt($('#quantity').val());
            if (quantity < 10) {
                $('#quantity').val(quantity + 1);
            }
        });

        $('.quantity-left-minus').click(function (e) {
            e.preventDefault();
            var quantity = parseInt($('#quantity').val());
            if (quantity > 1) {
                $('#quantity').val(quantity - 1);
            }
        });

    });
</script>
</body>
</html>
