package hr.project.cikloteka.servlet;

import hr.project.cikloteka.model.OrderDetails;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "PaypalPay", value = "/paypal-pay")
public class PaypalPayServlet extends HttpServlet {
    private static final String ID = "XXX";
    private static final String SECRET = "XXX";
    private static final String executionMode = "sandbox";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("cart") == null
                || ((OrderDetails) request.getSession().getAttribute("cart")).getOrderItems().size() == 0) {
            response.sendRedirect("index");
            return;
        }
        final OrderDetails cart = (OrderDetails) request.getSession().getAttribute("cart");
        request.setAttribute("amount", cart.getTotalPrice());
        request.getRequestDispatcher("/WEB-INF/paypal.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("index");
    }
}
