package onlineshop.example.beeshop.annotation;

import onlineshop.example.beeshop.common.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Authorized {
    boolean customer() default true;
    boolean supplier() default true;
    boolean admin() default true;
}
