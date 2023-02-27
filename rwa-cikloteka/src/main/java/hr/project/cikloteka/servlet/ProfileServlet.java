package hr.project.cikloteka.servlet;

import hr.project.cikloteka.dao.RepositoryFactory;
import hr.project.cikloteka.model.Customer;
import hr.project.cikloteka.model.OrderDetails;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProfileServlet", value = "/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") != null) {
            final Customer user = (Customer) request.getSession().getAttribute("user");
            try {
                final List<OrderDetails> orders = RepositoryFactory.getRepository().getAllOrderDetails(user.getId());
                request.getSession().setAttribute("user_orders", orders);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            response.sendRedirect("index");
        }
        request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("index");
    }
}
