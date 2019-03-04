package com.kovalenko.service.document;

import com.kovalenko.DocumentServiceApplicationTests;
import com.kovalenko.binding.UploadDocument;
import com.kovalenko.entity.document.Document;
import com.kovalenko.entity.user.User;
import com.kovalenko.repository.document.DocumentRepository;
import com.kovalenko.service.user.UserService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class DocumentServiceTest extends DocumentServiceApplicationTests {

    @Autowired
    private DocumentService documentService;

    @MockBean
    private DocumentRepository documentRepository;
    @MockBean
    private UserService userService;

    private static User admin;
    private static List<Document> documents;
    private static Document savedDocument;

    @BeforeClass
    public static void createDocument() {
        admin = new User(1L,
                "admin",
                "admin",
                "admin",
                1,
                Collections.emptySet(),
                Collections.emptyList());

        documents = Arrays.asList(
                 new Document(1L,
                        "title1",
                        "type1",
                        "description1",
                        LocalDateTime.now(),
                        null,
                         admin),
                 new Document(2L,
                        "title2",
                        "type2",
                        "description2",
                        LocalDateTime.now(),
                        null,
                        admin),
                 new Document(3L,
                        "title3",
                        "type3",
                        "description3",
                        LocalDateTime.now(),
                        null,
                        admin)
        );

        savedDocument = new Document(4L,
                "title4",
                "type4",
                "description4",
                LocalDateTime.now(),
                null,
                admin);
    }

    @Test
    public void find_whenDocumentsAreNotExist_shouldReturnEmptyList() {
        Mockito.when(documentRepository.findAll())
                .thenReturn(Collections.emptyList());

        List<Document> documents = documentService.find();

        assertEquals(0, documents.size());
    }

    @Test
    public void find_whenDocumentsAreExist_shouldReturnDocumentsList() {
        Mockito.when(documentRepository.findAll())
                .thenReturn(documents);

        List<Document> documents = documentService.find();

        assertTrue(documents.size() > 0);
    }

    @Test
    public void findByID_whenDocumentIsNotExist_shouldReturnNull() {
        Mockito.when(documentRepository.findById(1L))
                .thenReturn(Optional.of(documents.get(0)));

        Document document = documentService.findByID(1);

        assertNotNull(document);
        assertEquals(1, document.getId());
    }

    @Test
    public void findByID_whenDocumentIsExist_shouldReturnDocument() {
        Mockito.when(documentRepository.findById(100L))
                .thenReturn(Optional.empty());

        Document document = documentService.findByID(100);

        assertNull(document);
    }

    @Test
    public void save_shouldSaveDocument() {
        UploadDocument newDocument = new UploadDocument();
        newDocument.setDescription("description4");

        Mockito.when(userService.getUserByLogin("admin"))
                .thenReturn(admin);
        Mockito.when(documentRepository.save(savedDocument))
                .thenReturn(savedDocument);

        documentService.save("admin", newDocument);

        assertNotNull(savedDocument);
        assertEquals(4, savedDocument.getId());
        assertNotNull(savedDocument.getTitle());
        assertNotNull(savedDocument.getType());
        assertEquals("admin", savedDocument.getAuthor().getLogin());
    }

    @Test
    public void update_shouldUpdateDocument() {
        Document existDocument = documents.get(0);
        Mockito.when(documentRepository.findById(1L))
                .thenReturn(Optional.of(existDocument));
        existDocument.setTitle("newTitle");
        existDocument.setDescription("newDescription");
        Mockito.when(documentRepository.save(existDocument))
                .thenReturn(existDocument);

        Document updatedDocument = documentService.update(1L, new Document("newTitle", "newDescription"));

        assertNotNull(updatedDocument);
        assertEquals("newTitle", updatedDocument.getTitle());
        assertEquals("newDescription", updatedDocument.getDescription());
    }
}