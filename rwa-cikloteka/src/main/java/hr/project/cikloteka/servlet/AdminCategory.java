package hr.project.cikloteka.servlet;

import hr.project.cikloteka.dao.RepositoryFactory;
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

@WebServlet(name = "AdminCategory", value = "/admin-cat")
public class AdminCategory extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            final List<Category> categories = RepositoryFactory.getRepository().getAllCategories();
            request.getSession().setAttribute("categories", categories);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("/WEB-INF/admin-cat.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getParameter("action") == null) {
            response.sendRedirect(request.getHeader("Referer"));
            return;
        }

        final String action = request.getParameter("action");
        try {
            int cId = Integer.parseInt(request.getParameter("item"));
            if (Objects.equals(action, "delete")) {
                final Optional<Category> category = RepositoryFactory.getRepository().getCategory(cId);
                if (category.isPresent()) {
                    RepositoryFactory.getRepository().deleteCategory(category.get());
                } else {
                    response.sendRedirect(request.getHeader("Referer"));
                }
            }
            response.sendRedirect("admin-cat");
        } catch (Exception e) {
            response.sendRedirect(request.getHeader("Referer"));
        }
    }
}
