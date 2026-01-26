package com.laxman.job.server.repository;

import com.laxman.job.server.model.JobPost;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PostRepository extends MongoRepository<JobPost, String> {

}
