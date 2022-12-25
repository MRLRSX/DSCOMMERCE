package br.com.dscommerce.Course.controllers;


import br.com.dscommerce.Course.dto.DadosBasicosProductDTO;
import br.com.dscommerce.Course.dto.DadosInsertProductDTO;
import br.com.dscommerce.Course.dto.UpdateProductDTO;
import br.com.dscommerce.Course.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity detalharProduct(@PathVariable Long id){
        var result = productService.findById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<Page<DadosBasicosProductDTO>> productPaginado(@PageableDefault(size = 12, sort = {"name"})Pageable paginacao){
       var dados = productService.findAll(paginacao);
       return ResponseEntity.ok(dados);
    }

    @PostMapping
    public ResponseEntity<DadosBasicosProductDTO> cadastrarProduct(@RequestBody @Valid DadosInsertProductDTO dados, UriComponentsBuilder uriBuilder){
        var dadosSalvos = productService.insert(dados);
        var uriComponents = uriBuilder.path("/products/{id}").buildAndExpand(dadosSalvos.id()).toUri();
        return ResponseEntity.created(uriComponents).body(dadosSalvos);
    }

   @PutMapping
    public ResponseEntity<DadosBasicosProductDTO> updateProduct(@RequestBody @Valid UpdateProductDTO dados){
         DadosBasicosProductDTO product = productService.update(dados);
         return ResponseEntity.ok(product);
   }

   @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deletarProduct(id);
        return ResponseEntity.noContent().build();
   }


}
