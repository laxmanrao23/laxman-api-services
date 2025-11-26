package com.laxman.job.listing.controller;


import com.laxman.job.listing.repository.PostRepository;
import com.laxman.job.listing.model.JobPost;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class PostController {

    @Autowired
    PostRepository repo;

    @Hidden

    @RequestMapping(value = "/")
     public  void  redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }
    @GetMapping("/posts")
    public List<JobPost> getAllPosts() {
        return  repo.findAll();
    }

    @PostMapping("/JobPost")
    public JobPost addPost(@RequestBody JobPost jobPost) throws BadRequestException {
        if (jobPost.isValid()) {
            throw new BadRequestException("Job posting is not valid");
        }
        return repo.save(jobPost);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<JobPost> updatePost (
        @PathVariable ("id") String id,
        @RequestBody JobPost updatedPost) {
        Optional<JobPost> existingPost = repo.findById(id);
        if (existingPost.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "JobPost not found with id: " + id);
        }

        JobPost post = existingPost.get();
        post.setDesc(updatedPost.getDesc());
        post.setExp(updatedPost.getExp());
        post.setProfile(updatedPost.getProfile());
        post.setTechs(updatedPost.getTechs());


        JobPost saved = repo.save(updatedPost);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") String id){
        Optional<JobPost> existingPost = repo.findById(id);
        if (existingPost.isEmpty()) {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "JobPost not found with id: " + id);
        }

        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
