package hr.project.cikloteka.servlet;

import hr.project.cikloteka.dao.RepositoryFactory;
import hr.project.cikloteka.model.Bike;
import hr.project.cikloteka.model.Brand;
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

@WebServlet(name = "AdminBikeForm", value = "/admin-prod-form")
public class AdminBikeForm extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            final String action = request.getParameter("action");
            request.setAttribute("action", action);
            final List<Category> cats = RepositoryFactory.getRepository().getAllCategories();
            request.getSession().setAttribute("cats", cats);
            final List<Brand> brands = RepositoryFactory.getRepository().getAllBrands();
            request.getSession().setAttribute("brands", brands);

            if (Objects.equals(action, "update")) {
                final int id = Integer.parseInt(request.getParameter("id"));
                final Optional<Bike> bike = RepositoryFactory.getRepository().getBike(id);

                bike.ifPresent(b -> request.setAttribute("bike", b));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        request.getRequestDispatcher("/WEB-INF/admin-prod-form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("index");
    }
}
