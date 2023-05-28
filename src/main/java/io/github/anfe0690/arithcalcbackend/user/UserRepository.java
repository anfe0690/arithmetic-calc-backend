package io.github.anfe0690.arithcalcbackend.user;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    List<UserEntity> findByUsernameAndPasswordHash(String username, String passwordHash);
}
