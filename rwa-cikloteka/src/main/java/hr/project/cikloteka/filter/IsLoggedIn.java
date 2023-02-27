package hr.project.cikloteka.filter;

import hr.project.cikloteka.model.Customer;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebFilter(filterName = "IsLoggedIn", urlPatterns = {"/checkout", "/logout", "/profile", "/paypal-pay", "/pay", "/status"})
public class IsLoggedIn implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        final Customer user = (Customer) req.getSession().getAttribute("user");
        final Enumeration<String> headerNames = req.getHeaderNames();
        req.getSession().setAttribute("referer", req.getHeader("Referer"));
        if (user != null) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect("login");
        }
    }
}
