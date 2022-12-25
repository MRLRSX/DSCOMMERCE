package br.com.dscommerce.Course.infraestrutura.exceptionsdto;

import org.springframework.validation.FieldError;

import java.time.Instant;

public record CustomError(Instant timestamp, Integer status, String error, String path) {



}
