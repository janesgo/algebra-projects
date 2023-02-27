package hr.project.cikloteka.servlet;

import hr.project.cikloteka.dao.RepositoryFactory;
import hr.project.cikloteka.model.Bike;
import hr.project.cikloteka.model.OrderDetails;
import hr.project.cikloteka.model.OrderItem;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "ItemServlet", value = "/item")
public class ItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("index");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("cart") == null
                || request.getParameter("action") == null) {
            response.sendRedirect(request.getHeader("Referer"));
            return;
        }

        try {
            final OrderDetails cart = (OrderDetails) request.getSession().getAttribute("cart");
            final int action = Integer.parseInt(request.getParameter("action"));

            if (action < 0) {
                final int id = Integer.parseInt(request.getParameter("item"));
                final Optional<OrderItem> item = cart.getOrderItems().stream().filter(i -> i.getId() == id).findFirst();
                if (item.isPresent()) {
                    cart.getOrderItems().remove(item.get());
                }
                response.sendRedirect("cart");
            } else if (action > 0) {
                final int id = Integer.parseInt(request.getParameter("bike"));
                final int quantity = Integer.parseInt(request.getParameter("quantity"));
                final Optional<Bike> bike = RepositoryFactory.getRepository().getBike(id);

                if (bike.isPresent()) {
                    final double price = calculatePrice(quantity, bike.get());
                    if (price == 0) {
                        response.sendRedirect(request.getHeader("Referer"));
                        return;
                    }
                    OrderItem item = new OrderItem(bike.get(), quantity, price);
                    // TEST THIS
                    item.setId(cart.getOrderItems().size());
                    cart.getOrderItems().add(item);
                }
                response.sendRedirect(request.getHeader("Referer"));
            } else {
                final int id = Integer.parseInt(request.getParameter("item"));
                final int quantity = Integer.parseInt(request.getParameter("quantity"));
                final Optional<OrderItem> item = cart.getOrderItems().stream().filter(i -> i.getId() == id).findFirst();
                if (item.isPresent()) {
                    final double price = calculatePrice(quantity, item.get().getBike());
                    if (price == 0) {
                        response.sendRedirect(request.getHeader("Referer"));
                        return;
                    }
                    item.get().setQuantity(quantity);
                    item.get().setPrice(price);
                }
                response.sendRedirect("cart");
            }

        } catch (Exception e) {
            response.sendRedirect("index");
        }
    }

    private double calculatePrice(int quantity, Bike bike) {
        if (quantity < 1 || quantity > 10) {
            return 0;
        } else if (quantity == 1) {
            return bike.getPrice();
        } else {
            return quantity * bike.getPrice();
        }
    }
}
