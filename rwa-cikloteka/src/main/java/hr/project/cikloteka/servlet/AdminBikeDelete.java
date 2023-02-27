package hr.project.cikloteka.servlet;

import hr.project.cikloteka.dao.RepositoryFactory;
import hr.project.cikloteka.model.Bike;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "AdminBikeDelete", value = "/admin-prod-delete")
public class AdminBikeDelete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            final int id = Integer.parseInt(request.getParameter("id"));
            final Optional<Bike> bike = RepositoryFactory.getRepository().getBike(id);
            if (bike.isPresent()) {
                RepositoryFactory.getRepository().deleteBike(bike.get());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        response.sendRedirect("admin-prod");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("index");
    }
}
