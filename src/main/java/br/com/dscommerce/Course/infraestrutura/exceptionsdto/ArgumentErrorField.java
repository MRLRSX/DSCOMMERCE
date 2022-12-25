package br.com.dscommerce.Course.infraestrutura.exceptionsdto;

import org.springframework.validation.FieldError;

import java.time.Instant;
import java.util.List;

public record ArgumentErrorField(Instant timestamp, Integer status, String path, String field, String message) {


}
