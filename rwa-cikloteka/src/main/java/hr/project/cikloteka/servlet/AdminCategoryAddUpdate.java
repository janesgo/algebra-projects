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

@WebServlet(name = "AdminCategoryAddUpdate", value = "/admin-cat-addupdate")
public class AdminCategoryAddUpdate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("admin-cat");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String action = request.getParameter("action");
        if (Objects.equals(action, "update")) {
            try {
                final int id = Integer.parseInt(request.getParameter("id"));
                final Optional<Category> category = RepositoryFactory.getRepository().getCategory(id);
                if (category.isPresent()) {
                    final String name = request.getParameter("category");
                    if ((name != null || !name.isEmpty())
                            && RepositoryFactory.getRepository().getAllCategories().stream()
                            .noneMatch(c -> c.getName().equalsIgnoreCase(name))) {
                        category.get().setName(name);
                        RepositoryFactory.getRepository().updateCategory(category.get());
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (Objects.equals(action, "add")) {
            try {
                if ((request.getParameter("category") != null || !request.getParameter("category").isEmpty()) &&
                        RepositoryFactory.getRepository().getAllCategories().stream().noneMatch(c -> Objects.equals(c.getName().toLowerCase(), request.getParameter("category").toLowerCase()))
                ) {
                    Category category = new Category();
                    category.setName(request.getParameter("category"));
                    RepositoryFactory.getRepository().addCategory(category);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        response.sendRedirect("admin-cat");
    }
}

