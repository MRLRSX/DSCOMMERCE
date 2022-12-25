package br.com.dscommerce.Course.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.sql.Update;

public record UpdateProductDTO(

        @NotNull Long id,
        @NotBlank String name,
        @NotBlank String description,
        @NotNull Double price,
        @NotBlank String imgUrl) {


}
