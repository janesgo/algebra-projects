package hr.project.cikloteka.servlet;

import hr.project.cikloteka.dao.RepositoryFactory;
import hr.project.cikloteka.model.OrderDetails;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet(name = "AdminOrd", value = "/admin-ord")
public class AdminOrd extends HttpServlet {
    private static Set<OrderDetails> filterDate(Set<OrderDetails> orders, LocalDate from, LocalDate to) {
        return orders.stream().filter(o ->
                (o.getOrderDate().toLocalDate().equals(from) || o.getOrderDate().toLocalDate().isAfter(from))
                        && (o.getOrderDate().toLocalDate().isEqual(to) || o.getOrderDate().toLocalDate().isBefore(to))
        ).collect(Collectors.toSet());
    }

    private static Set<OrderDetails> filterCustomers(Set<OrderDetails> orders, String customer) {
        return orders.stream()
                .filter(o -> String.join(" ", o.getCustomer().getFirstName().toLowerCase(), o.getCustomer().getLastName().toLowerCase())
                        .contains(customer.toLowerCase())).collect(Collectors.toSet());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            final Set<OrderDetails> orders =new HashSet<>(RepositoryFactory.getRepository().getAllOrderDetails());
            request.getSession().setAttribute("orders", orders);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        request.getRequestDispatcher("/WEB-INF/admin-ord.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            final Set<OrderDetails> orders =new HashSet<>(RepositoryFactory.getRepository().getAllOrderDetails());
            final String customer = request.getParameter("customer");
            final String dateFrom = request.getParameter("date_from");
            final String dateTo = request.getParameter("date_to");

            if (customer == null && (dateFrom == null || dateTo == null)) {
                request.getRequestDispatcher("/WEB-INF/admin-ord.jsp").forward(request, response);
            }

            if (customer.isEmpty() && (dateFrom.isEmpty() || dateTo.isEmpty())) {
                request.getSession().setAttribute("orders", orders);
            } else {
                if (dateFrom.isEmpty() || dateTo.isEmpty()) {
                    final Set<OrderDetails> filtered = filterCustomers(orders, customer);
                    request.getSession().setAttribute("orders", filtered);
                } else if (customer.isEmpty()) {
                    final Set<OrderDetails> filtered = filterDate(orders, LocalDate.parse(dateFrom), LocalDate.parse(dateTo));
                    request.getSession().setAttribute("orders", filtered);
                } else {
                    request.getSession().setAttribute("orders", filterDate(filterCustomers(orders, customer), LocalDate.parse(dateFrom), LocalDate.parse(dateTo)));
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        request.getRequestDispatcher("/WEB-INF/admin-ord.jsp").forward(request, response);
    }
}
