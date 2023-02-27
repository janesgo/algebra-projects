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

@WebServlet(name = "CheckoutServlet", value = "/checkout")
public class CheckoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("cart") == null
                || ((OrderDetails) request.getSession().getAttribute("cart")).getOrderItems().size() == 0) {
            response.sendRedirect("index");
            return;
        }

        try {
            request.setAttribute("payments", RepositoryFactory.getRepository().getAllPayments());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        request.getRequestDispatcher("/WEB-INF/checkout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("index");
    }
}
