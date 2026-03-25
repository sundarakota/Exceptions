package org.example.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.OffsetDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ArithmeticException.class)
    public ProblemDetail handleArithmeticException(ArithmeticException ex, HttpServletRequest request) {
        return buildProblem(
                HttpStatus.BAD_REQUEST,
                URI.create("https://api.example.com/problems/invalid-arithmetic"),
                "Invalid Arithmetic Operation",
                "Mathematical error: cannot divide by zero.",
                request.getRequestURI()
        );
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGenericException(Exception ex, HttpServletRequest request) {
        return buildProblem(
                HttpStatus.INTERNAL_SERVER_ERROR,
                URI.create("https://api.example.com/problems/internal-server-error"),
                "Internal Server Error",
                "Unexpected server error.",
                request.getRequestURI()
        );
    }

    private ProblemDetail buildProblem(HttpStatus status, URI type, String title, String detail, String path) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, detail);
        problem.setType(type);
        problem.setTitle(title);
        problem.setInstance(URI.create(path));
        problem.setProperty("timestamp", OffsetDateTime.now().toString());
        return problem;
    }
}
