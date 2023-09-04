package com.driver.blogposts.controllers;

import com.driver.blogposts.BlogPost;
import com.driver.blogposts.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class BlogPostRepository {
    //DataBase for blogPosts
    HashMap<String, BlogPost> blogsDb = new HashMap<>();

    //database for users
    HashMap<String, User> userDb = new HashMap<>();

    //database to stor user and its blogs
    HashMap<String, List<BlogPost>> usersBlogDb = new HashMap<>();

    public String createUser(User user) {
        if(userDb.containsKey(user.getUserName())){
            return "User already exist.";
        }
        userDb.put(user.getUserName(),user);
        return "New User created successfully.";
    }

    public String createBlog(BlogPost post, String userName, String password) {
        //check for username and password
        if(userDb.containsKey(userName) && password.equals(userDb.get(userName).getPassword())){
            //add post only if it is not present in database
            if(blogsDb.containsKey(post.getTitle())){
                return "This title is already present. Please change the blog title.";
            }
            //add blog in blogs database
            blogsDb.put(post.getTitle(), post);

            //now add the blog in usersBlog data base
            List<BlogPost> usersList = new ArrayList<>();
            if(usersBlogDb.containsKey(userName)){
                usersList = usersBlogDb.get(userName);
            }
            usersBlogDb.put(userName, usersList);

            return "New Blog post created successfully !!";
        }else {
            return "UserName or password is incorrect.";
        }
    }

    public BlogPost readBlog(String blogTitle) {
        //check blog database and return
        if(blogsDb.containsKey(blogTitle)){
            return blogsDb.get(blogTitle);
        }
        BlogPost empdtyBlog = new BlogPost();
        return empdtyBlog;
    }

    public String deletePost(String postTitle, String userName, String password) {
        if(!blogsDb.containsKey(postTitle)){
            return "Post does not exist.";
        }else if(!userDb.containsKey(userName)){
            return "Username is wrong !!";
        }else if(!password.equals(userDb.get(userName).getPassword())){
            return "Incorrect password.";
        }else {
            //delete post
            blogsDb.remove(postTitle);
            List<BlogPost> blogList = usersBlogDb.get(userName);
            for(BlogPost post: blogList){
                if(postTitle.equals(post.getTitle())){
                    blogList.remove(post);
                    break;
                }
            }
            return "Blog deleted successfully !!";
        }
    }
}
