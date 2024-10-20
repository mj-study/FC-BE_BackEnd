package org.fastcampus.user.repository.jpa;

import java.util.List;
import org.fastcampus.user.repository.entity.UserRelationEntity;
import org.fastcampus.user.repository.entity.UserRelationIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaUserRelationRepository extends
    JpaRepository<UserRelationEntity, UserRelationIdEntity> {

    @Query("SELECT u.followerUserId "
        + "FROM UserRelationEntity u "
        + "WHERE u.followingUserId = :userId ")
    List<Long> findAllFollowers(Long userId);

}
