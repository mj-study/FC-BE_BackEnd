package org.fastcampus.user.domain;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.fastcampus.common.domain.PositiveIntegerCounter;

@Getter
@Builder
@AllArgsConstructor
public class User {

    private Long id;
    private UserInfo userInfo;
    private PositiveIntegerCounter followingCounter;
    private PositiveIntegerCounter followerCounter;

    public User(Long id, UserInfo userInfo) {
        if (userInfo == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.userInfo = userInfo;
        this.followerCounter = new PositiveIntegerCounter();
        this.followingCounter = new PositiveIntegerCounter();
    }

    public void follow(User targetUser) {
        // 본인이 본인을 팔로우하면 error
        if (this.equals(targetUser)) {
            throw new IllegalArgumentException();
        }

        followingCounter.increase();
        targetUser.increaseFollowerCount();
    }

    public void unfollow(User targetUser) {
        if (this.equals(targetUser)) {
            throw new IllegalArgumentException();
        }

        followingCounter.decrease();
        targetUser.decreaseFollowerCount();
    }

    private void increaseFollowerCount() {
        followerCounter.increase();
    }

    private void decreaseFollowerCount() {
        followerCounter.decrease();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public int followingCount() {
        return followingCounter.getCount();
    }

    public int followerCount() {
        return followerCounter.getCount();
    }

    public String getName() {
        return userInfo.getName();
    }

    public String getProfileImageUrl() {
        return userInfo.getProfileImageUrl();
    }

}
