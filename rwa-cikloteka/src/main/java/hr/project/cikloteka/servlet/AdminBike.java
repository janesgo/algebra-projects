package hr.project.cikloteka.servlet;

import hr.project.cikloteka.dao.RepositoryFactory;
import hr.project.cikloteka.model.Bike;
import hr.project.cikloteka.model.Category;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@WebServlet(name = "AdminBike", value = "/admin-prod")
public class AdminBike extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            final List<Bike> bikes = RepositoryFactory.getRepository().getAllBikes();
            request.getSession().setAttribute("bikes", bikes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("/WEB-INF/admin-prod.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getParameter("action") == null) {
            response.sendRedirect(request.getHeader("Referer"));
            return;
        }

        final String action = request.getParameter("action");
        try {
            int id = Integer.parseInt(request.getParameter("item"));
            if (Objects.equals(action, "delete")) {
                final Optional<Bike> bike = RepositoryFactory.getRepository().getBike(id);
                if (bike.isPresent()) {
                    RepositoryFactory.getRepository().deleteBike(bike.get());
                } else {
                    response.sendRedirect(request.getHeader("Referer"));
                }
            }
            response.sendRedirect("admin-prod");
        } catch (Exception e) {
            response.sendRedirect(request.getHeader("Referer"));
        }
    }
}
