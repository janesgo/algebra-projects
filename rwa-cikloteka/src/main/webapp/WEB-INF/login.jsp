<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Cikloteka - Prijavi se</title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,300,600" rel="stylesheet" type="text/css">
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="row justify-content-center mt-5">
    <section class="w-50 p-4 d-flex justify-content-center pb-4">
        <div style="width: 26rem;">
            <h3 class="text-center mt-5 mb-5">Prijavi se</h3>
            <form action="login" method="post">
                <div class="form-outline mb-4">
                    <input type="email" id="e-mail" name="email" class="form-control" placeholder="E-mail adresa" required>
                    <label for="e-mail"></label>
                </div>
                <div class="form-outline mb-4">
                    <input type="password" id="password" name="password" class="form-control" placeholder="Lozinka" required>
                    <label for="password"></label>
                </div>
                <button type="submit" class="btn btn-primary btn-block mb-6">Prijavi se</button>
                <div class="text-center mt-3">
                    <p>Nisi član? <a href="register">Registriraj se</a></p>
                </div>
            </form>
        </div>
    </section>
</div>
</body>
</html>
