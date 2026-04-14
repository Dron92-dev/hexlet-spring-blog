package io.hexlet.springblog;

import io.hexlet.springblog.model.Post;
import jakarta.validation.Valid;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    private List<Post> posts = new ArrayList<Post>();
    private Long nextId = 1L;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping
    public List<Post> index() {
        return posts;
    }

    @GetMapping("/{id}")
    public Optional<Post> show(@PathVariable Long id) {
        return posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @PostMapping
    public Post create(@Valid @RequestBody Post post) {
        post.setId(nextId);
        nextId++;

        if (post.getCreatedAt() == null) {
            post.setCreatedAt(LocalDateTime.now());
        }

        posts.add(post);
        return post;
    }

    @PutMapping("/{id}")
    public Post update(@PathVariable Long id, @RequestBody Post data) {
        var maybePost = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        if (maybePost.isPresent()) {
            var post = maybePost.get();
            post.setTitle(data.getTitle());
            post.setContent(data.getContent());
            post.setAuthor(data.getAuthor());
            post.setCreatedAt(data.getCreatedAt());
        }

        return data;
    }

    @DeleteMapping("/{id}")
    public void destroy(@PathVariable Long id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
