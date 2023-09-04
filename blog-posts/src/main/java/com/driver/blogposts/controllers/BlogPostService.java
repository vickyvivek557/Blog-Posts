package com.driver.blogposts.controllers;

import com.driver.blogposts.BlogPost;
import com.driver.blogposts.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogPostService {
    @Autowired
    BlogPostRepository blogPostRepository;
    public String createUser(User user) {
        return blogPostRepository.createUser(user);
    }

    public String createBlog(BlogPost post, String userName, String password) {
        return blogPostRepository.createBlog(post, userName, password);
    }

    public BlogPost readBlog(String blogTitle) {
        return blogPostRepository.readBlog(blogTitle);
    }

    public String deletePost(String postTitle, String userName, String password) {
        return blogPostRepository.deletePost(postTitle, userName, password);
    }
}
