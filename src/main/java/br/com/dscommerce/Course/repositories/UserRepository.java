package br.com.dscommerce.Course.repositories;

import br.com.dscommerce.Course.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
