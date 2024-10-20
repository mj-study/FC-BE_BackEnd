package org.fastcampus.post.repository.post_queue;

import org.fastcampus.post.repository.entity.post.PostEntity;

public interface UserPostQueueCommandRepository {

    /**
     * 팔로워 한 사람들에게 발행한 글을 알리는 피드
     */
    void publishPost(PostEntity postEntity);

    /**
     * 팔로우를 했을때 해당 유저에게 작성한 글 목록을 피드로 생성
     *
     * @param userId:   팔로잉을 한 유저
     * @param targetId: 팔로우 대상
     */
    void saveFollowPost(Long userId, Long targetId);

    /**
     * 팔로우를 삭제했을때 해당 피드 목록 삭제
     *
     * @param userId:   팔로우를 삭제한 유저
     * @param targetId: 팔로우 삭제 대상
     */
    void deleteUnfollowPost(Long userId, Long targetId);

}
