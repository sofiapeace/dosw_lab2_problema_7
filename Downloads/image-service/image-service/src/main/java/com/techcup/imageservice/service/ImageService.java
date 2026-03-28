package com.techcup.imageservice.service;

import com.techcup.imageservice.model.document.ImageDocument;
import com.techcup.imageservice.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public ImageDocument uploadImage(MultipartFile file, String externalReference) throws IOException {
        ImageDocument image = new ImageDocument(
            file.getOriginalFilename(),
            file.getContentType(),
            file.getSize(),
            file.getBytes(),
            LocalDateTime.now(),
            externalReference
        );
        return imageRepository.save(image);
    }

    public List<ImageDocument> getAllImages() { return imageRepository.findAll(); }
    public Optional<ImageDocument> getImageById(String id) { return imageRepository.findById(id); }
    public List<ImageDocument> getImagesByExternalReference(String externalReference) { return imageRepository.findByExternalReference(externalReference); }
    public void deleteImage(String id) { imageRepository.deleteById(id); }
}