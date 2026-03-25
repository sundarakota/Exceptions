package org.example.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GlobalExceptionHandlerUnitTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void arithmeticExceptionReturnsBadRequest() {
        ResponseEntity<ErrorResponse> response = handler.handleArithmeticException(new ArithmeticException("/ by zero"));

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Mathematical Error: Cannot divide by zero.", response.getBody().getMessage());
        assertNotNull(response.getBody().getTimestamp());
    }

    @Test
    void genericExceptionReturnsInternalServerError() {
        ResponseEntity<ErrorResponse> response = handler.handleGenericException(new IllegalStateException("boom"));

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Unexpected server error.", response.getBody().getMessage());
        assertNotNull(response.getBody().getTimestamp());
    }
}

