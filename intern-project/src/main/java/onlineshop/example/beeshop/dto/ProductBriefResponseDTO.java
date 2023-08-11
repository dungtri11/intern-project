package onlineshop.example.beeshop.dto;

import lombok.Getter;
import lombok.Setter;
import onlineshop.example.beeshop.common.ProductCategory;
import onlineshop.example.beeshop.common.ProductRate;
import onlineshop.example.beeshop.common.ProductStatus;
import onlineshop.example.beeshop.entity.Product;

@Getter
@Setter
public class ProductBriefResponseDTO {
    private Long id;
    private String name;
    private ProductCategory productCategory;
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
    }
}
