package io.github.anfe0690.arithcalcbackend.v1.operation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class OperationServiceImplTest {

    private OperationServiceImpl operationService;

    @BeforeEach
    public void beforeEach() {
        operationService = spy(new OperationServiceImpl());
    }

    @Test
    public void performOperationWhenAdditionThenReturnAddition() throws IOException {
        assertEquals("9", operationService.performOperation(Type.ADDITION, 5, 4));
    }

    @Test
    public void performOperationWhenSubtractionThenReturnSubtraction() throws IOException {
        assertEquals("22", operationService.performOperation(Type.SUBTRACTION, 45, 23));
    }

    @Test
    public void performOperationWhenMultiplicationThenReturnMultiplication() throws IOException {
        assertEquals("253", operationService.performOperation(Type.MULTIPLICATION, 23, 11));
    }

    @Test
    public void performOperationWhenDivisionThenReturnDivision() throws IOException {
        assertEquals("3.0", operationService.performOperation(Type.DIVISION, 99, 33));
    }

    @Test
    public void performOperationWhenSquareRootThenReturnSquareRoot() throws IOException {
        assertEquals("6.0", operationService.performOperation(Type.SQUARE_ROOT, 36, 0));
    }

    @Test
    public void performOperationWhenRandomStringThenReturnRandomString() throws IOException {
        doReturn("QWERTY").when(operationService).generateRandomString();

        assertEquals("QWERTY", operationService.performOperation(Type.RANDOM_STRING, 0, 0));
    }
}
