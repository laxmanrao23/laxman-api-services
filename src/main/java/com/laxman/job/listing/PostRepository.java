package com.laxman.job.listing;

import com.laxman.job.listing.model.JobPost;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PostRepository extends MongoRepository<JobPost, String> {

}
