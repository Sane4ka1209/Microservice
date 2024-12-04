package com.shoping.domain.dto.requests;

import lombok.*;
import org.springframework.validation.BindingResult;

import java.util.*;
import java.util.stream.Collectors;
import org.springframework.validation.FieldError;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
    @Builder.Default
    private Map<String, String> fieldErrors = new HashMap<>();
    @Builder.Default
    private List<String> globalErrors = new ArrayList<>();

    public static ErrorResponse fromBindingResult(BindingResult bindingResult){
        return ErrorResponse.builder()
                .fieldErrors(bindingResult.getFieldErrors().stream()
                        .collect(Collectors.toMap(FieldError::getField,
                                err -> Objects.requireNonNullElse(err.getDefaultMessage(), "")))
                )
                .globalErrors(bindingResult.getGlobalErrors().stream()
                        .map(err -> Objects.requireNonNullElse(err.getDefaultMessage(), ""))
                        .collect(Collectors.toList()))
                .build();
    }

    public static ErrorResponse fromString(String str){
        return ErrorResponse.builder()
                .globalErrors(List.of(str))
                .build();
    }
}
