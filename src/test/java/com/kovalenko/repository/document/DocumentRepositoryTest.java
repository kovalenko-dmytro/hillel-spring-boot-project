package com.kovalenko.repository.document;

import com.kovalenko.DocumentServiceApplicationTests;
import com.kovalenko.entity.document.Document;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class DocumentRepositoryTest extends DocumentServiceApplicationTests {

    @Autowired
    private DocumentRepository documentRepository;

    private static Document document;

    @BeforeClass
    public static void createDocument() {
        document = new Document(0L,
                "title",
                "type",
                "description",
                LocalDateTime.now(),
                null,
                null);
    }

    @Before
    public void clear() {
        documentRepository.deleteAll();

    }

    @Test
    public void findAll_whenDocumentsNotExist_ShouldReturnEmptyList() {
        List<Document> documents = (List<Document>) documentRepository.findAll();
        assertNotNull(documents);
        assertEquals(0, documents.size());
    }

    @Test
    public void findAll_whenDocumentsAreExist_ShouldReturnNotEmptyList() {
        documentRepository.save(document);
        List<Document> documents = (List<Document>) documentRepository.findAll();
        assertNotNull(documents);
        assertEquals(1, documents.size());
    }

    @Test
    public void save_whenDocumentSaved_ShouldReturnDocument() {
        Document saveDocument = documentRepository.save(document);
        assertNotNull(saveDocument);
    }

    @Test
    public void findById_whenDocumentNotExist_ShouldReturnNull() {
        Document document = documentRepository.findById(1L).orElse(null);
        assertNull(document);
    }

    @Test
    public void findById_whenDocumentIsExist_ShouldReturnDocument() {
        Document saveDocument = documentRepository.save(document);
        Document document = documentRepository.findById(saveDocument.getId()).orElse(null);
        assertNotNull(document);
    }

    @Test
    public void deleteById_whenDocumentIsExist_ShouldReturnDocument() {
        Document saveDocument = documentRepository.save(document);
        documentRepository.deleteById(saveDocument.getId());
        Document document = documentRepository.findById(saveDocument.getId()).orElse(null);
        assertNull(document);
    }
}
