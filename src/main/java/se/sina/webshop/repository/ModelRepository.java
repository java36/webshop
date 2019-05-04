package se.sina.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sina.webshop.model.entity.Model;

public interface ModelRepository extends JpaRepository<Model, Long> {
}
