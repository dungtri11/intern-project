package onlineshop.example.beeshop.dto;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCriteriaDTO {
    private String filterId;
    private String filterCategory;
    private String filterProvider;
    private String filterSale;
    private String filterPrice;
    private String orderBy;
    private String page;
}
