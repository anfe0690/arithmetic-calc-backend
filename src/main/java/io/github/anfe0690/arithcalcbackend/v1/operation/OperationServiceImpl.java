package io.github.anfe0690.arithcalcbackend.v1.operation;

import org.springframework.stereotype.Service;

@Service
public class OperationServiceImpl implements OperationService{

    @Override
    public String processOperation(Type type, int firstOperand, int secondOperand) {
        switch (type) {
            case ADDITION:
                return Integer.toString(firstOperand + secondOperand);
            case SUBTRACTION:
                return Integer.toString(firstOperand - secondOperand);
            case MULTIPLICATION:
                return Integer.toString(firstOperand * secondOperand);
            case DIVISION:
                return Double.toString(firstOperand * 1.0 / secondOperand);
            case SQUARE_ROOT:
                return Double.toString(Math.sqrt(firstOperand));
            case RANDOM_STRING:
                throw new RuntimeException("Not implemented");
            default:
                throw new IllegalArgumentException("Unexpected operation type: " + type);
        }
    }
}
