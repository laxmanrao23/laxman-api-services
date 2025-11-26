package com.laxman.job.listing.repository;

import com.laxman.job.listing.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactRepository extends  MongoRepository<Contact, String>{

}
