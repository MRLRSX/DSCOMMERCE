package br.com.dscommerce.Course.services;

import br.com.dscommerce.Course.dto.DadosBasicosProductDTO;
import br.com.dscommerce.Course.dto.DadosInsertProductDTO;
import br.com.dscommerce.Course.dto.UpdateProductDTO;
import br.com.dscommerce.Course.entities.Product;
import br.com.dscommerce.Course.infraestrutura.exceptions.ResourceNotFoundException;
import br.com.dscommerce.Course.repositories.ProductRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public DadosBasicosProductDTO findById(Long id){
      Product product =  productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
      return new DadosBasicosProductDTO(product);
    }

    @Transactional(readOnly = true)
    public Page<DadosBasicosProductDTO> findAll(Pageable paginacao){
        return productRepository.findAll(paginacao).map(DadosBasicosProductDTO::new);
    }

    @Transactional
    public DadosBasicosProductDTO insert (DadosInsertProductDTO dados){
         Product product = new Product(null, dados.name(), dados.description(), dados.price(), dados.imgUrl());
         productRepository.save(product);
         return new DadosBasicosProductDTO(product);
    }

    @Transactional
    public DadosBasicosProductDTO update(UpdateProductDTO dados){
        Product product = productRepository.getReferenceById(dados.id());
        copyDtoToEntity(dados, product);
        product = productRepository.save(product);
        return new DadosBasicosProductDTO(product);
    }
    @Transactional
    public void deletarProduct(Long id){
        productRepository.deleteById(id);
    }
    private void copyDtoToEntity(UpdateProductDTO dados, Product product){
        product.setName(dados.name());
        product.setDescription(dados.description());
        product.setPrice(dados.price());
        product.setImgUrl(dados.imgUrl());
    }
}
