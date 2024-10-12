package org.fastcampus.user.application;

import static org.junit.jupiter.api.Assertions.*;

import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.application.dto.FollowUserRequestDto;
import org.fastcampus.user.application.interfaces.UserRelationRepository;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.repository.FakeUserRelationRepository;
import org.fastcampus.user.repository.FakeUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserRelationServiceTest {

    private final UserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);
    private final UserRelationRepository userRelationRepository = new FakeUserRelationRepository();
    private final UserRelationService userRelationService = new UserRelationService(userService,
        userRelationRepository);

    private User user1;
    private User user2;

    private FollowUserRequestDto requestDto;

    @BeforeEach
    void init() {
        CreateUserRequestDto dto = new CreateUserRequestDto("test", "");
        this.user1 = userService.createUser(dto);
        this.user2 = userService.createUser(dto);

        this.requestDto = new FollowUserRequestDto(user1.getId(), user2.getId());
    }

    @Test
    void givenCreateTwoUser_whenFollow_thenUserFollowSaved() {
        //when
        userRelationService.follow(requestDto);

        //then
        assertEquals(1, user1.followingCount());
        assertEquals(1, user2.followerCount());
    }

    @Test
    void givenCreateTwoUser_whenFollow_thenUserThrowError() {
        //given
        userRelationService.follow(requestDto);

        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(requestDto));
    }

    @Test
    void givenCreateOneUser_whenSameUserFollow_thenUserThrowError() {
        //given
        FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(),
            user1.getId());

        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(sameUser));
    }

    @Test
    void givenCreateTwoUser_whenUnFollow_thenUserUnFollowSaved() {
        //given
        userRelationService.follow(requestDto);

        //when
        userRelationService.unFollow(requestDto);

        //then
        assertEquals(0, user1.followingCount());
        assertEquals(0, user2.followerCount());
    }

    @Test
    void givenCreateTwoUser_whenUnFollow_thenUserThrowError() {
        //given
        userRelationService.follow(requestDto);
        userRelationService.unFollow(requestDto);
        //when
        //then
        assertThrows(IllegalArgumentException.class,
            () -> userRelationService.unFollow(requestDto));
    }

    @Test
    void givenCreateOneUser_whenSameUserUnFollow_thenUserThrowError() {
        //given
        FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(),
            user1.getId());

        //when
        //then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unFollow(sameUser));
    }
}