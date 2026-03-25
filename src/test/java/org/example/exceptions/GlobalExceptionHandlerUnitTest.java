package org.example.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.ProblemDetail;
import org.springframework.mock.web.MockHttpServletRequest;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GlobalExceptionHandlerUnitTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void arithmeticExceptionReturnsBadRequest() {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/demo/divide-by-zero");
        ProblemDetail response = handler.handleArithmeticException(new ArithmeticException("/ by zero"), request);

        assertEquals(400, response.getStatus());
        assertEquals("Invalid Arithmetic Operation", response.getTitle());
        assertEquals("Mathematical error: cannot divide by zero.", response.getDetail());
        assertEquals(URI.create("https://api.example.com/problems/invalid-arithmetic"), response.getType());
        assertEquals(URI.create("/demo/divide-by-zero"), response.getInstance());
        assertNotNull(response.getProperties());
        assertNotNull(response.getProperties().get("timestamp"));
    }

    @Test
    void genericExceptionReturnsInternalServerError() {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/demo/unexpected-error");
        ProblemDetail response = handler.handleGenericException(new IllegalStateException("boom"), request);

        assertEquals(500, response.getStatus());
        assertEquals("Internal Server Error", response.getTitle());
        assertEquals("Unexpected server error.", response.getDetail());
        assertEquals(URI.create("https://api.example.com/problems/internal-server-error"), response.getType());
        assertEquals(URI.create("/demo/unexpected-error"), response.getInstance());
        assertNotNull(response.getProperties());
        assertNotNull(response.getProperties().get("timestamp"));
    }
}

