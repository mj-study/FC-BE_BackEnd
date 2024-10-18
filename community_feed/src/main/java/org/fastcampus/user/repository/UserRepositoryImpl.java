package org.fastcampus.user.repository;

import lombok.RequiredArgsConstructor;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.repository.entity.UserEntity;
import org.fastcampus.user.repository.jpa.JpaUserRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository userRepository;

    @Override
    public User save(User user) {
        UserEntity entity = new UserEntity(user);
        entity = userRepository.save(entity);
        return entity.toUser();
    }

    @Override
    public User findById(long id) {
        UserEntity entity = userRepository
            .findById(id)
            .orElseThrow(IllegalArgumentException::new);

        return entity.toUser();
    }
}
