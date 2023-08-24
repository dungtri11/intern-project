package onlineshop.example.beeshop.registrator;


import onlineshop.example.beeshop.filter.SupplierRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SupplierFilterRegistrator {

    private final String[] urls = {"/product/add"};
    @Bean
    public FilterRegistrationBean<SupplierRequestFilter> supplierLoggingFilter() {
        FilterRegistrationBean<SupplierRequestFilter> registrationBean =
                new FilterRegistrationBean<>();

        registrationBean.setFilter(new SupplierRequestFilter());
        registrationBean.addUrlPatterns(urls);
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
