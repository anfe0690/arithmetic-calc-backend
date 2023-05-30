package io.github.anfe0690.arithcalcbackend.v1.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

    UserEntity findByUsernameAndPasswordHashAndStatus(String username, String passwordHash, Status status);
}
