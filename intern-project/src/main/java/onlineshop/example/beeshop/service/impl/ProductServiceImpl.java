package onlineshop.example.beeshop.service.impl;

import onlineshop.example.beeshop.dto.ProductBriefResponseDTO;
import onlineshop.example.beeshop.entity.Image;
import onlineshop.example.beeshop.model.ProductCriteriaModel;
import onlineshop.example.beeshop.entity.Product;
import onlineshop.example.beeshop.repository.ImageRepository;
import onlineshop.example.beeshop.repository.OrderRepository;
import onlineshop.example.beeshop.repository.ProductRepository;
import onlineshop.example.beeshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private Environment environment;

    @Override
    public List<ProductBriefResponseDTO> findProductByCriteria(ProductCriteriaModel productCriteriaModel) {
        List<ProductBriefResponseDTO> responseDTOS = new ArrayList<>();
        for (Product product : productRepository.findProductByCriteria(productCriteriaModel)) {
            ProductBriefResponseDTO productBriefResponseDTO = new ProductBriefResponseDTO(product);
            Image image = imageRepository.findById(product.getImage().getId())
                    .orElseThrow(() -> new RuntimeException("file not found"));
            productBriefResponseDTO.setImageUrl(environment.getProperty("environment.root-path") + image.getName());
            responseDTOS.add(productBriefResponseDTO);
        }
        return responseDTOS;
    }
    @Override
    public List<ProductBriefResponseDTO> getRecommendProduct(Long userid) throws IOException {
        List<String> recommendCategories = orderRepository.recommendCategory(userid);
        List<ProductBriefResponseDTO> responseDTOS = new ArrayList<>();
        for (String recommendCategory : recommendCategories) {
            for (Product product : productRepository.recommendProduct(recommendCategory)) {
                ProductBriefResponseDTO productBriefResponseDTO = new ProductBriefResponseDTO(product);
                Image image = imageRepository.findById(product.getImage().getId())
                        .orElseThrow(() -> new RuntimeException("file not found"));
                productBriefResponseDTO.setImageUrl(environment.getProperty("environment.root-path") + image.getName());
                responseDTOS.add(productBriefResponseDTO);
            }
        }
        return responseDTOS;
    }

    @Override
    public Product viewById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product addNew(Product product) {
        if (productRepository.existsById(product.getId())) {
            return null;
        }
        return productRepository.save(product);
    }

    @Override
    public Product editInfo(Product product) {
        if (productRepository.existsById(product.getId())) {
            return null;
        }
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
