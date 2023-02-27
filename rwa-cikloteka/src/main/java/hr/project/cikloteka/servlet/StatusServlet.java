package hr.project.cikloteka.servlet;

import hr.project.cikloteka.dao.RepositoryFactory;
import hr.project.cikloteka.model.OrderDetails;
import hr.project.cikloteka.model.OrderItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StatusServlet", value = "/status")
public class StatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("index");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("cart") == null
                || ((OrderDetails) request.getSession().getAttribute("cart")).getOrderItems().size() == 0) {
            response.sendRedirect("index");
            return;
        }

        final String status = request.getParameter("status");
        if (status != null && status.equals("COMPLETED")) {
            final OrderDetails cart = (OrderDetails) request.getSession().getAttribute("cart");
            final int order_id;
            try {
                order_id = RepositoryFactory.getRepository().addOrderDetails(cart);
                cart.setId(order_id);
                for (OrderItem o : cart.getOrderItems()) {
                    o.setOrder(cart);
                    RepositoryFactory.getRepository().addOrderItem(o);

                }
                request.getSession().removeAttribute("cart");
                response.sendRedirect("index");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                response.sendRedirect("checkout");
            }
        } else {
            response.sendRedirect("checkout");
        }
    }
}
