package hr.project.cikloteka.servlet;

import hr.project.cikloteka.dao.RepositoryFactory;
import hr.project.cikloteka.model.Category;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@WebServlet(name = "AdminCategoryForm", value = "/admin-cat-form")
public class AdminCategoryForm extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String action = request.getParameter("action");
        request.setAttribute("action", action);
        if (Objects.equals(action, "update")) {
            try {
                final int id = Integer.parseInt(request.getParameter("id"));
                final Optional<Category> category = RepositoryFactory.getRepository().getCategory(id);
                category.ifPresent(c -> request.setAttribute("category", c));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        request.getRequestDispatcher("/WEB-INF/admin-cat-form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("index");
    }
}