package org.fastcampus.post.repository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.fastcampus.post.application.interfaces.PostRepository;
import org.fastcampus.post.domain.Post;
import org.fastcampus.post.repository.entity.post.PostEntity;
import org.fastcampus.post.repository.jpa.JpaPostRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final JpaPostRepository postRepository;

    @Override
    @Transactional
    public Post save(Post post) {
        PostEntity entity = new PostEntity(post);
        if (post.getId() != null) {
            postRepository.updatePostEntity(entity);
            return entity.toPost();
        }
        entity = postRepository.save(entity);
        return entity.toPost();
    }

    @Override
    public Post findById(Long id) {
        PostEntity entity = postRepository.findById(id).orElseThrow();
        return entity.toPost();
    }
}
