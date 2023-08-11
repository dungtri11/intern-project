package onlineshop.example.beeshop.controller;

import onlineshop.example.beeshop.dto.ProductBriefResponseDTO;
import onlineshop.example.beeshop.model.ProductCriteriaModel;
import onlineshop.example.beeshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @RequestMapping(value = "/mainpage", method = RequestMethod.GET)
    public List<ProductBriefResponseDTO> showMainPage(ProductCriteriaModel productCriteriaModel) {
        return productService.findProductByCriteria(productCriteriaModel);
    }

    @RequestMapping(value = "/product/recommend", method = RequestMethod.GET)
    public List<ProductBriefResponseDTO> showRecommend(@RequestParam Long userid) {
        return productService.getRecommendProduct(userid);
    }
}
