package onlineshop.example.beeshop.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Component
@Order(1)
public class AdminRequestFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(AdminRequestFilter.class);
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
            if (!req.getHeader("authorise").equalsIgnoreCase("ADMIN")) {
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                res.getWriter().write("User is not an Admin");
                logger.info("Response Status: {}", HttpStatus.UNAUTHORIZED);
            } else {
                chain.doFilter(request, response);
            }


    }
}
