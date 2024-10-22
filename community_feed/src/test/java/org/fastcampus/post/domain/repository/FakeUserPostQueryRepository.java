package org.fastcampus.post.domain.repository;

import java.util.List;
import java.util.stream.Collectors;
import org.fastcampus.post.repository.entity.post.PostEntity;
import org.fastcampus.post.repository.post_queue.UserPostQueueQueryRepository;
import org.fastcampus.post.ui.dto.GetPostContentResponseDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")
public class FakeUserPostQueryRepository implements UserPostQueueQueryRepository {

    private final FakeUserQueueRedisRepository userQueueRedisRepository;

    public FakeUserPostQueryRepository(FakeUserQueueRedisRepository userQueueRedisRepository) {
        this.userQueueRedisRepository = userQueueRedisRepository;
    }

    @Override
    public List<GetPostContentResponseDto> getContentResponse(Long userId, Long lastPostId) {
        List<PostEntity> postEntityList = userQueueRedisRepository.getPostListByUserId(userId);
        List<GetPostContentResponseDto> result;

        result = postEntityList.stream()
            .map(postEntity -> GetPostContentResponseDto.builder()
                .id(postEntity.getId())
                .build())
            .collect(Collectors.toList());

        return result;
    }
}
