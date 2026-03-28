package com.techcup.imageservice.model.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "images")
public class ImageDocument {
    
    @Id
    private String id;
    private String name;
    private String contentType;
    private Long size;
    private byte[] data;
    private LocalDateTime uploadDate;
    private String externalReference;

    public ImageDocument() {}

    public ImageDocument(String name, String contentType, Long size, byte[] data, LocalDateTime uploadDate, String externalReference) {
        this.name = name;
        this.contentType = contentType;
        this.size = size;
        this.data = data;
        this.uploadDate = uploadDate;
        this.externalReference = externalReference;
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getContentType() { return contentType; }
    public void setContentType(String contentType) { this.contentType = contentType; }
    public Long getSize() { return size; }
    public void setSize(Long size) { this.size = size; }
    public byte[] getData() { return data; }
    public void setData(byte[] data) { this.data = data; }
    public LocalDateTime getUploadDate() { return uploadDate; }
    public void setUploadDate(LocalDateTime uploadDate) { this.uploadDate = uploadDate; }
    public String getExternalReference() { return externalReference; }
    public void setExternalReference(String externalReference) { this.externalReference = externalReference; }
}