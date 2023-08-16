package onlineshop.example.beeshop.service;

import onlineshop.example.beeshop.entity.Image;
import onlineshop.example.beeshop.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class ImageServiceImpl implements ImageService{
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private Environment environment;
    @Override
    public Image getImageByName(String filename) throws IOException {
        Image image = imageRepository.findByName(filename).orElseThrow(() -> new RuntimeException("file not found"));
        return image;
    }

    @Override
    public String saveImage(MultipartFile file, Long imageId) throws IOException {
        long current = System.currentTimeMillis();
        String filePath = environment.getProperty("environment.img-root-path")
                + current
                + file.getOriginalFilename();

        if (imageId != null) {
            Image existImage = imageRepository.findById(imageId).orElse(null);
            if (existImage != null) {
                removeImage(existImage.getPath());
            }
        }
        Image image = imageRepository.save(
                (imageId == null ?  Image.builder()
                        .name(current + file.getOriginalFilename())
                        .type(file.getContentType())
                        .path(filePath).build() :
                        Image.builder()
                                .name(current + file.getOriginalFilename())
                                .type(file.getContentType())
                                .path(filePath)
                                .id(imageId).build())
        );

        file.transferTo(new File(filePath));


        if (image != null) {
            return "file up load successfully: " + filePath;
        }
        return null;
    }

    public void removeImage(String pathname) throws IOException {
        Image image = imageRepository.findByPath(pathname).orElseThrow(() -> new RuntimeException("file not found"));
        Files.delete(new File(pathname).toPath());
    }
}
