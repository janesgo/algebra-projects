package hr.project.cikloteka.servlet;

import hr.project.cikloteka.dao.RepositoryFactory;
import hr.project.cikloteka.model.Customer;
import hr.project.cikloteka.model.OrderDetails;
import hr.project.cikloteka.model.OrderItem;
import hr.project.cikloteka.model.Payment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@WebServlet(name = "PayServlet", value = "/pay")
public class PayServlet extends HttpServlet {
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
        final OrderDetails cart = (OrderDetails) request.getSession().getAttribute("cart");
        try {
            final int id = Integer.parseInt(request.getParameter("payment"));
            final Optional<Payment> payment = RepositoryFactory.getRepository().getPayment(id);
            if (payment.isPresent()) {
                cart.setPayment(payment.get());
                cart.setTotalPrice(cart.getOrderItems().stream()
                        .map(OrderItem::getPrice).reduce(0.0, Double::sum));

                cart.setCustomer((Customer) request.getSession().getAttribute("user"));
                cart.setOrderDate(LocalDateTime.now());
                if (payment.get().getName().equals("paypal")) {
                    request.getSession().setAttribute("cart", cart);
                    response.sendRedirect("paypal-pay");
                } else {
                    final int order_id = RepositoryFactory.getRepository().addOrderDetails(cart);
                    cart.setId(order_id);
                    for (OrderItem o : cart.getOrderItems()) {
                        o.setOrder(cart);
                        RepositoryFactory.getRepository().addOrderItem(o);
                    }
                    request.getSession().removeAttribute("cart");
                    response.sendRedirect("index");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.sendRedirect("checkout");
        }
    }
}
