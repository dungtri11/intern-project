package onlineshop.example.beeshop.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCriteriaModel {
    private String filterId;
    private String filterCategory;
    private String filterSupplier;
    private String filterSale;
    private String filterPrice;
    private String orderBy;
    private String page;
}
