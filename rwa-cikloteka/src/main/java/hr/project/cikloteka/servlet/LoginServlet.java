package hr.project.cikloteka.servlet;

import hr.project.cikloteka.dao.RepositoryFactory;
import hr.project.cikloteka.model.Customer;
import hr.project.cikloteka.model.Login;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getSession().getAttribute("user") != null) {
            response.sendRedirect("index");
        } else {
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        Optional<Customer> customer;
        final String email = request.getParameter("email");
        final String password = request.getParameter("password");

        try {
            customer = RepositoryFactory.getRepository().getCustomer(email, password);
        } catch (Exception e) {
            response.sendRedirect("index");
            return;
        }

        if (customer.isPresent()) {
            request.getSession().setAttribute("user", customer.get());
            Login login = new Login();
            login.setUser(customer.get().getEmail());
            login.setAddress(request.getRemoteAddr());
            login.setTime(String.valueOf(LocalDateTime.now()));
            request.getSession().setAttribute("login", login);
            if (request.getSession().getAttribute("checkout") != null) {
                response.sendRedirect("checkout");
            } else {
                final String referer = (String) request.getSession().getAttribute("referer");
                if (referer != null && referer.contains("cart")) {
                    request.getSession().removeAttribute("referer");
                    response.sendRedirect("checkout");
                } else {
                    response.sendRedirect("index");
                }
            }
        } else {
            request.setAttribute("error", "Unknown login, try again");
            doGet(request, response);
        }
    }
}
