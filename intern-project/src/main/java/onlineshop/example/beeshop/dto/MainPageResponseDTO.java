package onlineshop.example.beeshop.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MainPageResponseDTO {
    private List<ProductBriefResponseDTO> saleProducts;
    private List<ProductBriefResponseDTO> recommendProducts;
}
