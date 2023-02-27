<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Cikloteka - Registriraj se</title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,300,600" rel="stylesheet" type="text/css">
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="row justify-content-center mt-5">
    <section class="w-50 p-4 d-flex justify-content-center pb-4">
        <div style="width: 26rem;">
            <h3 class="text-center mt-5 mb-5">Registriraj se</h3>
            <form action="register" method="post">
                <div class="form-outline mb-4">
                    <input type="text" id="firstname" name="firstname" class="form-control" placeholder="Ime" required>
                    <label for="firstname"></label>
                </div>
                <div class="form-outline mb-4">
                    <input type="text" id="lastname" name="lastname" class="form-control" placeholder="Prezime" required>
                    <label for="lastname"></label>
                </div>
                <div class="form-outline mb-4">
                    <input type="email" id="email" name="email" class="form-control" placeholder="E-mail adresa" required>
                    <label for="email"></label>
                </div>
                <div class="form-outline mb-4">
                    <input type="password" id="password" name="password" class="form-control" placeholder="Lozinka" required>
                    <label for="password"></label>
                </div>
                <button type="submit" class="btn btn-primary btn-block mb-6">Registriraj se</button>
            </form>
        </div>
    </section>
</div>
</body>
</html>
