package io.github.anfe0690.arithcalcbackend.v1.operation;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;

public interface OperationService {

    ResponseEntity<?> performOperation(OperationDto operation, HttpSession session);
}
