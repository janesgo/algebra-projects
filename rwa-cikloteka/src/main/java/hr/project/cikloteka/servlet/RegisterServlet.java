package hr.project.cikloteka.servlet;

import hr.project.cikloteka.dao.RepositoryFactory;
import hr.project.cikloteka.model.Customer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    public static final String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getSession(false) == null || request.getSession(false).getAttribute("user") == null) {
            request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        } else {
            response.sendRedirect("index");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        boolean ok = true;

        final String firstname = request.getParameter("firstname");
        if (firstname == null || firstname.isEmpty() || firstname.length() > 255) {
            ok = false;
        }
        final String lastname = request.getParameter("lastname");
        if (lastname == null || lastname.isEmpty() || lastname.length() > 255) {
            ok = false;
        }
        final String email = request.getParameter("email");
        if (email == null || email.isEmpty() || email.length() > 255 || !email.matches(EMAIL_REGEX)) {
            ok = false;
        }
        final String password = request.getParameter("password");
        if (lastname == null || lastname.isEmpty() || lastname.length() > 64) {
            ok = false;
        }

        if (ok) {
            try {
                RepositoryFactory.getRepository().addCustomer(new Customer(firstname, lastname, email, password));
                response.sendRedirect("login");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            response.sendRedirect("register");
        }
    }
}
