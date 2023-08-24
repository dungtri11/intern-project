package onlineshop.example.beeshop.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerRequestFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(CustomerRequestFilter.class);
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (!req.getHeader("authorise").equalsIgnoreCase("Customer")) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.getWriter().write("User is not a Customer");
            logger.info("Response Status: {}", HttpStatus.UNAUTHORIZED);
        } else {

            chain.doFilter(request, response);
        }
    }
}
