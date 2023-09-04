package com.driver.blogposts.controllers;

import com.driver.blogposts.BlogPost;
import com.driver.blogposts.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BlogPostController {
    @Autowired
    BlogPostService blogPostService;
    @PostMapping("/createUser")
    public String createUser(@RequestBody User user){
        return blogPostService.createUser(user);
    }
    @PostMapping("/create-blog/{name}/{pass}")
    public String createBlog(@RequestBody BlogPost post, @PathVariable("name") String userName, @PathVariable("pass") String password){
        return blogPostService.createBlog(post, userName, password);
    }
    @GetMapping("/get-blog")
    public BlogPost readBlog(@RequestParam("q") String blogTitle){
        return blogPostService.readBlog(blogTitle);
    }
    @DeleteMapping("/delete/{title}/{name}/{pass}")
    public String deletePost(@PathVariable("title") String postTitle, @PathVariable("name") String userName, @PathVariable("pass") String password){
        return blogPostService.deletePost(postTitle, userName, password);
    }
}
