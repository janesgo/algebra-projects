<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title> PayPal Checkout Integration | Cikloteka </title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,300,600" rel="stylesheet" type="text/css">
    <link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="row justify-content-center mt-5">
    <section class="w-50 p-4 d-flex justify-content-center pb-4">
        <div style="width: 26rem;">
            <div class="mb-5"><h3 class="text-center">Cikloteka Paypal plaćanje</h3></div>
            <div id="paypal-button-container"></div>
            <div class="mt-5 text-center"><a href="checkout">Odustani od plaćanja</a></div>
        </div>
    </section>
</div>
</body>
</html>

<script src="https://www.paypal.com/sdk/js?client-id=test&currency=USD"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script>
    paypal.Buttons({
        createOrder: function (data, actions) {
            return actions.order.create({
                purchase_units: [{
                    amount: {
                        value: '${amount}'
                    }
                }]
            });
        },
        onApprove: function (data, actions) {
            return actions.order.capture().then(function (orderData) {
                console.log('Capture result', orderData, JSON.stringify(orderData, null, 2));
                var transaction = orderData.purchase_units[0].payments.captures[0];
                postRedirect("status", transaction.status);
            });
        }

    }).render('#paypal-button-container');

    function postRedirect(redirectUrl, status) {
        const input = '<input type="hidden" name="' + redirectUrl + '" value="' + status + '">';
        const form_part = '<form action="' + redirectUrl + '" method="post">' + input + '</form>';
        const form = $(form_part);
        $('body').append(form);
        $(form).submit();
    }
</script>
</body>
</html>