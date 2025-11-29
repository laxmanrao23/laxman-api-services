package com.laxman.job.listing.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "contacts")
public class Contact {

    @Id
    private String id;

    private String fullname;
    private String email;
    private String message;
}
