package hr.project.cikloteka.servlet;

import hr.project.cikloteka.model.OrderDetails;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "CartServlet", value = "/cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderDetails cart = new OrderDetails();
        initCart(request, cart);
        request.getRequestDispatcher("/WEB-INF/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("index");
    }

    private void initCart(HttpServletRequest request, OrderDetails cart) {
        if (request.getSession().getAttribute("cart") != null) {
            cart = (OrderDetails) request.getSession().getAttribute("cart");
        } else {
            request.getSession().setAttribute("cart", cart);
        }
    }
}
