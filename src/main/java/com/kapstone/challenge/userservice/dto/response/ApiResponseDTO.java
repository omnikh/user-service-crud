package com.kapstone.challenge.userservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseDTO<T> {
    private T data;
    private List<T> dataList;
    private HttpStatus status;
    private String message;
    private boolean error;
    private LocalDateTime timestamp = LocalDateTime.now();

    public ApiResponseDTO(T data, HttpStatus status,
                          String message, boolean error) {
        this.data = data;
        this.status = status;
        this.message = message;
        this.error = error;
    }

    public ApiResponseDTO(T data, List<T> dataList, HttpStatus status,
                          String message, boolean error) {
        this.data = data;
        this.dataList = dataList;
        this.status = status;
        this.message = message;
        this.error = error;
    }

    public ApiResponseDTO(HttpStatus status, String message, boolean error) {
        this.status = status;
        this.message = message;
        this.error = error;
    }

}
