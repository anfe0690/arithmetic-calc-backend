package io.github.anfe0690.arithcalcbackend.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByUsernameAndPasswordHashAndStatus(String username, String passwordHash, Status status);
}
