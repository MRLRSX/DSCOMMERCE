package br.com.dscommerce.Course.dto;

import br.com.dscommerce.Course.entities.Product;

public record DadosBasicosProductDTO(Long id, String name, String description, Double price, String imgUrl)
{
    public DadosBasicosProductDTO(Product product){
        this(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getImgUrl());
    }
}
