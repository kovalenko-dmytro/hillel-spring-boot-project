package com.kovalenko.entity.document;

import com.kovalenko.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private long id;

    @NotNull
    @Size(min = 1)
    private String title;
    private String type;
    @NotNull
    @Size(min = 1)
    private String description;
    private LocalDateTime created;

    @Lob
    @Column(name = "content", columnDefinition="BLOB")
    private byte[] content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public Document(@NotNull @Size(min = 1) String description) {
        this.description = description;
    }

    public Document(@NotNull @Size(min = 1) String title, @NotNull @Size(min = 1) String description) {
        this.title = title;
        this.description = description;
    }
}
