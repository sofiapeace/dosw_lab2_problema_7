package com.techcup.imageservice.repository;

import com.techcup.imageservice.model.document.ImageDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ImageRepository extends MongoRepository<ImageDocument, String> {
    List<ImageDocument> findByExternalReference(String externalReference);
}