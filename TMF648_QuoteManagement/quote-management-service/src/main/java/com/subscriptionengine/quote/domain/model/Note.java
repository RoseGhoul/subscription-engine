package com.subscriptionengine.quote.domain.model;
import java.time.OffsetDateTime;
import lombok.Data;
@Data
public class Note {
    private String id;
    private String text;
    private String author;
    private OffsetDateTime date;
}
