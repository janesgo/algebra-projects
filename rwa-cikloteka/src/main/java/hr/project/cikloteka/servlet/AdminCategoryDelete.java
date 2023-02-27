package hr.project.cikloteka.servlet;

import hr.project.cikloteka.dao.RepositoryFactory;
import hr.project.cikloteka.model.Category;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "AdminCategoryDelete", value = "/admin-cat-delete")
public class AdminCategoryDelete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            final int id = Integer.parseInt(request.getParameter("id"));
            final Optional<Category> category = RepositoryFactory.getRepository().getCategory(id);
            if (category.isPresent()) {
                RepositoryFactory.getRepository().deleteCategory(category.get());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        response.sendRedirect("admin-cat");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("index");
    }
}
