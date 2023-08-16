package onlineshop.example.beeshop.controller;

import onlineshop.example.beeshop.dto.ProductBriefResponseDTO;
import onlineshop.example.beeshop.entity.Image;
import onlineshop.example.beeshop.model.ProductCriteriaModel;
import onlineshop.example.beeshop.repository.ImageRepository;
import onlineshop.example.beeshop.service.ImageService;
import onlineshop.example.beeshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private ImageRepository imageRepository;
    @RequestMapping(value = "/mainpage", method = RequestMethod.GET)
    public List<ProductBriefResponseDTO> showMainPage(ProductCriteriaModel productCriteriaModel) {
        return productService.findProductByCriteria(productCriteriaModel);
    }

    @RequestMapping(value = "/product/recommend", method = RequestMethod.GET)
    public List<ProductBriefResponseDTO> showRecommend(@RequestParam Long userid) throws IOException {
        return productService.getRecommendProduct(userid);
    }

    @RequestMapping(value = "/img/upload", method = RequestMethod.POST)
    public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file, @RequestParam(value = "imgId", required = false) Long imgId) throws IOException {
        String response = imageService.saveImage(file, imgId);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/img/download/{filename}", method = RequestMethod.GET)
    public ResponseEntity<?> downloadImage(@PathVariable String filename) throws IOException {
        Image image = imageService.getImageByName(filename);

        String filePath = image.getPath();
        byte[] imageData = Files.readAllBytes(new File(filePath).toPath());
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(image.getType()))
                .body(imageData);
    }
}
