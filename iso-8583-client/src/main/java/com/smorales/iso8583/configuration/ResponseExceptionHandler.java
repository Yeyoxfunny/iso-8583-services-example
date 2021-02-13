package com.smorales.iso8583.configuration;

import com.smorales.iso8583.dto.GatewayResponseDTO;
import com.smorales.iso8583.util.ServerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@ControllerAdvice(
        annotations = RestController.class)
public class ResponseExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GatewayResponseDTO> handleError(final MethodArgumentNotValidException ex) {
        String errorMessage = "Validation failed for arguments: " + ex.getBindingResult()
                .getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));
        GatewayResponseDTO response = new GatewayResponseDTO();
        response.setMessage(errorMessage);
        LOGGER.info("HTTP Status 400 Body: {}", ServerUtils.stringifyAsJson(response));
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<GatewayResponseDTO> handleError(final ResponseStatusException ex) {
        GatewayResponseDTO response = new GatewayResponseDTO();
        response.setMessage(ex.getReason());
        LOGGER.info("HTTP Status {} Body: {}", ex.getRawStatusCode(), ServerUtils.stringifyAsJson(response));
        return ResponseEntity.status(ex.getRawStatusCode()).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GatewayResponseDTO> handleError(final Exception ex) {
        LOGGER.error("Unexpected Error", ex);
        GatewayResponseDTO response = new GatewayResponseDTO();
        response.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        LOGGER.info("HTTP Status 500 Body: {}", ServerUtils.stringifyAsJson(response));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}
