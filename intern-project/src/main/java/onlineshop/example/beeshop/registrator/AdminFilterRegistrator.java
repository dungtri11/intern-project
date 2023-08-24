package onlineshop.example.beeshop.registrator;

import onlineshop.example.beeshop.annotation.Authority;
import onlineshop.example.beeshop.controller.AccountController;
import onlineshop.example.beeshop.controller.ProductController;
import onlineshop.example.beeshop.filter.AdminRequestFilter;
import onlineshop.example.beeshop.utils.UrlHandler;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Configuration
@ComponentScan
public class AdminFilterRegistrator {



    @Bean
    public FilterRegistrationBean<AdminRequestFilter> adminLoggingFilter() {
        FilterRegistrationBean<AdminRequestFilter> registrationBean =
                new FilterRegistrationBean<>();

        registrationBean.setFilter(new AdminRequestFilter());
        registrationBean.addUrlPatterns(adminListAuthorizedUrls().toArray(String[]::new));
        registrationBean.setOrder(2);
        return registrationBean;
    }

    private List<String> adminListAuthorizedUrls() {
        List<String> urls = new ArrayList<>();
        Set<Class> packageClass = findAllClassesUsingClassLoader("onlineshop.example.beeshop.controller");

        for (Class aClass : packageClass) {
            for (Method method : aClass.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Authority.class)) {
                    Authority authority = method.getAnnotation(Authority.class);
                    RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                    if (authority.admin()) {
                        for (String s : requestMapping.value()) {
                            s = UrlHandler.handleUrl(s);
                            urls.add(s);
                        }
                    }
                }
            }
        }

        return urls;
    }

    private Set<Class> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }

    private Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }
}
