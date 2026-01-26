package com.laxman.job.server.repository;

import com.laxman.job.server.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactRepository extends  MongoRepository<Contact, String>{

}
