package com.techcup.imageservice.controller;

import com.techcup.imageservice.model.document.ImageDocument;
import com.techcup.imageservice.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<ImageDocument> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("externalReference") String externalReference) {
        try {
            ImageDocument savedImage = imageService.uploadImage(file, externalReference);
            return new ResponseEntity<>(savedImage, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<ImageDocument> getAllImages() { return imageService.getAllImages(); }

    @GetMapping("/{id}")
    public ResponseEntity<ImageDocument> getImageById(@PathVariable String id) {
        return imageService.getImageById(id)
                .map(image -> new ResponseEntity<>(image, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/reference/{externalReference}")
    public List<ImageDocument> getImagesByExternalReference(@PathVariable String externalReference) {
        return imageService.getImagesByExternalReference(externalReference);
    }

    @DeleteMapping("/{id}")
    public void deleteImage(@PathVariable String id) { imageService.deleteImage(id); }
}
