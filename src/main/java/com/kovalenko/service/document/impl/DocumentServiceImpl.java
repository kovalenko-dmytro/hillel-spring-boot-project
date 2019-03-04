package com.kovalenko.service.document.impl;

import com.kovalenko.binding.UploadDocument;
import com.kovalenko.entity.document.Document;
import com.kovalenko.repository.document.DocumentRepository;
import com.kovalenko.service.document.DocumentService;
import com.kovalenko.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final UserService userService;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository,
                               UserService userService) {

        this.documentRepository = documentRepository;
        this.userService = userService;
    }

    @Override
    public List<Document> find() {
        return (List<Document>) documentRepository.findAll();
    }

    @Override
    public Document findByID(long id) {
        return documentRepository.findById(id).orElse(null);
    }

    @Override
    public void save(String authorLogin, UploadDocument document) {
        Document savedDocument = new Document();
        savedDocument.setAuthor(userService.getUserByLogin(authorLogin));
        savedDocument.setCreated(LocalDateTime.now());
        savedDocument.setDescription(document.getDescription());

        MultipartFile file = document.getFile();
        if (file != null) {
            savedDocument.setTitle(file.getOriginalFilename());
            savedDocument.setType(file.getContentType());
            try {
                savedDocument.setContent(file.getBytes());
            } catch (IOException e) {
                savedDocument.setContent(null);
            }
        }
        documentRepository.save(savedDocument);
    }

    @Override
    public Document update(long id, Document document) {
        Optional<Document> optionalDocument = documentRepository.findById(id);
        if (optionalDocument.isPresent()) {
            Document updatedDocument = optionalDocument.get();
            updatedDocument.setTitle(document.getTitle());
            updatedDocument.setDescription(document.getDescription());
            documentRepository.save(updatedDocument);
        }
        return findByID(id);
    }

    @Override
    public void delete(long id) {
        documentRepository.deleteById(id);
    }
}
