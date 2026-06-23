package com.subscriptionengine.productorder.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteJpaEntity {
    @Column(name = "note_id")
    private String id;
    
    @Column(name = "note_author")
    private String author;
    
    @Column(name = "note_date")
    private OffsetDateTime date;
    
    @Column(name = "note_text")
    private String text;
}
