package br.com.dscommerce.Course.repositories;

import br.com.dscommerce.Course.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>
{

}
