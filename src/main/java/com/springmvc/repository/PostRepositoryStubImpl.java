package com.springmvc.repository;

import com.springmvc.model.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepositoryStubImpl implements PostRepository {
    private List<Post> posts = new ArrayList<>();
    private static long countId = 1;

    public List<Post> all() {
        return this.posts;
    }

    public Optional<Post> getById(long id) {
        for (Post post : posts) {
            if (post.getId() == id) {
                return Optional.of(post);
            }
        }
        return Optional.empty();
    }

    public Post save(Post post) {
        synchronized (post) {
            if (post.getId() == 0) {
                posts.add(new Post(countId, post.getContent()));
                countId++;
                return post;
            } else if (post.getId() > 0){
                for (Post pst : posts) {
                    if (post.getId() == pst.getId()) {
                        posts.set(posts.indexOf(pst), post);
                        return pst;
                    }else {
                        posts.add(post);
                        return post;
                    }
                }
            }
            return post;
        }
    }

    public void removeById(long id) {
        for (Post post : posts) {
            if (post.getId() == id) {
                posts.remove(post);
                return;
            }
        }
    }
}
