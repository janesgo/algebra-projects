package hr.project.cikloteka.filter;

import hr.project.cikloteka.model.Customer;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebFilter(filterName = "IsAdmin",
        urlPatterns = {
                "/admin-cat",
                "/admin-cat-addupdate",
                "/admin-cat-delete",
                "/admin-cat-form",
                "/admin-prod",
                "/admin-prod-addupdate",
                "/admin-prod-delete",
                "/admin-prod-form",
                "/admin-ord",
                "/admin-log"
        })
public class IsAdmin implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        final Customer user = (Customer) req.getSession().getAttribute("user");
        if (user != null && Objects.equals(user.getUserType(), "admin")) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect("index");
        }
    }
}
