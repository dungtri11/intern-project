package onlineshop.example.beeshop.service;

import onlineshop.example.beeshop.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    public Image getImageByName(String filename) throws IOException;

    public String saveImage(MultipartFile file, Long imageId) throws IOException;
}
