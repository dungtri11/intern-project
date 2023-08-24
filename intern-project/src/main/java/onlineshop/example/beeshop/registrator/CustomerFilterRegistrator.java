package onlineshop.example.beeshop.registrator;

import onlineshop.example.beeshop.annotation.Authority;
import onlineshop.example.beeshop.controller.AccountController;
import onlineshop.example.beeshop.controller.ProductController;
import onlineshop.example.beeshop.filter.AdminRequestFilter;
import onlineshop.example.beeshop.filter.CustomerRequestFilter;

import onlineshop.example.beeshop.utils.UrlHandler;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class CustomerFilterRegistrator {
    private final String[] urls = {"/product/recommend"};
    @Bean
    public FilterRegistrationBean<CustomerRequestFilter> customerLoggingFilter() {
        FilterRegistrationBean<CustomerRequestFilter> registrationBean =
                new FilterRegistrationBean<>();

        registrationBean.setFilter(new CustomerRequestFilter());
        registrationBean.addUrlPatterns(urls);

        registrationBean.setOrder(0);
        return registrationBean;
    }


}
