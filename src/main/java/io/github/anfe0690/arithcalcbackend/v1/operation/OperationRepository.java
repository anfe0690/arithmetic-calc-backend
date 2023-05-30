package io.github.anfe0690.arithcalcbackend.v1.operation;

import org.springframework.data.repository.CrudRepository;

public interface OperationRepository extends CrudRepository<OperationEntity, Long> {

    OperationEntity findByType(Type type);
}
