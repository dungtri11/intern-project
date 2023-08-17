package onlineshop.example.beeshop.dto;

import lombok.*;
import onlineshop.example.beeshop.common.ProductCategory;
import onlineshop.example.beeshop.common.ProductRate;
import onlineshop.example.beeshop.common.ProductStatus;
import onlineshop.example.beeshop.entity.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Getter
@Setter
public class ProductBriefResponseDTO {
    private Long id;
    private String name;
    private ProductCategory productCategory;
    private String imageUrl;
    private Double cost;
    private Double sale;
    private Double price;
    private ProductRate rate;
    private ProductStatus status;


    public ProductBriefResponseDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.productCategory = product.getCategory();
        this.cost = product.getCost();
        this.sale = product.getSale();
        this.price = product.getPrice();
        this.rate = product.getProductRate();
        this.status = product.getStatus();
        this.imageUrl = "http://localhost:8080/img/download/" + product.getImage().getName();
    }

}
