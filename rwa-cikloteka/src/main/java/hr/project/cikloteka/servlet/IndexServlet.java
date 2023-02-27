package hr.project.cikloteka.servlet;

import hr.project.cikloteka.dao.RepositoryFactory;
import hr.project.cikloteka.model.Bike;
import hr.project.cikloteka.model.Category;
import hr.project.cikloteka.model.Customer;
import hr.project.cikloteka.model.OrderDetails;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "IndexServlet", value = "/index")
public class IndexServlet extends HttpServlet {

    public static final int BIKES_PER_PAGE = 9;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final Customer user = (Customer) request.getSession().getAttribute("user");
        if (user != null && user.getUserType().equals("admin")) {
            response.sendRedirect("admin-cat");
            return;
        }

        OrderDetails cart = new OrderDetails();

        try {
            initCart(request, cart);

            final List<Category> categories = RepositoryFactory.getRepository().getAllCategories();

            if (request.getSession().getAttribute("categories") == null) {
                request.getSession().setAttribute("categories", categories);
            }

            int category = 0;

            int categoriesCount = RepositoryFactory.getRepository().getCategoriesCount();
            if (request.getParameter("category") != null) {
                try {
                    category = Integer.parseInt(request.getParameter("category"));
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                }
            }

            int bikesCount;
            if (category == 0) {
                bikesCount = RepositoryFactory.getRepository().getBikesCount();
            } else {
                bikesCount = RepositoryFactory.getRepository().getBikesCount(category);
            }

            if (bikesCount > 0) {
                int page = 1;
                int nrPages = (int) Math.ceil(bikesCount * 1.0 / BIKES_PER_PAGE);
                if (request.getParameter("page") != null) {
                    try {
                        int pageRequest = Integer.parseInt(request.getParameter("page"));
                        if (pageRequest <= nrPages) {
                            page = pageRequest;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(e.getMessage());
                    }
                }

                if (category == 0) {
                    request.getSession().setAttribute("bikes",
                            RepositoryFactory.getRepository()
                                    .getAllBikes((page - 1) * BIKES_PER_PAGE, BIKES_PER_PAGE));
                } else {
                    request.getSession().setAttribute("bikes",
                            RepositoryFactory.getRepository()
                                    .getAllBikes(category, (page - 1) * BIKES_PER_PAGE, BIKES_PER_PAGE));
                }
                request.getSession().setAttribute("currentCategory", category);
                final int cat = category;
                List<Category> selected = categories.stream().filter(c -> c.getId() == cat).collect(Collectors.toList());
                if (!selected.isEmpty()) {
                    request.getSession().setAttribute("selected", selected.get(0));
                } else {
                    request.getSession().setAttribute("selected", "");
                }
                request.getSession().setAttribute("nrPages", nrPages);
                request.getSession().setAttribute("currentPage", page);
            } else {
                request.getSession().setAttribute("bikes", new ArrayList<Bike>());
            }
            request.getRequestDispatcher("/WEB-INF/bikes.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void initCart(HttpServletRequest request, OrderDetails cart) {
        if (request.getSession().getAttribute("cart") != null) {
            cart = (OrderDetails) request.getSession().getAttribute("cart");
        } else {
            request.getSession().setAttribute("cart", cart);
        }
    }
}
