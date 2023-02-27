package hr.project.cikloteka.servlet;

import hr.project.cikloteka.dao.RepositoryFactory;
import hr.project.cikloteka.model.Bike;
import hr.project.cikloteka.model.OrderDetails;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "BikeServlet", value = "/bike")
public class BikeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        OrderDetails cart = new OrderDetails();

        if (request.getParameter("i") != null) {
            try {
                initCart(request, cart);
                int bikeId = Integer.parseInt(request.getParameter("i"));
                final Optional<Bike> bike = RepositoryFactory.getRepository().getBike(bikeId);
                if (!bike.isPresent()) {
                    response.sendRedirect("index");
                    return;
                }
                request.setAttribute("bike", bike.get());
                request.getRequestDispatcher("/WEB-INF/bike.jsp").forward(request, response);
            } catch (Exception e) {
                response.sendRedirect("index");
            }
        } else {
            response.sendRedirect("index");
        }
    }

    private void initCart(HttpServletRequest request, OrderDetails cart) {
        if (request.getSession().getAttribute("cart") != null) {
            cart = (OrderDetails) request.getSession().getAttribute("cart");
        } else {
            request.getSession().setAttribute("cart", cart);
        }
    }
}
