package se.sina.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sina.webshop.model.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
