package com.kovalenko.service.document;

import com.kovalenko.binding.UploadDocument;
import com.kovalenko.entity.document.Document;

import java.util.List;

public interface DocumentService {
    List<Document> find();
    Document findByID(long id);
    void save(String authorLogin, UploadDocument document);
    Document update(long id, Document document);
    void delete(long id);
}
