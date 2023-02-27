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
import java.util.Objects;
import java.util.Optional;

@WebServlet(name = "AdminBikeAddUpdate", value = "/admin-prod-addupdate")
public class AdminBikeAddUpdate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("admin-prod");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String action = request.getParameter("action");
        if (Objects.equals(action, "update")) {
            try {
                final int id = Integer.parseInt(request.getParameter("id"));
                final Optional<Bike> bike = RepositoryFactory.getRepository().getBike(id);
                if (bike.isPresent()) {
                    final Optional<Brand> brand = RepositoryFactory.getRepository().getBrand(Integer.parseInt(request.getParameter("bike-br")));
                    if (brand.isPresent()) {
                        bike.get().setBrand(brand.get());
                    }
                    final Optional<Category> cat = RepositoryFactory.getRepository().getCategory(Integer.parseInt(request.getParameter("bike-ct")));
                    if (cat.isPresent()) {
                        bike.get().setCategory(cat.get());
                    }
                    final String name = request.getParameter("bike-name");
                    if (name != null && !name.isEmpty()) {
                        bike.get().setName(name);
                    }

                    final String year = request.getParameter("bike-modelyear");
                    if (year != null && !year.isEmpty()) {
                        final int i = Integer.parseInt(year);
                        bike.get().setModelYear(i);
                    }

                    final String desc = request.getParameter("bike-dsc");
                    if (desc != null && !desc.isEmpty()) {
                        bike.get().setDescription(desc);
                    }

                    final String price = request.getParameter("bike-price");
                    if (price != null && !price.isEmpty()) {
                        final double p = Double.parseDouble(price);
                        bike.get().setPrice(p);
                    }
                    RepositoryFactory.getRepository().updateBike(bike.get());
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (Objects.equals(action, "add")) {
            try {
                if ((request.getParameter("bike-brand") != null && !request.getParameter("bike-brand").isEmpty())
                        && (request.getParameter("bike-name") != null && !request.getParameter("bike-name").isEmpty())
                        && (request.getParameter("bike-cat") != null && !request.getParameter("bike-cat").isEmpty())
                        && (request.getParameter("bike-modelyear") != null && !request.getParameter("bike-modelyear").isEmpty())
                        && (request.getParameter("bike-dsc") != null && !request.getParameter("bike-dsc").isEmpty())
                        && (request.getParameter("bike-price") != null && !request.getParameter("bike-price").isEmpty())) {
                    Bike bike = new Bike();
                    bike.setPrice(Double.parseDouble(request.getParameter("bike-price")));
                    bike.setName(request.getParameter("bike-name"));
                    bike.setDescription(request.getParameter("bike-dsc"));
                    bike.setModelYear(Integer.parseInt(request.getParameter("bike-modelyear")));
                    final Optional<Brand> brand = RepositoryFactory.getRepository().getBrand(Integer.parseInt(request.getParameter("bike-brand")));
                    if (brand.isPresent()) {
                        bike.setBrand(brand.get());
                    }
                    final Optional<Category> cat = RepositoryFactory.getRepository().getCategory(Integer.parseInt(request.getParameter("bike-cat")));
                    if (cat.isPresent()) {
                        bike.setCategory(cat.get());
                    }
                    RepositoryFactory.getRepository().addBike(bike);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        response.sendRedirect("admin-prod");
    }
}

